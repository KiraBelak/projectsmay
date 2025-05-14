package com.alldata.jproject.database.repositories;

import org.springframework.stereotype.Repository;
import com.alldata.jproject.database.models.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
