package com.example.herotozero;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.text.Collator;

@Named("Laender")
@ViewScoped
public class Laenderliste implements Serializable {

    private List<Land> laenderListe = new ArrayList<Land>();
    private List<Land> gefilterteLaender;
    private Land neuesLand = null;
    private int minJahr;
    private int maxJahr;
    private int selectedJahr;

    public Laenderliste() {
    }

    @PostConstruct
    public void init() {
        listeAktualisieren();
        if (laenderListe != null && !laenderListe.isEmpty()) {
            this.minJahr = laenderListe.stream().mapToInt(Land::getJahr).min().getAsInt();
            this.maxJahr = laenderListe.stream().mapToInt(Land::getJahr).max().getAsInt();
        } else {
            this.minJahr = 2000;
            this.maxJahr = 2026;
        }
        this.selectedJahr = this.maxJahr;
        this.gefilterteLaender = this.laenderListe.stream().filter(l -> l.getJahr() == this.selectedJahr).collect(Collectors.toList());
    }

    public void listeAktualisieren() {
        Collator deCollator = Collator.getInstance(Locale.GERMAN);
        deCollator.setStrength(Collator.PRIMARY);
        Comparator<Land> landComparator = (land1, land2) -> deCollator
                .compare(land1.getName(), land2.getName());
        landComparator = landComparator.thenComparingInt(Land::getJahr);
        this.laenderListe = LaenderListeController.getInstance().getLaender().stream()
                .sorted(landComparator).collect(Collectors.toList());
        this.gefilterteLaender = new ArrayList<>(this.laenderListe);
    }

    public void handleSave(Benutzer aktuellerUser) {
        try {
            //Neues Land-Objekt an den Controller zum Speichern senden
            LaenderListeController.getInstance().saveLand(this.neuesLand, aktuellerUser);
            //Liste der Länder neu aus der Datenbank laden, damit das neue Land auch auf der Webseite bzw. in derTabelle angezeigt wird. Tabelle muss auch neu geladen werden
            listeAktualisieren();
            this.gefilterteLaender = this.laenderListe.stream().filter(l -> l.getJahr() == this.selectedJahr).collect(Collectors.toList());
            //Für das nächste neue Land zurücksetzten. Alle daten des vorherigen Hinzufügens müssen weg.
            this.neuesLand = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Land erfolgreich hinzugefügt."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", e.getMessage()));
        }
    }

    public void updateSingleLand(Land land, Benutzer aktuellerUser) {
        LaenderListeController.getInstance().saveSingleEdit(land, aktuellerUser);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Update eingereicht",
                "Änderungsvorschlag für " + land.getName() + " wurde gespeichert.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        land.setGeaendert(false);
    }

    public List<Land> getLaenderListe() {
        return laenderListe;
    }

    public Land getNeuesLand() {
        if (null == neuesLand) {
            this.neuesLand = new Land();
            this.neuesLand.setJahr(2024);
        }
        return this.neuesLand;
    }

    public List<Land> getGefilterteLaender() {
        return gefilterteLaender;
    }

    public void setGefilterteLaender(List<Land> gefilterteLaender) {
        this.gefilterteLaender = gefilterteLaender;
    }

    public int getMinJahr() {
        return minJahr;
    }

    public void setMinJahr(int minJahr) {
        this.minJahr = minJahr;
    }

    public int getMaxJahr() {
        return maxJahr;
    }

    public void setMaxJahr(int maxJahr) {
        this.maxJahr = maxJahr;
    }

    public int getSelectedJahr() {
        return selectedJahr;
    }

    public void setSelectedJahr(int selectedJahr) {
        this.selectedJahr = selectedJahr;
    }
}
