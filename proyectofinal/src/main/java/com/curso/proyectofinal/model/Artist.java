package com.curso.proyectofinal.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del artista es obligatorio")
    private String name;

    private String biography;

    private String country;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private Set<Album> albums = new HashSet<>();
}
