package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Product;

@Repository
public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {

    List<T> findByNameContainingIgnoreCase(String name);

    List<T> findByPriceBetween(double minPrice, double maxPrice);

    List<T> findAllByOrderByRatingDesc();
}
