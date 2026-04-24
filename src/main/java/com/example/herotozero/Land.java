package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Land {
    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    //private int ID;
    private String name;
    private double co2Emmission;
    private Date gemeldetAm;

    public Land() {}

    public Land(String name, double co2Emmission, Date gemeldetAm) {
        super();
        this.name = name;
        this.co2Emmission = co2Emmission;
        this.gemeldetAm = gemeldetAm;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCo2Emmission() {
        return co2Emmission;
    }

    public void setCo2Emmission(double co2Emmission) {
        this.co2Emmission = co2Emmission;
    }

    public Date getGemeldetAm() {
        return gemeldetAm;
    }

    public void setGemeldetAm(Date gemeldetAm) {
        this.gemeldetAm = gemeldetAm;
    }
}
