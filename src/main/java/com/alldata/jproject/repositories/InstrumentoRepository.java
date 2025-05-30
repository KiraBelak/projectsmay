package com.alldata.jproject.repositories;

import org.springframework.stereotype.Repository;

import com.alldata.jproject.models.Instrumento;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {

}
