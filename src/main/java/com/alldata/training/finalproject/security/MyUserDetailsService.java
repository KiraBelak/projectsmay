package com.alldata.training.finalproject.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        //TODO Get user from DB
        if ("admin".equals(username)) {
            return new User(
                "admin",
                //"{noop}admin", // {noop} indica que la contraseña no está encriptada
                "admin",
                Collections.singleton(() -> "ROLE_ADMIN")
            );
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
    }
}
