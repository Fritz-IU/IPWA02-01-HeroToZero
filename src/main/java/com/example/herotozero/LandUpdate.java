package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class LandUpdate {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ID;
    private String laendercode;
    private String name;
    private double co2Emission;
    private Date gemeldetAm;
    @Enumerated(EnumType.STRING)
    private UpdateStatus updateStatus = UpdateStatus.OFFEN;
    private boolean vorhandenLand;
    private Integer idLand;
    private Date logDate;

    public LandUpdate(){    }

    public LandUpdate(String laendercode, String name, double co2Emission, Date gemeldetAm) {
        super();
        this.laendercode = laendercode;
        this.name = name;
        this.co2Emission = co2Emission;
        this.gemeldetAm = gemeldetAm;
        this.vorhandenLand = false;
        this.logDate = new Date();
    }

    public LandUpdate(String laendercode, String name, double co2Emission, Date gemeldetAm, Integer idLand) {
        this(laendercode, name, co2Emission, gemeldetAm);
        this.idLand = idLand;
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

    public Date getGemeldetAm() {
        return gemeldetAm;
    }

    public void setGemeldetAm(Date gemeldetAm) {
        this.gemeldetAm = gemeldetAm;
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

    public Integer getIdLand() {
        return idLand;
    }

    public void setIdLand(Integer idLand) {
        this.idLand = idLand;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}