package com.alldata.jproject.repository;

import com.alldata.jproject.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void UsuarioRepository_SaveAll_ReturnSaved_User(){

        //Arrange

        Usuario usuario = Usuario.builder().username("Miguel")
                .email("Miguel.Jaramillo@Autozone.com")
                .tipo("ADMIN")
                .direccion("Chiahuahua Mexico")
                .password("pass")
                .telefono("614123456").build();

        //Act
        Usuario savedUser= usuarioRepository.save(usuario);

        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
