package com.alldata.jproject.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Clasificacion {

    public Clasificacion(String clasificacion_name,
            String descripcion) {
        this.clasificacion_name = clasificacion_name;
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "clasificacion_name", nullable = false, length = 100)
    private String clasificacion_name;

    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;

    @OneToMany(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private Set<Marca> marcas = new HashSet<>();

}
