package com.example.herotozero;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

    public String startEdit() {
        return "editCO2";
    }

    public String saveEdit() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("heroToZeroPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        for (Land a : laenderliste.getListe())
            em.merge(a);
        et.commit();
        return "editCO2";
    }

    private Land neuesLand = null;

    public String addLand() {
        return "addLand";
    }

    public Land getNeuesLand() {
        if(null == neuesLand) {
            this.neuesLand = new Land();
            neuesLand.setGemeldetAm(new Date());
        }
        return this.neuesLand;
    }

    public void handleSave() {
        this.neuesLand.speichern();
    }

    public String abbruch() {
        //muss die required Attribute der Eingabefelder umgehen können
        return "editCO2";
    }

    public String saveLand(Land neuesLand) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(neuesLand);
        et.commit();
        em.close();
        return null;
    }
}
