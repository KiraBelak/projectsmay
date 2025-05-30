package com.alldata.jproject.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Marca {

    public Marca(String marca_name,String descripcion, Clasificacion clasificacion) {
        this.marca_name = marca_name;
        this.descripcion = descripcion;
        this.clasificacion = clasificacion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marca_name", nullable = false, length = 100)
    private String marca_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "clasificacion_id")
    private Clasificacion clasificacion;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
    private Set<Instrumento> instrumentos = new HashSet<>();
}
