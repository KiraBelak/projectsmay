package com.alldata.jproject.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.alldata.jproject.exception.ResourceNotFoundException;
import com.alldata.jproject.models.Clasificacion;
import com.alldata.jproject.modelsDTO.ClasificacionDTO;
import com.alldata.jproject.repositories.ClasificacionRepository;

@Service
public class ClasificacionService {

    private final ClasificacionRepository clasificacionRepository;

    public ClasificacionService(ClasificacionRepository _clasificacionRepository) {
        this.clasificacionRepository = _clasificacionRepository;
    }

    public Clasificacion Save(Clasificacion clasificacion) {
        return clasificacionRepository.save(clasificacion);
    }

    public List<ClasificacionDTO> Get() {
        return clasificacionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ClasificacionDTO GetById(Integer id) {
        Clasificacion clasificacion = clasificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Clasificacion no encontrada con el id: " + id));
        return convertToDto(clasificacion);
    }

    public ResponseEntity<Clasificacion> Update(Integer id, Clasificacion nuevaClasificacion) {
        return clasificacionRepository.findById(id)
                .map(clasificacion -> {
                    clasificacion.setClasificacion_name(nuevaClasificacion.getClasificacion_name());
                    clasificacion.setDescripcion(nuevaClasificacion.getDescripcion());
                    clasificacionRepository.save(clasificacion);
                    return ResponseEntity.ok(clasificacion);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void Delete(Integer id) {
        if (!clasificacionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artista no encontrado con id: " + id);
        }
        clasificacionRepository.deleteById(id);
    }
    private ClasificacionDTO convertToDto(Clasificacion clasificacion) {
        ClasificacionDTO clasificacionDTO = new ClasificacionDTO();
        clasificacionDTO.setId(clasificacion.getId());
        clasificacionDTO.setClasificacion_name(clasificacion.getClasificacion_name());
        clasificacionDTO.setDescripcion(clasificacion.getDescripcion());
        return clasificacionDTO;
    }
}
