package com.example.herotozero;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginController implements Serializable {
    String name;
    Benutzer benutzer;

    List<Benutzer> benutzerliste;

    public LoginController() {
        this.benutzerliste = new ArrayList<Benutzer>();
        this.benutzer = new Benutzer();
    }

    public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
        UIInput tmp = (UIInput)ev.getComponent();
        this.name = (String) tmp.getValue();
        int breakpoint = 1;
    }

    public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        for(Benutzer b:benutzerliste) {
            Benutzer tmp = new Benutzer(this.name, (String)value);
            if(b.equals(tmp))
                return;
        }
        throw new ValidatorException(new FacesMessage("Login falsch!"));
    }

    public String adminLogin() {
        int breakpoint = 1;
        if (this.name.equals("Admin"))
            return "checkUpdate";
        else if (this.name.equals("Wissenschaftler"))
            return "editCO2";
        else
            return "showCO2";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }
}
