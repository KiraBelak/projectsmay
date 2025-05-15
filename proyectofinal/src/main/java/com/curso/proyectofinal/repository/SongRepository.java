package com.curso.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.proyectofinal.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findByTitleContainingIgnoreCase(String title);

    List<Song> findByGenreIgnoreCase(String genre);

    List<Song> findByAlbumId(Long albumId);

    List<Song> findByAlbumArtistId(Long artistId);
}
