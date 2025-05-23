package com.finalproject.gamestop.model;

import jakarta.persistence.Entity;

@Entity
public class Accessory extends Product {

    private String type;
    private String compatibility;
    private String brand;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
