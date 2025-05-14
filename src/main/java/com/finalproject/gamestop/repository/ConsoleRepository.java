package com.finalproject.gamestop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.finalproject.gamestop.model.Console;

@Repository
public interface ConsoleRepository extends ProductRepository<Console> {

    List<Console> findByManufacturer(String manufacturer);

    List<Console> findByGeneration(String generation);
}
