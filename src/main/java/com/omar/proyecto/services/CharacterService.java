package com.omar.proyecto.services;

import com.omar.proyecto.models.Character;
import com.omar.proyecto.repository.CharacterRepository;
import com.omar.proyecto.dto.CharacterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    public Character saveCharacterDTO(CharacterDTO dto) {
        Character character = new Character();
        character.setName(dto.getName());
        character.setAge(dto.getAge());
        character.setMovements(dto.getMovements());
        Character saved = characterRepository.save(character);
        messagingTemplate.convertAndSend("/topic/characters", saved);
        return saved;
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}
