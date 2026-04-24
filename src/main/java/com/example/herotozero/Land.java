package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Land {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ID;
    private String name;
    private double co2Emission;
    private Date gemeldetAm;

    public Land() {}

    public Land(String name, double co2Emission, Date gemeldetAm) {
        super();
        this.name = name;
        this.co2Emission = co2Emission;
        this.gemeldetAm = gemeldetAm;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emmission) {
        this.co2Emission = co2Emmission;
    }

    public Date getGemeldetAm() {
        return gemeldetAm;
    }

    public void setGemeldetAm(Date gemeldetAm) {
        this.gemeldetAm = gemeldetAm;
    }
}
