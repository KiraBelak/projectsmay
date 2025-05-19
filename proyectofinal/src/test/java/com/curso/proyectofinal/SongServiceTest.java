package com.curso.proyectofinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.proyectofinal.dto.SongDTO;
import com.curso.proyectofinal.exception.ResourceNotFoundException;
import com.curso.proyectofinal.model.Album;
import com.curso.proyectofinal.model.Artist;
import com.curso.proyectofinal.model.Song;
import com.curso.proyectofinal.repository.AlbumRepository;
import com.curso.proyectofinal.repository.SongRepository;
import com.curso.proyectofinal.service.NotificationService;
import com.curso.proyectofinal.service.SongService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private SongService songService;

    private Song song;
    private SongDTO songDTO;
    private Album album;
    private Artist artist;

    @BeforeEach
    void setUp() {
        artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");
        artist.setBiography("Test Biography");
        artist.setCountry("Test Country");

        album = new Album();
        album.setId(1L);
        album.setTitle("Test Album");
        album.setReleaseDate(LocalDate.of(2024, 1, 1));
        album.setArtist(artist);

        song = new Song();
        song.setId(1L);
        song.setTitle("Test Song");
        song.setDurationInSeconds(180);
        song.setGenre("Rock");
        song.setAlbum(album);

        songDTO = new SongDTO();
        songDTO.setId(1L);
        songDTO.setTitle("Test Song");
        songDTO.setDurationInSeconds(180);
        songDTO.setGenre("Rock");
        songDTO.setAlbumId(1L);
        songDTO.setAlbumTitle("Test Album");
        songDTO.setArtistId(1L);
        songDTO.setArtistName("Test Artist");
    }

    @Test
    void getAllSongs_ShouldReturnListOfSongDTOs() {
        when(songRepository.findAll()).thenReturn(Arrays.asList(song));

        List<SongDTO> result = songService.getAllSongs();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());
        assertEquals(song.getTitle(), result.get(0).getTitle());
        assertEquals(song.getGenre(), result.get(0).getGenre());
        assertEquals(song.getDurationInSeconds(), result.get(0).getDurationInSeconds());
        assertEquals(album.getId(), result.get(0).getAlbumId());
        assertEquals(album.getTitle(), result.get(0).getAlbumTitle());
        assertEquals(artist.getId(), result.get(0).getArtistId());
        assertEquals(artist.getName(), result.get(0).getArtistName());
    }

    @Test
    void getSongById_WithValidId_ShouldReturnSongDTO() {
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));

        SongDTO result = songService.getSongById(1L);

        assertNotNull(result);
        assertEquals(song.getId(), result.getId());
        assertEquals(song.getTitle(), result.getTitle());
        assertEquals(song.getGenre(), result.getGenre());
        assertEquals(song.getDurationInSeconds(), result.getDurationInSeconds());
        assertEquals(album.getId(), result.getAlbumId());
        assertEquals(album.getTitle(), result.getAlbumTitle());
        assertEquals(artist.getId(), result.getArtistId());
        assertEquals(artist.getName(), result.getArtistName());
    }

    @Test
    void getSongById_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(songRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            songService.getSongById(999L);
        });
    }

    @Test
    void searchSongsByTitle_ShouldReturnMatchingSongs() {
        when(songRepository.findByTitleContainingIgnoreCase("Test")).thenReturn(Arrays.asList(song));

        List<SongDTO> result = songService.searchSongsByTitle("Test");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());
        assertEquals(song.getTitle(), result.get(0).getTitle());
    }

    @Test
    void getSongsByGenre_ShouldReturnSongsWithMatchingGenre() {
        when(songRepository.findByGenreIgnoreCase("Rock")).thenReturn(Arrays.asList(song));

        List<SongDTO> result = songService.getSongsByGenre("Rock");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());
        assertEquals(song.getTitle(), result.get(0).getTitle());
        assertEquals("Rock", result.get(0).getGenre());
    }

    @Test
    void getSongsByAlbum_ShouldReturnSongsFromSpecificAlbum() {
        when(songRepository.findByAlbumId(1L)).thenReturn(Arrays.asList(song));

        List<SongDTO> result = songService.getSongsByAlbum(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());
        assertEquals(song.getTitle(), result.get(0).getTitle());
        assertEquals(album.getId(), result.get(0).getAlbumId());
    }

    @Test
    void getSongsByArtist_ShouldReturnSongsFromSpecificArtist() {
        when(songRepository.findByAlbumArtistId(1L)).thenReturn(Arrays.asList(song));

        List<SongDTO> result = songService.getSongsByArtist(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(song.getId(), result.get(0).getId());
        assertEquals(song.getTitle(), result.get(0).getTitle());
        assertEquals(artist.getId(), result.get(0).getArtistId());
    }

    @Test
    void createSong_ShouldReturnCreatedSongDTO() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(songRepository.save(any(Song.class))).thenReturn(song);
        doNothing().when(notificationService).notifyNewSong(any(SongDTO.class));

        SongDTO result = songService.createSong(songDTO);

        assertNotNull(result);
        assertEquals(song.getId(), result.getId());
        assertEquals(song.getTitle(), result.getTitle());
        assertEquals(song.getGenre(), result.getGenre());
        assertEquals(song.getDurationInSeconds(), result.getDurationInSeconds());

        verify(notificationService, times(1)).notifyNewSong(any(SongDTO.class));
    }

    @Test
    void createSong_WithInvalidAlbumId_ShouldThrowResourceNotFoundException() {
        when(albumRepository.findById(999L)).thenReturn(Optional.empty());

        songDTO.setAlbumId(999L);

        assertThrows(ResourceNotFoundException.class, () -> {
            songService.createSong(songDTO);
        });
    }

    @Test
    void updateSong_WithValidId_ShouldReturnUpdatedSongDTO() {
        when(songRepository.findById(1L)).thenReturn(Optional.of(song));
        when(albumRepository.findById(1L)).thenReturn(Optional.of(album));
        when(songRepository.save(any(Song.class))).thenReturn(song);

        SongDTO result = songService.updateSong(1L, songDTO);

        assertNotNull(result);
        assertEquals(song.getId(), result.getId());
        assertEquals(song.getTitle(), result.getTitle());
        assertEquals(song.getGenre(), result.getGenre());
        assertEquals(song.getDurationInSeconds(), result.getDurationInSeconds());
    }

    @Test
    void updateSong_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(songRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            songService.updateSong(999L, songDTO);
        });
    }

    @Test
    void deleteSong_WithValidId_ShouldDeleteSong() {
        when(songRepository.existsById(1L)).thenReturn(true);
        doNothing().when(songRepository).deleteById(1L);

        songService.deleteSong(1L);

        verify(songRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSong_WithInvalidId_ShouldThrowResourceNotFoundException() {
        when(songRepository.existsById(999L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            songService.deleteSong(999L);
        });
    }
}
