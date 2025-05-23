package com.alldata.jproject.service;

import com.alldata.jproject.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public Usuario save(Usuario usuario);
    public void delete(Integer id);
    public Usuario update(Usuario usuario);
    public Optional<Usuario> findById(Integer id);
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findAll();
}
