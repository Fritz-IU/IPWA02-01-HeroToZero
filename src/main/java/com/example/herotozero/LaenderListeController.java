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
    /*
    public List<Land> getUpdate() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT u FROM Update u");
        List<Land> update = q.getResultList();
        em.close();
        return update;
    }*/

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



    public void saveLand(Land neuesLand) throws Exception {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            List<Land> existiert = em.createQuery("SELECT l FROM Land l WHERE l.laendercode = :n", Land.class).setParameter("n", neuesLand.getLaendercode()).getResultList();
            if (!existiert.isEmpty()) {
                if (neuesLand.getID() == null) {
                    throw new Exception("Das Land existiert bereits ind der Datenbank!");
                }
                if (!existiert.get(0).getID().equals(neuesLand.getID())) {
                    throw new Exception("Das Land mit dem Ländercode wurde bereits angegeben.");
                }

            }
            et.begin();
            if (neuesLand.getID() == null) {
                em.persist(neuesLand);
            } else {
                em.merge(neuesLand);
            }
            et.commit();
        } finally {
            em.close();
        }
    }
}