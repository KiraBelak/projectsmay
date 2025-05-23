package com.alldata.jproject.config;

import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseDataStarter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        setUsers();
    }

    public void setUsers(){
        if(userRepository.findAll().isEmpty()){
            User userAdmin = new User();

            userAdmin.setEmail("admin@dominio.com");
            userAdmin.setName("Ivan");
            userAdmin.setPassword(passwordEncoder.encode("root123"));
            Set<String> roles = new HashSet<>();
            roles.add("ADMIN");
            roles.add("USER");
            userAdmin.setRoles(roles);
            userRepository.save(userAdmin);
        }
    }
}
