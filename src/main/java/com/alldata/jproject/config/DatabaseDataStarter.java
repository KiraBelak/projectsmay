package com.alldata.jproject.config;

import com.alldata.jproject.entities.Product;
import com.alldata.jproject.entities.User;
import com.alldata.jproject.repositories.ProductsRepository;
import com.alldata.jproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseDataStarter implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        setUsers();
        setProducts();
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

    public void setProducts(){
        if(productsRepository.findAllProducts().isEmpty()){
            Product gpu = new Product();
            Product cpu = new Product();
            Product ram = new Product();

            gpu.setName("RTX-5090");
            gpu.setPrice((long)8000);

            cpu.setName("Ryzen-7");
            cpu.setPrice((long)10000);

            ram.setName("Kingston");
            ram.setPrice((long)3000);

            productsRepository.save(gpu);
            productsRepository.save(cpu);
            productsRepository.save(ram);
        }
    }
}
