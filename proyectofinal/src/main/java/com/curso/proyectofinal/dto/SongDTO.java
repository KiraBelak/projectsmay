package com.curso.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDTO {
    private Long id;
    private String title;
    private Integer durationInSeconds;
    private String genre;
    private Long albumId;
    private String albumTitle;
    private Long artistId;
    private String artistName;
}
