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

    public String saveEdit() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (Land a : laenderliste.getLaenderListe())
            em.merge(a);
        et.commit();
        return "editCO2";
    }

    public void saveSingleEdit(Land landAenderung) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        LandUpdate landVorschlag = new LandUpdate(landAenderung.getLaendercode(), landAenderung.getName(), landAenderung.getCo2Emission(), landAenderung.getGemeldetAm(), landAenderung.getID());
        em.persist(landVorschlag);
        et.commit();
    }

    public void saveLand(Land neuesLand) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            List<Land> existiert = em.createQuery("SELECT l FROM Land l WHERE l.laendercode = :n", Land.class).setParameter("n", neuesLand.getLaendercode()).getResultList();
            if (!existiert.isEmpty()) {
                if (neuesLand.getID() == null) {
                    throw new Exception("Das Land existiert bereits in der Datenbank! Bitte aktualisiere den Eintrag des Landes!");
                }
                if (!existiert.get(0).getID().equals(neuesLand.getID())) {
                    throw new Exception("Das Land mit dem Ländercode wurde bereits angegeben! Bitte aktualisiere den Eintrag des Landes!");
                }
            }
            et.begin();
            LandUpdate landVorschlag;
            if (neuesLand.getID() == null) {
                landVorschlag = new LandUpdate(neuesLand.getLaendercode(), neuesLand.getName(), neuesLand.getCo2Emission(), neuesLand.getGemeldetAm());
                //em.persist(neuesLand);
            } else {
                landVorschlag = new LandUpdate(neuesLand.getLaendercode(), neuesLand.getName(), neuesLand.getCo2Emission(), neuesLand.getGemeldetAm(), neuesLand.getID());
                //em.merge(neuesLand);
            }
            em.persist(landVorschlag);
            et.commit();
        } finally {
            em.close();
        }
    }
}