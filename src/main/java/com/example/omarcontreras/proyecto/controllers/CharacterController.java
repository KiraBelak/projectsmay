package com.example.omarcontreras.proyecto.controllers;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class CharacterController {
  


    @GetMapping("/")
    public String getCharacters() {
        return new String("HOLAAAAAAAAAAAAA");
    }
    
}
