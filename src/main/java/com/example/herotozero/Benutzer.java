package com.example.herotozero;

import jakarta.persistence.*;

@Entity
public class Benutzer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer ID;
    String name;
    String passwort;
    @Enumerated(EnumType.STRING)
    private Rolle rolle = Rolle.BENUTZER;

    public Benutzer() {
    }

    public Benutzer(String name, String passwort) {
        super();
        this.name = name;
        this.passwort = passwort;
        this.rolle = Rolle.BENUTZER;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof  Benutzer) {
            Benutzer b = (Benutzer)obj;
            if(b.getName().equals(this.name) && b.getPasswort().equals(this.passwort)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }
}
