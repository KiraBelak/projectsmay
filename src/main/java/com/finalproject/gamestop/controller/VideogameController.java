package com.finalproject.gamestop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finalproject.gamestop.dto.VideogameDTO;
import com.finalproject.gamestop.service.VideogameService;

@RestController
@RequestMapping("/api/videogames")
public class VideogameController {

    @Autowired
    private VideogameService videogameService;

    @GetMapping
    public ResponseEntity<List<VideogameDTO>> getAllVideogames() {
        List<VideogameDTO> videogames = videogameService.getAllVideogameDTOs();
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VideogameDTO> getVideogameById(@PathVariable Long id) {
        VideogameDTO videogame = videogameService.getVideogameDTOById(id);
        return new ResponseEntity<>(videogame, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VideogameDTO> createVideogame(@RequestBody VideogameDTO videogameDTO) {
        VideogameDTO newVideogame = videogameService.createVideogame(videogameDTO);
        return new ResponseEntity<>(newVideogame, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VideogameDTO> updateVideogame(@PathVariable Long id, @RequestBody VideogameDTO videogameDTO) {
        VideogameDTO updatedVideogame = videogameService.updateVideogame(id, videogameDTO);
        return new ResponseEntity<>(updatedVideogame, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVideogame(@PathVariable Long id) {
        videogameService.deleteVideogame(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Videogame deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<VideogameDTO>> getVideogamesByGenre(@PathVariable String genre) {
        List<VideogameDTO> videogames = videogameService.findVideogameDTOByGenre(genre);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/developer/{developer}")
    public ResponseEntity<List<VideogameDTO>> getVideogamesByDeveloper(@PathVariable String developer) {
        List<VideogameDTO> videogames = videogameService.findVideogameDTOByDeveloper(developer);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/platform/{platform}")
    public ResponseEntity<List<VideogameDTO>> getVideogamesByPlatform(@PathVariable String platform) {
        List<VideogameDTO> videogames = videogameService.findVideogameDTOByPlatform(platform);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<VideogameDTO>> findByPriceRange(
            @RequestParam(value = "min") double min,
            @RequestParam(value = "max") double max) {
        return ResponseEntity.ok(videogameService.findVideogameDTOByPriceRange(min, max));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<VideogameDTO>> getPopularVideogames() {
        List<VideogameDTO> videogames = videogameService.findVideogameDTOByPopularity();
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VideogameDTO>> searchVideogamesByName(@RequestParam String name) {
        List<VideogameDTO> videogames = videogameService.searchVideogameDTOByName(name);
        return new ResponseEntity<>(videogames, HttpStatus.OK);
    }
}
