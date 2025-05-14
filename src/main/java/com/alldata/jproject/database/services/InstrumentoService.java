package com.alldata.jproject.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alldata.jproject.database.models.Instrumento;
import com.alldata.jproject.database.repositories.InstrumentoRepository;

@Service
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;

    public InstrumentoService(InstrumentoRepository _instrumentoRepository) {
        this.instrumentoRepository = _instrumentoRepository;
    }

    public Instrumento Save(Instrumento producto) {
        return instrumentoRepository.save(producto);
    }

    public List<Instrumento> Get() {
        return instrumentoRepository.findAll();
    }

    public Optional<Instrumento> GetById(Integer id) {
        return instrumentoRepository.findById(id);
    }

    public ResponseEntity<Instrumento> Update(Integer id, Instrumento nuevaInstrumento) {
        return instrumentoRepository.findById(id)
                .map(Instrumento -> {
                    Instrumento.setInstrumento_name(nuevaInstrumento.getInstrumento_name());
                    Instrumento.setDescripcion(nuevaInstrumento.getDescripcion());
                    Instrumento.setMarca_id(nuevaInstrumento.getMarca_id());
                    Instrumento.setStock(nuevaInstrumento.getStock());
                    Instrumento.setImagen(nuevaInstrumento.getImagen());
                    instrumentoRepository.save(Instrumento);
                    return ResponseEntity.ok(Instrumento);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> Delete(Integer id) {
        return instrumentoRepository.findById(id).map(producto -> {
            instrumentoRepository.delete(producto);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
