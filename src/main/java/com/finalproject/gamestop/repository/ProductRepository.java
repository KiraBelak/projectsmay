package com.finalproject.gamestop.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Product;

@Repository
public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {

    List<T> findByNameContainingIgnoreCase(String name);

    List<T> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    @Query("SELECT p FROM #{entityName} p ORDER BY p.rating DESC")
    List<T> findAllByOrderrByRatingDesc();
}
