package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Accesory;

@Repository
public interface AccessoryRepository extends ProductRepository<Accesory> {

    List<Accesory> findByType(String type);

    List<Accesory> findByCompatibility(String compatibility);

    List<Accesory> findByBrand(String brand);
}
