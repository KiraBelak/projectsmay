package com.alldata.jproject.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.alldata.jproject.exception.ResourceNotFoundException;
import com.alldata.jproject.models.Instrumento;
import com.alldata.jproject.models.Marca;
import com.alldata.jproject.modelsDTO.InstrumentoDTO;
import com.alldata.jproject.repositories.InstrumentoRepository;
import com.alldata.jproject.repositories.MarcaRepository;

@Service
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;
    private final MarcaRepository marcaRepository;

    public InstrumentoService(InstrumentoRepository _instrumentoRepository, MarcaRepository _marcaRepository) {
        this.instrumentoRepository = _instrumentoRepository;
        this.marcaRepository = _marcaRepository;
    }

    public InstrumentoDTO Save(InstrumentoDTO instrumentoDTO) {
        Instrumento instrumento = new Instrumento();
        instrumento.setInstrumento_name(instrumentoDTO.getInstrumento_name());
        instrumento.setDescripcion(instrumentoDTO.getDescripcion());
        instrumento.setStock(instrumentoDTO.getStock());
        instrumento.setImagen(instrumentoDTO.getImagen());
        instrumento.setCreated_at(new Date());
        instrumento.setPrecio(instrumentoDTO.getPrecio());
        if (instrumentoDTO.getMarca_id() != 0) {
            Marca marca = marcaRepository.findById(instrumentoDTO.getMarca_id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Clasification no encontrada con id: "
                                            + instrumentoDTO.getMarca_id()));
            instrumento.setMarca(marca);
        }
        return convertToDto(instrumentoRepository.save(instrumento));
    }

    public List<InstrumentoDTO> Get() {
        return instrumentoRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public InstrumentoDTO GetById(Integer id) {
        Instrumento instrumento = instrumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instrumento no encontrada con el id: " + id));
        return convertToDto(instrumento);
    }

    public InstrumentoDTO Update(Integer id, InstrumentoDTO nuevaInstrumento) {
        Instrumento instrumento = instrumentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instrumento no encontrada con el id: " + id));
        instrumento.setInstrumento_name(nuevaInstrumento.getInstrumento_name());
        instrumento.setDescripcion(nuevaInstrumento.getDescripcion());
        instrumento.setStock(nuevaInstrumento.getStock());
        instrumento.setImagen(nuevaInstrumento.getImagen());
        instrumento.setPrecio(nuevaInstrumento.getPrecio());
        if (nuevaInstrumento.getMarca_id() != 0) {
            Marca marca = marcaRepository.findById(nuevaInstrumento.getMarca_id())
                    .orElseThrow(
                            () -> new ResourceNotFoundException(
                                    "Clasification no encontrada con id: "
                                            + nuevaInstrumento.getMarca_id()));
            instrumento.setMarca(marca);
        }
        return convertToDto(instrumentoRepository.save(instrumento));

    }

    public void Delete(Integer id) {
        if (!instrumentoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Artista no encontrado con id: " + id);
        }
        instrumentoRepository.deleteById(id);
    }

    private InstrumentoDTO convertToDto(Instrumento instrumento) {
        InstrumentoDTO instrumentoDTO = new InstrumentoDTO();
        instrumentoDTO.setId(instrumento.getId());
        instrumentoDTO.setInstrumento_name(instrumento.getInstrumento_name());
        instrumentoDTO.setDescripcion(instrumento.getDescripcion());
        instrumentoDTO.setStock(instrumento.getStock());
        instrumentoDTO.setImagen(instrumento.getImagen());
        instrumentoDTO.setPrecio(instrumento.getPrecio());
        instrumentoDTO.setCreated_at(instrumento.getCreated_at());
        if (instrumento.getMarca() != null) {
            instrumentoDTO.setMarca_id(instrumento.getMarca().getId());
            instrumentoDTO.setMarca_name(instrumento.getMarca().getMarca_name());
        }
        return instrumentoDTO;
    }
}
