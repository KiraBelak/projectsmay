package com.finalproject.gamestop.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalproject.gamestop.dto.VideogameDTO;
import com.finalproject.gamestop.exception.ResourceNotFoundException;
import com.finalproject.gamestop.mapper.ProductMapper;
import com.finalproject.gamestop.model.Videogame;
import com.finalproject.gamestop.repository.VideogameRepository;
import com.finalproject.gamestop.service.VideogameService;

@Service
public class VideogameServiceImpl implements VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<VideogameDTO> getAllVideogameDTOs() {
        return videogameRepository.findAll().stream()
                .filter(v -> v.getStock() > 0)
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VideogameDTO getVideogameDTOById(Long id) {
        Videogame videogame = videogameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Videogame not found with id: " + id));
        return productMapper.videogameToVideogameDTO(videogame);
    }

    @Override
    public VideogameDTO createVideogame(VideogameDTO videogameDTO) {
        Videogame videogame = productMapper.videogameDTOToVideogame(videogameDTO);
        Videogame savedVideogame = videogameRepository.save(videogame);
        return productMapper.videogameToVideogameDTO(savedVideogame);
    }

    @Override
    public VideogameDTO updateVideogame(Long id, VideogameDTO videogameDTO) {
        Videogame existingVideogame = videogameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Videogame not found with id: " + id));

        if (videogameDTO.getName() != null)
            existingVideogame.setName(videogameDTO.getName());
        if (videogameDTO.getDescription() != null)
            existingVideogame.setDescription(videogameDTO.getDescription());
        if (videogameDTO.getPrice() != null)
            existingVideogame.setPrice(videogameDTO.getPrice());
        if (videogameDTO.getStock() != null)
            existingVideogame.setStock(videogameDTO.getStock());
        if (videogameDTO.getGenre() != null)
            existingVideogame.setGenre(videogameDTO.getGenre());
        if (videogameDTO.getDeveloper() != null)
            existingVideogame.setDeveloper(videogameDTO.getDeveloper());
        if (videogameDTO.getPublisher() != null)
            existingVideogame.setPublisher(videogameDTO.getPublisher());
        if (videogameDTO.getReleaseYear() != null)
            existingVideogame.setReleaseYear(videogameDTO.getReleaseYear());
        if (videogameDTO.getPlatform() != null)
            existingVideogame.setPlatform(videogameDTO.getPlatform());

        Videogame updatedVideogame = videogameRepository.save(existingVideogame);
        return productMapper.videogameToVideogameDTO(updatedVideogame);
    }

    @Override
    public void deleteVideogame(Long id) {
        Videogame videogame = videogameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Videogame not found with id: " + id));
        videogameRepository.delete(videogame);
    }

    @Override
    public List<VideogameDTO> findVideogameDTOByGenre(String genre) {
        return videogameRepository.findByGenre(genre).stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideogameDTO> findVideogameDTOByDeveloper(String developer) {
        return videogameRepository.findByDeveloper(developer).stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideogameDTO> findVideogameDTOByPlatform(String platform) {
        return videogameRepository.findByPlatform(platform).stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideogameDTO> findVideogameDTOByPriceRange(double minPrice, double maxPrice) {
        return videogameRepository.findByPriceBetween(minPrice, maxPrice).stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideogameDTO> findVideogameDTOByPopularity() {
        return videogameRepository.findAllByOrderByRatingDesc().stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideogameDTO> searchVideogameDTOByName(String name) {
        return videogameRepository.findByNameContainingIgnoreCase(name).stream()
                .map(productMapper::videogameToVideogameDTO)
                .collect(Collectors.toList());
    }
}
