package com.omar.proyecto.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.omar.proyecto.models.Character;


@RestController
public class CharacterController {
  
	private final AtomicLong counter = new AtomicLong();

    @GetMapping("/char")
    public Character getCharacters() {
        return new Character(counter.incrementAndGet(), "terry", 40, null);
    }

    @PostMapping("/char")
    public ResponseEntity<Character> newCharacter(@RequestBody Character newCharacter){
        //long id = 55;
        //newCharacter.id(id);
        return new ResponseEntity<>(newCharacter, HttpStatus.CREATED);
    }
    
}
