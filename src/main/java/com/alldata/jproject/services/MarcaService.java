package com.alldata.jproject.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.alldata.jproject.exception.ResourceNotFoundException;
import com.alldata.jproject.models.Clasificacion;
import com.alldata.jproject.models.Marca;
import com.alldata.jproject.modelsDTO.MarcaDTO;
import com.alldata.jproject.repositories.ClasificacionRepository;
import com.alldata.jproject.repositories.MarcaRepository;

@Service
public class MarcaService {

    private final MarcaRepository marcaRepository;
    private final ClasificacionRepository clasificacionRepository;

    public MarcaService(MarcaRepository _marcaRepository, ClasificacionRepository _clasificacionRepository) {
        this.marcaRepository = _marcaRepository;
        this.clasificacionRepository = _clasificacionRepository;
    }

    public MarcaDTO Save(MarcaDTO marcaDto) {
        Marca marca = new Marca();
        if (marcaDto.getClasificacion_id() != 0) {
            Clasificacion clasificacion = clasificacionRepository.findById(marcaDto.getClasificacion_id()).orElseThrow(
                    () -> new ResourceNotFoundException(
                            "Clasification no encontrada con id: " + marcaDto.getClasificacion_id()));
            marca.setClasificacion(clasificacion);
        }
        marca.setMarca_name(marcaDto.getMarca_name());
        marca.setDescripcion(marcaDto.getDescripcion());

        return convertToDto(marcaRepository.save(marca));
    }

    public List<MarcaDTO> Get() {
        return marcaRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MarcaDTO GetById(Integer id) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada con el id: " + id));
        return convertToDto(marca);
    }

    public MarcaDTO Update(Integer id, MarcaDTO marcaDto) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada con el id: " + id));
        marca.setMarca_name(marcaDto.getMarca_name());
        marca.setDescripcion(marcaDto.getDescripcion());

        if (marcaDto.getClasificacion_id() != 0) {
            Clasificacion clasificacion = clasificacionRepository.findById(marcaDto.getClasificacion_id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Clasification no encontrada con id: "
                                            + marcaDto.getClasificacion_id()));
            marca.setClasificacion(clasificacion);
        }
        return convertToDto(marcaRepository.save(marca));
    }

    public void Delete(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artista no encontrado con id: " + id);
        }
        marcaRepository.deleteById(id);
    }

    private MarcaDTO convertToDto(Marca marca) {
        MarcaDTO marcaDTO = new MarcaDTO();
        marcaDTO.setId(marca.getId());
        marcaDTO.setMarca_name(marca.getMarca_name());
        marcaDTO.setDescripcion(marca.getDescripcion());
        if (marca.getClasificacion() != null) {
            marcaDTO.setClasificacion_id(marca.getClasificacion().getId());
            marcaDTO.setClasificacion_name(marca.getClasificacion().getClasificacion_name());
        }
        return marcaDTO;
    }
}
