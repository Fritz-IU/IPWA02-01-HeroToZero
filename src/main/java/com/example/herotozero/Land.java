package com.example.herotozero;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "land", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"laendercode", "jahr"})
})
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @Column(name = "laendercode", nullable = false, length = 2, columnDefinition = "CHAR(2)")
    private String laendercode;
    private String name;
    private double co2Emission;
    private int jahr;

    public Land() {
    }

    public Land(int gemeldetAm) {
        super();
        this.jahr = gemeldetAm;
    }

    public Land(String laendercode, String name, double co2Emission, int jahr) {
        super();
        this.name = name;
        this.laendercode = laendercode;
        this.co2Emission = co2Emission;
        this.jahr = jahr;
    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public int getJahr() {
        return jahr;
    }

    public void setJahr(int jahr) {
        this.jahr = jahr;
    }

    public String getLaendercode() {
        return laendercode;
    }

    public void setLaendercode(String laendercode) {
        this.laendercode = laendercode;
    }
}
