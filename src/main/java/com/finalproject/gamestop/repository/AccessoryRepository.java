package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Accessory;

@Repository
public interface AccessoryRepository extends ProductRepository<Accessory> {

    List<Accessory> findByType(String type);

    List<Accessory> findByCompatibility(String compatibility);

    List<Accessory> findByBrand(String brand);
}
