package com.alldata.jproject.modelsDTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentoDTO {
    private int id;
    private String instrumento_name;
    private String descripcion;
    private int precio;
    private int stock;
    private String imagen;
    private Date created_at;
    
    private int marca_id;
    private String marca_name;
}
