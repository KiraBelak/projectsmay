package com.alldata.jproject.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alldata.jproject.database.models.Marca;
import com.alldata.jproject.database.repositories.MarcaRepository;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository _marcaRepository) {
        this.marcaRepository = _marcaRepository;
    }

    public Marca Save(Marca producto) {
        return marcaRepository.save(producto);
    }

    public List<Marca> Get() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> GetById(Integer id) {
        return marcaRepository.findById(id);
    }

    public ResponseEntity<Marca> Update(Integer id, Marca nuevaMarca) {
        return marcaRepository.findById(id)
                .map(Marca -> {
                    Marca.setMarca_name(nuevaMarca.getMarca_name());
                    Marca.setDescripcion(nuevaMarca.getDescripcion());
                    marcaRepository.save(Marca);
                    return ResponseEntity.ok(Marca);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> Delete(Integer id) {
        return marcaRepository.findById(id).map(producto -> {
            marcaRepository.delete(producto);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
