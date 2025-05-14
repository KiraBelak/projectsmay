package com.alldata.jproject.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marca_name", nullable = false, length = 100)
    private String marca_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getMarca_name() {
        return marca_name;
    }

    public void setMarca_name(String marca_name) {
        this.marca_name = marca_name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
