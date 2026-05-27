package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LandUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String laendercode;
    private String name;
    private double co2Emission;
    private int jahr;
    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus = UpdateStatus.OFFEN;
    private boolean vorhandenLand;
    @ManyToOne
    @JoinColumn(name = "idLand", referencedColumnName = "ID")
    private Land land;
    private Date logDate;

    public LandUpdate() {
    }

    public LandUpdate(String laendercode, String name, double co2Emission, int jahr) {
        super();
        this.laendercode = laendercode;
        this.name = name;
        this.co2Emission = co2Emission;
        this.jahr = jahr;
        this.vorhandenLand = false;
        this.logDate = new Date();
    }

    public LandUpdate(String laendercode, String name, double co2Emission, int jahr, Land land) {
        this(laendercode, name, co2Emission, jahr);
        this.land = land;
        this.vorhandenLand = true;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLaendercode() {
        return laendercode;
    }

    public void setLaendercode(String laendercode) {
        this.laendercode = laendercode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public UpdateStatus getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(UpdateStatus updateStatus) {
        this.updateStatus = updateStatus;
    }

    public boolean isVorhandenLand() {
        return vorhandenLand;
    }

    public void setVorhandenLand(boolean vorhandenLand) {
        this.vorhandenLand = vorhandenLand;
    }

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}