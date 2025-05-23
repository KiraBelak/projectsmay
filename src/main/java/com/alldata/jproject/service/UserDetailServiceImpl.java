package com.alldata.jproject.service;

import com.alldata.jproject.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    @Lazy
    private UsuarioService usuarioService;
    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCrypt;
    @Autowired
    @Lazy
    HttpSession session;

    private Logger Logg = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logg.info("Este es el username : ");
        Optional<Usuario> optionalUser=usuarioService.findByUsername(username);
        if(optionalUser.isPresent()){
            Logg.info("Esto es el id del usuario : {}",optionalUser.get().getId());
            session.setAttribute("idusuario",optionalUser.get().getId());
            Usuario usuario = optionalUser.get();
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
        }else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
