package com.alldata.jproject.repository;

import com.alldata.jproject.model.Orden;
import com.alldata.jproject.model.Usuario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrdenRepositoryTests {

    @Autowired
    private OrdenRepository ordenRepository;


    @Test
    public void OrderRepository_SaveAll_ReturnSavedOrder(){

        Date date = new Date();
        Usuario usuario = Usuario.builder().username("Miguel")
                .email("Miguel.Jaramillo@Autozone.com")
                .tipo("ADMIN")
                .direccion("Chiahuahua Mexico")
                .password("pass")
                .telefono("614123456").build();
        //Arrange
        Orden orden = Orden.builder().total(500).fechaCreacion(date).fechaRecibida(date).usuario(usuario).numero("000001").build();

        //Act
        Orden saveOrder = ordenRepository.save(orden);

        //Assert
        Assertions.assertThat(saveOrder).isNotNull();
        Assertions.assertThat(saveOrder.getId()).isGreaterThan(0);
    }
}
