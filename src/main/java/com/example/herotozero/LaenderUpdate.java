package com.example.herotozero;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("Update")
@ViewScoped
public class LaenderUpdate implements Serializable {

    private static LaenderUpdate instance = new LaenderUpdate();
    private List<LandUpdate> laenderUpdate = new ArrayList<LandUpdate>();

    public LaenderUpdate() {
        listeAktualisieren();
    }

    public void listeAktualisieren() {
        this.laenderUpdate = LaenderListeController.getInstance().getUpdatesOffen();
    }

    public void accept(LandUpdate update) {
        try {
            LaenderListeController.getInstance().acceptUpdate(update);
            listeAktualisieren();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Das Land " + update.getName() + " wurde aktualisiert."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Aktualisierung von " + update.getName() + " ist fehlgeschlagen: " + e.getMessage()));
        }
    }

    public void reject(LandUpdate update) {
        try {
            LaenderListeController.getInstance().rejectUpdate(update);
            listeAktualisieren();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Die Aktualisierung von " + update.getName() + " wurde abgelehnt."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Ablehnen der Aktualisierung von " + update.getName() + " ist fehlgeschlagen: " + e.getMessage()));
        }
    }

    public static LaenderUpdate getInstance() {
        return instance;
    }

    public List<LandUpdate> getLaenderUpdate() {
        return laenderUpdate;
    }
}
