package com.alldata.jproject.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcaDTO {
    private int id;
    private String marca_name;
    private String descripcion;
    private int clasificacion_id;
    private String clasificacion_name;
}
