package com.example.herotozero;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Named("Laender")
@ApplicationScoped
public class Laenderliste implements Serializable {
    private static Laenderliste instance = new Laenderliste();
    private List<Land> liste = new ArrayList<Land>();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public Laenderliste() {
        try {
            liste.add(new Land("Deutschland", 572319170.00f, dateFormat.parse("23.04.2026")));
            liste.add(new Land("Österreich", 56367656.00f, dateFormat.parse("23.04.2026")));
            liste.add(new Land("Schweiz", 31977486.00f, dateFormat.parse("23.04.2026")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Laenderliste getInstance() {
        return instance;
    }

    public List<Land> getListe() {
        return liste;
    }
}
