package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Videogame;

@Repository
public interface VideogameRepository extends ProductRepository<Videogame> {

    List<Videogame> findByGenre(String genre);

    List<Videogame> findByPlatform(String platform);

    List<Videogame> findByDeveloper(String developer);

}
