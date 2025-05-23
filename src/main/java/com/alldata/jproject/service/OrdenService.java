package com.alldata.jproject.service;

import com.alldata.jproject.model.Orden;
import com.alldata.jproject.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface OrdenService {
    Orden save(Orden orden);
    List<Orden> findAll();
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
    Optional<Orden> findById(Integer id);
}
