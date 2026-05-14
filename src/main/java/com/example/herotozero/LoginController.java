package com.example.herotozero;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("Login")
@SessionScoped
public class LoginController implements Serializable {
    private String name;
    private String passwort;
    private Benutzer benutzerAngemeldet;
    private Benutzer neuerBenutzer = new Benutzer();

    public String login() {
        Benutzer b = LaenderListeController.getInstance().login(name, passwort);
        if (b != null) {
            this.benutzerAngemeldet = b;
            if (b.getRolle() == Rolle.WISSENSCHAFTLER) {
                return "editCO2";
            } else if (b.getRolle() == Rolle.ADMIN) {
                return "checkUpdate";
            } else {
                return "showCO2";
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Der Login ist fehlgeschlagen!"));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "showCO2";
    }

    public String registrieren() {
        boolean erfolg = LaenderListeController.getInstance().registrieren(neuerBenutzer);
        if (erfolg) {
            this.neuerBenutzer = new Benutzer();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Registrierung erfolgreich."));
            return "login";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Name schon vergeben."));
            return null;
        }
    }

    public Rolle[] getRolle() {
        return Rolle.values();
    }

    public boolean isWissen() {
        return benutzerAngemeldet != null && benutzerAngemeldet.getRolle() == Rolle.WISSENSCHAFTLER;
    }

    public boolean isAdmin() {
        return benutzerAngemeldet != null && benutzerAngemeldet.getRolle() == Rolle.ADMIN;
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

    public Benutzer getBenutzerAngemeldet() {
        return benutzerAngemeldet;
    }

    public void setBenutzerAngemeldet(Benutzer benutzerAngemeldet) {
        this.benutzerAngemeldet = benutzerAngemeldet;
    }

    public Benutzer getNeuerBenutzer() {
        return neuerBenutzer;
    }

    public void setNeuerBenutzer(Benutzer neuerBenutzer) {
        this.neuerBenutzer = neuerBenutzer;
    }
}
