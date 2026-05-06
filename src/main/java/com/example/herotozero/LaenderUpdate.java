package com.example.herotozero;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Named("Update")
@ApplicationScoped
public class LaenderUpdate implements Serializable {
    /*
    private static LaenderUpdate instance = new LaenderUpdate();
    private List<Land> laenderUpdate = new ArrayList<Land>();

    public LaenderUpdate() {
        this.laenderUpdate = LaenderListeController.getInstance().getUpdate();
    }

    public static LaenderUpdate getInstance() {
        return instance;
    }

    public List<Land> getLaenderUpdate() {
        return laenderUpdate;
    }*/
}
