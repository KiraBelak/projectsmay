package com.curso.proyectofinal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.curso.proyectofinal.dto.ArtistDTO;
import com.curso.proyectofinal.exception.ResourceNotFoundException;
import com.curso.proyectofinal.model.Artist;
import com.curso.proyectofinal.repository.ArtistRepository;
import com.curso.proyectofinal.service.ArtistService;
import com.curso.proyectofinal.service.NotificationService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private ArtistService artistService;

    private Artist artist;
    private ArtistDTO artistDTO;

    @BeforeEach
    void setUp() {
        artist = new Artist();
        artist.setId(1L);
        artist.setName("Test Artist");
        artist.setBiography("Test Biography");
        artist.setCountry("Test Country");

        artistDTO = new ArtistDTO();
        artistDTO.setId(1L);
        artistDTO.setName("Test Artist");
        artistDTO.setBiography("Test Biography");
        artistDTO.setCountry("Test Country");
    }

    @Test
    void getAllArtists_ShouldReturnListOfArtistDTOs() {

        when(artistRepository.findAll()).thenReturn(Arrays.asList(artist));

        List<ArtistDTO> result = artistService.getAllArtists();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(artist.getId(), result.get(0).getId());
        assertEquals(artist.getName(), result.get(0).getName());
    }

    @Test
    void getArtistById_WithValidId_ShouldReturnArtistDTO() {

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));

        ArtistDTO result = artistService.getArtistById(1L);

        assertNotNull(result);
        assertEquals(artist.getId(), result.getId());
        assertEquals(artist.getName(), result.getName());
    }

    @Test
    void getArtistById_WithInvalidId_ShouldThrowResourceNotFoundException() {

        when(artistRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            artistService.getArtistById(999L);
        });
    }

    @Test
    void createArtist_ShouldReturnCreatedArtistDTO() {

        when(artistRepository.save(any(Artist.class))).thenReturn(artist);
        doNothing().when(notificationService).notifyNewArtist(any(ArtistDTO.class));

        ArtistDTO result = artistService.createArtist(artistDTO);

        assertNotNull(result);
        assertEquals(artist.getId(), result.getId());
        assertEquals(artist.getName(), result.getName());
        verify(notificationService, times(1)).notifyNewArtist(any(ArtistDTO.class));
    }

    @Test
    void updateArtist_WithValidId_ShouldReturnUpdatedArtistDTO() {

        when(artistRepository.findById(1L)).thenReturn(Optional.of(artist));
        when(artistRepository.save(any(Artist.class))).thenReturn(artist);

        ArtistDTO result = artistService.updateArtist(1L, artistDTO);

        assertNotNull(result);
        assertEquals(artist.getId(), result.getId());
        assertEquals(artist.getName(), result.getName());
    }

    @Test
    void deleteArtist_WithValidId_ShouldDeleteArtist() {

        when(artistRepository.existsById(1L)).thenReturn(true);
        doNothing().when(artistRepository).deleteById(1L);

        artistService.deleteArtist(1L);

        verify(artistRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteArtist_WithInvalidId_ShouldThrowResourceNotFoundException() {

        when(artistRepository.existsById(999L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            artistService.deleteArtist(999L);
        });
    }
}
