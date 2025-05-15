package com.curso.proyectofinal.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.proyectofinal.dto.SongDTO;
import com.curso.proyectofinal.model.Album;
import com.curso.proyectofinal.model.Song;
import com.curso.proyectofinal.repository.AlbumRepository;
import com.curso.proyectofinal.repository.SongRepository;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final NotificationService notificationService;

    @Autowired
    public SongService(SongRepository songRepository, AlbumRepository albumRepository,
            NotificationService notificationService) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.notificationService = notificationService;
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SongDTO getSongById(Long id) {
        Song song = null;
        try {
            song = songRepository.findById(id)
                    .orElseThrow(() -> new Exception("Canción no encontrada con id: " + id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertToDTO(song);
    }

    public List<SongDTO> searchSongsByTitle(String title) {
        return songRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByGenre(String genre) {
        return songRepository.findByGenreIgnoreCase(genre).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByAlbum(Long albumId) {
        return songRepository.findByAlbumId(albumId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<SongDTO> getSongsByArtist(Long artistId) {
        return songRepository.findByAlbumArtistId(artistId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SongDTO createSong(SongDTO songDTO) {
        Song song = convertToEntity(songDTO);
        Song savedSong = songRepository.save(song);
        SongDTO savedSongDTO = convertToDTO(savedSong);

        // Enviar notificación
        notificationService.notifyNewSong(savedSongDTO);

        return savedSongDTO;
    }

    public SongDTO updateSong(Long id, SongDTO songDTO) {
        Song existingSong = null;
        try {
            existingSong = songRepository.findById(id)
                    .orElseThrow(() -> new Exception("Canción no encontrada con id: " + id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        existingSong.setTitle(songDTO.getTitle());
        existingSong.setDurationInSeconds(songDTO.getDurationInSeconds());
        existingSong.setGenre(songDTO.getGenre());

        if (songDTO.getAlbumId() != null) {
            Album album = null;
            try {
                album = albumRepository.findById(songDTO.getAlbumId())
                        .orElseThrow(
                                () -> new Exception("Álbum no encontrado con id: " + songDTO.getAlbumId()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            existingSong.setAlbum(album);
        }

        Song updatedSong = songRepository.save(existingSong);
        return convertToDTO(updatedSong);
    }

    public void deleteSong(Long id) throws Exception {
        if (!songRepository.existsById(id)) {
            throw new Exception("Canción no encontrada con id: " + id);
        }
        songRepository.deleteById(id);
    }

    private SongDTO convertToDTO(Song song) {
        SongDTO dto = new SongDTO();
        dto.setId(song.getId());
        dto.setTitle(song.getTitle());
        dto.setDurationInSeconds(song.getDurationInSeconds());
        dto.setGenre(song.getGenre());

        if (song.getAlbum() != null) {
            dto.setAlbumId(song.getAlbum().getId());
            dto.setAlbumTitle(song.getAlbum().getTitle());

            if (song.getAlbum().getArtist() != null) {
                dto.setArtistId(song.getAlbum().getArtist().getId());
                dto.setArtistName(song.getAlbum().getArtist().getName());
            }
        }

        return dto;
    }

    private Song convertToEntity(SongDTO dto) {
        Song song = new Song();
        song.setId(dto.getId());
        song.setTitle(dto.getTitle());
        song.setDurationInSeconds(dto.getDurationInSeconds());
        song.setGenre(dto.getGenre());

        if (dto.getAlbumId() != null) {
            Album album = null;
            try {
                album = albumRepository.findById(dto.getAlbumId())
                        .orElseThrow(
                                () -> new Exception("Álbum no encontrado con id: " + dto.getAlbumId()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            song.setAlbum(album);
        }

        return song;
    }
}
