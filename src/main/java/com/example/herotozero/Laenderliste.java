package com.example.herotozero;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named("Laender")
@ViewScoped
public class Laenderliste implements Serializable {

    private List<Land> laenderListe = new ArrayList<Land>();
    private List<Land> gefilterteLaender;
    private Land neuesLand = null;

    public Laenderliste() {
    }

    @PostConstruct
    public void init() {
        listeAktualisieren();
    }

    public void listeAktualisieren() {
        this.laenderListe = LaenderListeController.getInstance().getLaender();
        this.gefilterteLaender = LaenderListeController.getInstance().getLaender();
    }

    public void handleSave() {
        try {
            //Neues Land-Objekt an den Controller zum Speichern senden
            LaenderListeController.getInstance().saveLand(this.neuesLand);
            //Liste der Länder neu aus der Datenbank laden, damit das neue Land auch auf der Webseite bzw. in derTabelle angezeigt wird. Tabelle muss auch neu geladen werden
            this.laenderListe = LaenderListeController.getInstance().getLaender();
            //Für das nächste neue Land zurücksetzten. Alle daten des vorherigen Hinzufügens müssen weg.
            this.neuesLand = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Land erfolgreich hinzugefügt."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", e.getMessage()));
        }
    }

    public String abbruch() {
        this.neuesLand = null;
        return "editCO2";
    }

    public void updateSingleLand(Land land) {
        LaenderListeController.getInstance().saveSingleEdit(land);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update eingereicht", "Änderungsvorschlag für " + land.getName() + " wurde gespeichert.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Land> getLaenderListe() {
        return laenderListe;
    }

    public Land getNeuesLand() {
        if(null == neuesLand) {
            this.neuesLand = new Land();
            this.neuesLand.setJahr(2025);
        }
        return this.neuesLand;
    }

    public List<Land> getGefilterteLaender() {
        return gefilterteLaender;
    }

    public void setGefilterteLaender(List<Land> gefilterteLaender) {
        this.gefilterteLaender = gefilterteLaender;
    }
}
