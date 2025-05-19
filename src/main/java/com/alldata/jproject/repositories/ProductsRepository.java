package com.alldata.jproject.repositories;

import com.alldata.jproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM PRODUCTS", nativeQuery = true)
    List<Product> findAllProducts();
}
