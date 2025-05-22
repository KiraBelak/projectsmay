package org.example.demo.controller;

import jakarta.validation.Valid;
import org.example.demo.dto.FighterDto;
import org.example.demo.model.Fighter;
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
    public ResponseEntity<FighterDto> addFighter(@RequestBody @Valid FighterDto fighter) {
        //TODO: proper input DTO for fighter; use mapping library
        Fighter newFighter = fighterService.addFighter(FighterDto.toEntity(fighter));
        return new ResponseEntity<>(FighterDto.fromEntity(newFighter), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FighterDto> getFighterById(@PathVariable Long id) {
        Fighter fighter = fighterService.getFighterById(id);
        String nullString = null;
        nullString.trim();
        if (fighter != null) {
            return ResponseEntity.ok(FighterDto.fromEntity(fighter));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
