package com.alldata.jproject.database.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tlb_instrumento")
public class Instrumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "instrumento_name", nullable = false, length = 100)
    private String instrumento_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "marca_id", nullable = false)
    private int marca_id;

    @Column(name = "precio", nullable = false)
    private int precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public int getId() {
        return id;
    }

    public String getInstrumento_name() {
        return instrumento_name;
    }

    public void setInstrumento_name(String instrumento_name) {
        this.instrumento_name = instrumento_name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMarca_id() {
        return marca_id;
    }

    public void setMarca_id(int marca_id) {
        this.marca_id = marca_id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
