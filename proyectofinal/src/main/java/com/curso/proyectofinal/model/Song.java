package com.curso.proyectofinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título de la canción es obligatorio")
    private String title;

    @Positive(message = "La duración debe ser un valor positivo")
    private Integer durationInSeconds;

    private String genre;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
}
