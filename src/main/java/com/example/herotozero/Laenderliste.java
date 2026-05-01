package com.example.herotozero;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named("Laender")
@ApplicationScoped
public class Laenderliste implements Serializable {
    private static Laenderliste instance = new Laenderliste();
    private List<Land> laenderListe = new ArrayList<Land>();
    private Land neuesLand = null;

    //Lädt initial beim Start die Liste der Länder von Server
    public Laenderliste() {
        this.laenderListe = LaenderListeController.getInstance().getLaender();
    }

    public static Laenderliste getInstance() {
        return instance;
    }

    public List<Land> getLaenderListe() {
        return laenderListe;
    }

    public Land getNeuesLand() {
        if(null == neuesLand) {
            this.neuesLand = new Land();
            neuesLand.setGemeldetAm(new Date());
        }
        return this.neuesLand;
    }

    public String handleSave() {
        try {
            //Neues Land-Objekt an den Controller zum Speichern senden
            LaenderListeController.getInstance().saveLand(this.neuesLand);
            //Liste der Länder neu aus der Datenbank laden, damit das neue Land auch auf der Webseite bzw. in derTabelle angezeigt wird. Tabelle muss auch neu geladen werden
            this.laenderListe = LaenderListeController.getInstance().getLaender();
            //Für das nächste neue Land zurücksetzten. Alle daten des vorherigen Hinzufügens müssen weg.
            this.neuesLand = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erfolg", "Land erfolgreich hinzugefügt."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", e.getMessage()));
        }
        return "editCO2";
    }

    public String abbruch() {
        this.neuesLand = null;
        //muss die required Attribute der Eingabefelder umgehen können
        return "editCO2";
    }
}
