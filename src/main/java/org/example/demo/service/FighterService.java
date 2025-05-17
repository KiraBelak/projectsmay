package org.example.demo.service;

import org.example.demo.domain.Fighter;
import org.example.demo.repository.FighterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FighterService {

    private final FighterRepository fighterRepository;

    @Autowired
    public FighterService(FighterRepository fighterRepository) {
        this.fighterRepository = fighterRepository;
    }

    public List<Fighter> getAllFighters() {
        return fighterRepository.findAll();
    }

    public Fighter addFighter(Fighter fighter) {
        return fighterRepository.save(fighter);
    }

    public Fighter getFighterById(Long id) {
        return fighterRepository.findById(id).orElse(null);
    }

}
