package com.alldata.jproject.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instrumento {

    public Instrumento(
            String instrumento_name, String descripcion, int precio, int stock, String imagen, Marca marca) {
        this.instrumento_name = instrumento_name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.marca = marca;
        this.created_at = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "instrumento_name", nullable = false, length = 100)
    private String instrumento_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private int precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

}
