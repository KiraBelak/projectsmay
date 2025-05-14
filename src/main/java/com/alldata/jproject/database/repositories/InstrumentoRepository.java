package com.alldata.jproject.database.repositories;

import org.springframework.stereotype.Repository;
import com.alldata.jproject.database.models.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InstrumentoRepository extends JpaRepository<Instrumento, Integer> {

}
