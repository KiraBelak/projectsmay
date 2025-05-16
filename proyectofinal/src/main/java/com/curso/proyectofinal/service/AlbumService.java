package com.curso.proyectofinal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.proyectofinal.dto.AlbumDTO;
import com.curso.proyectofinal.exception.ResourceNotFoundException;
import com.curso.proyectofinal.model.Album;
import com.curso.proyectofinal.model.Artist;
import com.curso.proyectofinal.repository.AlbumRepository;
import com.curso.proyectofinal.repository.ArtistRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final NotificationService notificationService;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository,
            NotificationService notificationService) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.notificationService = notificationService;
    }

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Álbum no encontrado con id: " + id));
        return convertToDTO(album);
    }

    public List<AlbumDTO> searchAlbumsByTitle(String title) {
        return albumRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByGenre(String genre) {
        return albumRepository.findByGenreIgnoreCase(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByDateRange(LocalDate startDate, LocalDate endDate) {
        return albumRepository.findByReleaseDateBetween(startDate, endDate).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AlbumDTO> getAlbumsByArtist(Long artistId) {
        return albumRepository.findByArtistId(artistId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        Album album = convertToEntity(albumDTO);
        Album savedAlbum = albumRepository.save(album);
        AlbumDTO savedAlbumDTO = convertToDTO(savedAlbum);

        // Enviar notificación
        notificationService.notifyNewAlbum(savedAlbumDTO);

        return savedAlbumDTO;
    }

    public AlbumDTO updateAlbum(Long id, AlbumDTO albumDTO) {
        Album existingAlbum = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Álbum no encontrado con id: " + id));

        existingAlbum.setTitle(albumDTO.getTitle());
        existingAlbum.setReleaseDate(albumDTO.getReleaseDate());
        existingAlbum.setGenre(albumDTO.getGenre());

        if (albumDTO.getArtistId() != null) {
            Artist artist = artistRepository.findById(albumDTO.getArtistId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Artista no encontrado con id: " + albumDTO.getArtistId()));
            existingAlbum.setArtist(artist);
        }

        Album updatedAlbum = albumRepository.save(existingAlbum);
        return convertToDTO(updatedAlbum);
    }

    public void deleteAlbum(Long id) {
        if (!albumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Álbum no encontrado con id: " + id);
        }
        albumRepository.deleteById(id);
    }

    private AlbumDTO convertToDTO(Album album) {
        AlbumDTO dto = new AlbumDTO();
        dto.setId(album.getId());
        dto.setTitle(album.getTitle());
        dto.setReleaseDate(album.getReleaseDate());
        dto.setGenre(album.getGenre());

        if (album.getArtist() != null) {
            dto.setArtistId(album.getArtist().getId());
            dto.setArtistName(album.getArtist().getName());
        }

        return dto;
    }

    private Album convertToEntity(AlbumDTO dto) {
        Album album = new Album();
        album.setId(dto.getId());
        album.setTitle(dto.getTitle());
        album.setReleaseDate(dto.getReleaseDate());
        album.setGenre(dto.getGenre());

        if (dto.getArtistId() != null) {
            Artist artist = artistRepository.findById(dto.getArtistId())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Artista no encontrado con id: " + dto.getArtistId()));
            album.setArtist(artist);
        }

        return album;
    }
}
