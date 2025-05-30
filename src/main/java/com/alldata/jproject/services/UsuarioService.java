package com.alldata.jproject.services;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alldata.jproject.models.Usuario;
import com.alldata.jproject.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(userName)
                .orElseGet(() -> usuarioRepository.findByUsername(userName)
                        .orElseThrow(() -> new UsernameNotFoundException(
                                "Usuario no encontrado con username o email: "
                                        + userName)));

        return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                mapRoles(usuario.getRoles()));
    }

     private Collection<? extends GrantedAuthority> mapRoles(Set<String> roles) {
                return roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                                .collect(Collectors.toList());
        }

}
