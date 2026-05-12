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
}
