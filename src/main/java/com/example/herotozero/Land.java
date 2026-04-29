package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Land {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int ID;
    private String laendercode;
    private String name;
    private double co2Emission;
    private Date gemeldetAm;

    public Land() {}

    public Land(Date gemeldetAm) {
        super();
        this.gemeldetAm = gemeldetAm;
    }

    public Land(String laendercode, String name, double co2Emission, Date gemeldetAm) {
        super();
        this.name = name;
        this.laendercode = laendercode;
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

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public Date getGemeldetAm() {
        return gemeldetAm;
    }

    public void setGemeldetAm(Date gemeldetAm) {
        this.gemeldetAm = gemeldetAm;
    }

    public String getLaendercode() {
        return laendercode;
    }

    public void setLaendercode(String laendercode) {
        this.laendercode = laendercode;
    }
}
