package com.alldata.jproject.database.repositories;

import org.springframework.stereotype.Repository;
import com.alldata.jproject.database.models.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

}
