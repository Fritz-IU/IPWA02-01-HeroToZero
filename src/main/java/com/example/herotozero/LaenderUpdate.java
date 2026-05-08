package com.example.herotozero;

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
        this.laenderUpdate = LaenderListeController.getInstance().getUpdatesOffen();
    }

    public void accept(LandUpdate update) {
        LaenderListeController.getInstance().acceptUpdate(update);
        getLaenderUpdate();
    }

    public void reject(LandUpdate update) {
        LaenderListeController.getInstance().rejectUpdate(update);
        getLaenderUpdate();
    }

    public static LaenderUpdate getInstance() {
        return instance;
    }

    public List<LandUpdate> getLaenderUpdate() {
        return laenderUpdate;
    }
}
