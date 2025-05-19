package com.curso.proyectofinal.config;

import com.curso.proyectofinal.model.Album;
import com.curso.proyectofinal.model.Artist;
import com.curso.proyectofinal.model.Song;
import com.curso.proyectofinal.model.User;
import com.curso.proyectofinal.repository.AlbumRepository;
import com.curso.proyectofinal.repository.ArtistRepository;
import com.curso.proyectofinal.repository.SongRepository;
import com.curso.proyectofinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Crear usuarios
        createUsers();

        // Crear artistas, álbumes y canciones
        createMusicData();
    }

    private void createUsers() {
        if (userRepository.count() == 0) {
            // Crear usuario administrador
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            Set<String> adminRoles = new HashSet<>();
            adminRoles.add("ADMIN");
            adminRoles.add("USER");
            admin.setRoles(adminRoles);
            userRepository.save(admin);

            // Crear usuario normal
            User user = new User();
            user.setUsername("user");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            Set<String> userRoles = new HashSet<>();
            userRoles.add("USER");
            user.setRoles(userRoles);
            userRepository.save(user);
        }
    }

    private void createMusicData() {
        if (artistRepository.count() == 0) {
            // Crear artistas
            Artist artist1 = new Artist();
            artist1.setName("Mathame");
            artist1.setBiography(
                    "Mathame es conocido profesionalmente como un dúo de DJ y productores, destacados dentro del género techno cinematográfico.");
            artist1.setCountry("Italia");
            artistRepository.save(artist1);

            Artist artist2 = new Artist();
            artist2.setName("Michael Jackson");
            artist2.setBiography("Michael Joseph Jackson fue un cantante, compositor y bailarín estadounidense.");
            artist2.setCountry("Estados Unidos");
            artistRepository.save(artist2);

            // Crear álbumes
            Album album1 = new Album();
            album1.setTitle("MEMO");
            album1.setReleaseDate(LocalDate.of(2023, 6, 30));
            album1.setGenre("Techno");
            album1.setArtist(artist1);
            albumRepository.save(album1);

            Album album2 = new Album();
            album2.setTitle("Thriller");
            album2.setReleaseDate(LocalDate.of(1982, 11, 30));
            album2.setGenre("Pop");
            album2.setArtist(artist2);
            albumRepository.save(album2);

            // Crear canciones
            Song song1 = new Song();
            song1.setTitle("Dance to Death");
            song1.setDurationInSeconds(354);
            song1.setGenre("Techno");
            song1.setAlbum(album1);
            songRepository.save(song1);

            Song song2 = new Song();
            song2.setTitle("Feel your ghost");
            song2.setDurationInSeconds(217);
            song2.setGenre("Techno");
            song2.setAlbum(album1);
            songRepository.save(song2);

            Song song3 = new Song();
            song3.setTitle("Thriller");
            song3.setDurationInSeconds(357);
            song3.setGenre("Pop");
            song3.setAlbum(album2);
            songRepository.save(song3);

            Song song4 = new Song();
            song4.setTitle("Beat It");
            song4.setDurationInSeconds(258);
            song4.setGenre("Pop Rock");
            song4.setAlbum(album2);
            songRepository.save(song4);
        }
    }
}
