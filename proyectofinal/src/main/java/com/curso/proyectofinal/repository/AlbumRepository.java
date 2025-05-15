package com.curso.proyectofinal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.proyectofinal.model.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByTitleContainingIgnoreCase(String title);

    List<Album> findByGenreIgnoreCase(String genre);

    List<Album> findByReleaseDateBetween(LocalDate start, LocalDate end);

    List<Album> findByArtistId(Long artistId);
}
