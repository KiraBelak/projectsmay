package com.curso.proyectofinal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.curso.proyectofinal.dto.AlbumDTO;
import com.curso.proyectofinal.service.AlbumService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        return ResponseEntity.ok(albumService.getAllAlbums());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.getAlbumById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AlbumDTO>> searchAlbums(@RequestParam(required = false) String title,
            @RequestParam(required = false) String genre) {
        if (title != null && !title.isEmpty()) {
            return ResponseEntity.ok(albumService.searchAlbumsByTitle(title));
        } else if (genre != null && !genre.isEmpty()) {
            return ResponseEntity.ok(albumService.getAlbumsByGenre(genre));
        } else {
            return ResponseEntity.ok(albumService.getAllAlbums());
        }
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(albumService.getAlbumsByDateRange(startDate, endDate));
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtist(@PathVariable Long artistId) {
        return ResponseEntity.ok(albumService.getAlbumsByArtist(artistId));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumDTO> createAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.createAlbum(albumDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Long id, @Valid @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(id, albumDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) throws Exception {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}
