package com.alldata.jproject.repository;

import com.alldata.jproject.model.Producto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductoRepositoryTest {

    @Autowired
    ProductoRepository productoRepository;

    @Test
    public void ProductRepository_SaveAll_ReturnSaved_Product(){

        //Arrange

        Producto producto = Producto.builder().nombre("Paleta de chocolate")
                .precio(150)
                .imagen("choco.jpg")
                .description("5 paletas de helado")
                .cantidad(15).
                calificacion(5)
                .build();

        //Act
        Producto savedProduct= productoRepository.save(producto);

        //Assert
        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);
    }
}
