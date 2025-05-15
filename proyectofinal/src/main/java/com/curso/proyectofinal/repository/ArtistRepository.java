package com.curso.proyectofinal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.proyectofinal.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByNameContainingIgnoreCase(String name);

    List<Artist> findByCountryIgnoreCase(String country);
}
