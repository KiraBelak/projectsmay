package com.curso.proyectofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.proyectofinal.dto.ArtistDTO;
import com.curso.proyectofinal.model.Artist;
import com.curso.proyectofinal.repository.ArtistRepository;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final NotificationService notificationService;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, NotificationService notificationService) {
        this.artistRepository = artistRepository;
        this.notificationService = notificationService;
    }

    public List<ArtistDTO> getAllArtists() {
        return artistRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ArtistDTO getArtistById(Long id) {
        Artist artist = null;
        try {
            artist = artistRepository.findById(id)
                    .orElseThrow(() -> new Exception("Artista no encontrado con id: " + id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertToDTO(artist);
    }

    public List<ArtistDTO> searchArtistsByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArtistDTO> getArtistsByCountry(String country) {
        return artistRepository.findByCountryIgnoreCase(country).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        Artist artist = convertToEntity(artistDTO);
        Artist savedArtist = artistRepository.save(artist);
        ArtistDTO savedArtistDTO = convertToDTO(savedArtist);

        // Enviar notificación
        notificationService.notifyNewArtist(savedArtistDTO);

        return savedArtistDTO;
    }

    public ArtistDTO updateArtist(Long id, ArtistDTO artistDTO) {
        Artist existingArtist = null;
        try {
            existingArtist = artistRepository.findById(id)
                    .orElseThrow(() -> new Exception("Artista no encontrado con id: " + id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        existingArtist.setName(artistDTO.getName());
        existingArtist.setBiography(artistDTO.getBiography());
        existingArtist.setCountry(artistDTO.getCountry());

        Artist updatedArtist = artistRepository.save(existingArtist);
        return convertToDTO(updatedArtist);
    }

    public void deleteArtist(Long id) throws Exception {
        if (!artistRepository.existsById(id)) {
            throw new Exception("Artista no encontrado con id: " + id);
        }
        artistRepository.deleteById(id);
    }

    private ArtistDTO convertToDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setName(artist.getName());
        dto.setBiography(artist.getBiography());
        dto.setCountry(artist.getCountry());
        return dto;
    }

    private Artist convertToEntity(ArtistDTO dto) {
        Artist artist = new Artist();
        artist.setId(dto.getId());
        artist.setName(dto.getName());
        artist.setBiography(dto.getBiography());
        artist.setCountry(dto.getCountry());
        return artist;
    }
}
