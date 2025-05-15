package com.curso.proyectofinal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.proyectofinal.dto.ArtistDTO;
import com.curso.proyectofinal.service.ArtistService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArtistDTO>> searchArtists(@RequestParam(required = false) String name,
            @RequestParam(required = false) String country) {
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(artistService.searchArtistsByName(name));
        } else if (country != null && !country.isEmpty()) {
            return ResponseEntity.ok(artistService.getArtistsByCountry(country));
        } else {
            return ResponseEntity.ok(artistService.getAllArtists());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArtistDTO> createArtist(@Valid @RequestBody ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.createArtist(artistDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Long id, @Valid @RequestBody ArtistDTO artistDTO) {
        return ResponseEntity.ok(artistService.updateArtist(id, artistDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) throws Exception {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
