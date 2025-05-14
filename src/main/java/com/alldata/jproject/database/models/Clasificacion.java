package com.alldata.jproject.database.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_clasificacion")
public class Clasificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clasificacion_name", nullable = false, length = 100)
    private String clasificacion_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    public int getId() {
        return id;
    }

    public String getClasificacion_name() {
        return clasificacion_name;
    }

    public void setClasificacion_name(String clasificacion_name) {
        this.clasificacion_name = clasificacion_name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
