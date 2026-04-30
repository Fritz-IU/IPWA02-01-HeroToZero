package com.example.herotozero;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named("Laender")
@ApplicationScoped
public class Laenderliste implements Serializable {
    private static Laenderliste instance = new Laenderliste();
    private List<Land> liste = new ArrayList<Land>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Laenderliste() {
        this.liste = LaenderListeController.getInstance().getLaender();
        /*try {
            liste.add(new Land("DE", "Deutschland", 572319170.00, dateFormat.parse("23.04.2026")));
            liste.add(new Land("AT", "Österreich", 56367656.00, dateFormat.parse("23.04.2026")));
            liste.add(new Land("CH", "Schweiz", 31977486.00, dateFormat.parse("23.04.2026")));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
    }

    public static Laenderliste getInstance() {
        return instance;
    }

    public List<Land> getListe() {
        return liste;
    }
}
