package com.finalproject.gamestop.service;

import com.finalproject.gamestop.dto.VideogameDTO;
import java.util.List;

public interface VideogameService {
    List<VideogameDTO> getAllVideogameDTOs();

    VideogameDTO getVideogameDTOById(Long id);

    VideogameDTO createVideogame(VideogameDTO videogameDTO);

    VideogameDTO updateVideogame(Long id, VideogameDTO videogameDTO);

    void deleteVideogame(Long id);

    List<VideogameDTO> findVideogameDTOByGenre(String genre);

    List<VideogameDTO> findVideogameDTOByDeveloper(String developer);

    List<VideogameDTO> findVideogameDTOByPlatform(String platform);

    List<VideogameDTO> findVideogameDTOByPriceRange(double min, double max);

    List<VideogameDTO> findVideogameDTOByPopularity();

    List<VideogameDTO> searchVideogameDTOByName(String name);
}
