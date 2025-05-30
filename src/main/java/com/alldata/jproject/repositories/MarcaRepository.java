package com.alldata.jproject.repositories;

import org.springframework.stereotype.Repository;

import com.alldata.jproject.models.Marca;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
