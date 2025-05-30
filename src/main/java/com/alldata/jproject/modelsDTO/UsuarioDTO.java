package com.alldata.jproject.modelsDTO;


import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String username;
    private String password;
    private Set<String> roles;

}
