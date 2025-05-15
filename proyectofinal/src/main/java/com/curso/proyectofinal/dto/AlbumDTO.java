package com.curso.proyectofinal.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumDTO {
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String genre;
    private Long artistId;
    private String artistName;
}
