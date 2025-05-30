package com.alldata.jproject.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasificacionDTO {
    private int id;
    private String clasificacion_name;
    private String descripcion;

}
