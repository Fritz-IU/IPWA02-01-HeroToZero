package com.example.herotozero;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Named("controller")
@ViewScoped
public class LaenderListeController implements Serializable {

    private static LaenderListeController instance;
    private final EntityManagerFactory emf;

    private LaenderListeController() {
        this.emf = Persistence.createEntityManagerFactory("heroToZeroPersistenceUnit");
    }

    @Inject
    private Laenderliste laenderliste;

    public static synchronized LaenderListeController getInstance() {
        if (instance == null) {
            instance = new LaenderListeController();
        }
        return instance;
    }

    public List<Land> getLaender() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT l FROM Land l");
        List<Land> laender = q.getResultList();
        em.close();
        return laender;
    }

    public List<Land> getUpdate() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT u FROM LandUpdate u");
        List<Land> update = q.getResultList();
        em.close();
        return update;
    }

    public String startEdit() {
        return "editCO2";
    }

    public String checkUpdate() {
        return "checkUpdate";
    }

    public String showCO2() {
        return "showCO2";
    }

    public String addLand() {
        return "addLand";
    }

    public String anmeldung() {
        return "login";
    }

    public String registrierung() {
        return "registrieren";
    }

    public void saveSingleEdit(Land landAenderung, Benutzer aktuellerUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        LandUpdate landVorschlag = new LandUpdate(landAenderung.getLaendercode(),
                landAenderung.getName(),
                landAenderung.getCo2Emission(),
                landAenderung.getJahr(),
                landAenderung,
                aktuellerUser);
        em.persist(landVorschlag);
        et.commit();
        em.close();
    }

    public void saveLand(Land neuesLand, Benutzer aktuellerUser) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            List<Land> existiert = em.createQuery("SELECT l FROM Land l WHERE l.laendercode = :code AND l.jahr = :jahr", Land.class)
                    .setParameter("code", neuesLand.getLaendercode()).setParameter("jahr", neuesLand.getJahr()).getResultList();
            if (!existiert.isEmpty()) {
                if (neuesLand.getID() == null) {
                    throw new Exception("Für " + neuesLand.getName() + " existiert bereits ein Eintrag für das Jahr " + neuesLand.getJahr() + ". Bitte aktualisiere den vorhandene eintrag.");
                }
                if (!existiert.get(0).getID().equals(neuesLand.getID())) {
                    throw new Exception("Es existiert bereits eine anderer Eintrag für " + neuesLand.getName() + " im Jahr " + neuesLand.getJahr() + ".");
                }
            }
            et.begin();
            LandUpdate landVorschlag;
            if (neuesLand.getID() == null) {
                landVorschlag = new LandUpdate(neuesLand.getLaendercode(), neuesLand.getName(), neuesLand.getCo2Emission(), neuesLand.getJahr(), aktuellerUser);
            } else {
                landVorschlag = new LandUpdate(neuesLand.getLaendercode(), neuesLand.getName(), neuesLand.getCo2Emission(), neuesLand.getJahr(), neuesLand, aktuellerUser);
            }
            em.persist(landVorschlag);
            et.commit();
        } finally {
            em.close();
        }
    }

    public void acceptUpdate(LandUpdate update) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        if (update.getVorhandenLand()) {
            Land vorhanden = em.find(Land.class, update.getLand().getID());
            vorhanden.setName(update.getName());
            vorhanden.setLaendercode(update.getLaendercode());
            vorhanden.setCo2Emission(update.getCo2Emission());
            vorhanden.setJahr(update.getJahr());
            em.merge(vorhanden);
        } else {
            Land neu = new Land();
            neu.setName(update.getName());
            neu.setLaendercode(update.getLaendercode());
            neu.setCo2Emission(update.getCo2Emission());
            neu.setJahr(update.getJahr());
            em.persist(neu);
        }
        LandUpdate verwalteUpdate = em.find(LandUpdate.class, update.getID());
        verwalteUpdate.setUpdateStatus(UpdateStatus.AKZEPTIERT);
        em.merge(verwalteUpdate);
        et.commit();
        em.close();
    }

    public void rejectUpdate(LandUpdate update) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        LandUpdate verwalteUpdate = em.find(LandUpdate.class, update.getID());
        verwalteUpdate.setUpdateStatus(UpdateStatus.ABGELEHNT);
        em.merge(verwalteUpdate);
        et.commit();
        em.close();
    }

    public List<LandUpdate> getUpdatesOffen() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM LandUpdate u WHERE u.updateStatus = :s ORDER BY u.logDate ASC", LandUpdate.class)
                    .setParameter("s", UpdateStatus.OFFEN).getResultList();
        } finally {
            em.close();
        }
    }

    public Benutzer login(String name, String passwort) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Benutzer> query = em.createQuery("SELECT b FROM Benutzer b WHERE b.name = :name", Benutzer.class);
            query.setParameter("name", name);
            Benutzer b = query.getSingleResult();

            if (b != null && b.getPasswort().equals(passwort)) {
                return b;
            }
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return null;
    }

    public boolean registrieren(Benutzer neuerBenutzer) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            List<Benutzer> existiert = em.createQuery("SELECT b FROM Benutzer b WHERE b.name = :n", Benutzer.class).setParameter("n", neuerBenutzer.getName()).getResultList();
            if (!existiert.isEmpty()) {
                return false;
            } else {
                et.begin();
                em.persist(neuerBenutzer);
                et.commit();
                return true;
            }
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            System.err.println("Registrierung fehlgeschlagen! Name wird schon verwendet: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }
}