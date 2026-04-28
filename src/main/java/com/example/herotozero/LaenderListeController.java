package com.example.herotozero;


import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Named("controller")
@ViewScoped
public class LaenderListeController implements Serializable {

    @Inject
    private Laenderliste laenderliste;

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
}
