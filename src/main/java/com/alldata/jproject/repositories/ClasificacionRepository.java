package com.alldata.jproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alldata.jproject.models.Clasificacion;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Integer> {

}
