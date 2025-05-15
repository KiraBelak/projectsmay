package com.curso.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {
    private Long id;
    private String name;
    private String biography;
    private String country;
}
