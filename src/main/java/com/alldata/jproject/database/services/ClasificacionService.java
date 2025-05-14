package com.alldata.jproject.database.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alldata.jproject.database.models.Clasificacion;
import com.alldata.jproject.database.repositories.ClasificacionRepository;

@Service
public class ClasificacionService {

    private final ClasificacionRepository clasificacionRepository;

    public ClasificacionService(ClasificacionRepository _clasificacionRepository) {
        this.clasificacionRepository = _clasificacionRepository;
    }

    public Clasificacion Save(Clasificacion producto) {
        return clasificacionRepository.save(producto);
    }

    public List<Clasificacion> Get() {
        return clasificacionRepository.findAll();
    }

    public Optional<Clasificacion> GetById(Long id) {
        return clasificacionRepository.findById(id);
    }

    public ResponseEntity<Clasificacion> Update(Long id, Clasificacion nuevaClasificacion) {
        return clasificacionRepository.findById(id)
                .map(clasificacion -> {
                    clasificacion.setClasificacion_name(nuevaClasificacion.getClasificacion_name());
                    clasificacion.setDescripcion(nuevaClasificacion.getDescripcion());
                    clasificacionRepository.save(clasificacion);
                    return ResponseEntity.ok(clasificacion);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> Delete(Long id) {
        return clasificacionRepository.findById(id).map(producto -> {
            clasificacionRepository.delete(producto);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
