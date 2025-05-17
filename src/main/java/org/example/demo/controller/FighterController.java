package org.example.demo.controller;

import org.example.demo.domain.Fighter;
import org.example.demo.service.FighterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fighters")
public class FighterController {

    private final FighterService fighterService;

    @Autowired
    public FighterController(FighterService fighterService) {
        this.fighterService = fighterService;
    }

    @GetMapping
    public ResponseEntity<List<Fighter>> getAllFighters() {
        List<Fighter> fighters = fighterService.getAllFighters();
        return ResponseEntity.ok(fighters);
    }

    @PostMapping
    public ResponseEntity<Fighter> addFighter(@RequestBody Fighter fighter) {
        Fighter newFighter = fighterService.addFighter(fighter);
        return new ResponseEntity<>(newFighter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fighter> getFighterById(@RequestBody Long id) {
        Fighter fighter = fighterService.getFighterById(id);
        if (fighter != null) {
            return ResponseEntity.ok(fighter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
