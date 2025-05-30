package com.alldata.jproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alldata.jproject.models.Clasificacion;
import com.alldata.jproject.repositories.ClasificacionRepository;
import com.alldata.jproject.services.ClasificacionService;
import com.alldata.jproject.modelsDTO.ClasificacionDTO;

@ExtendWith(MockitoExtension.class)
public class ClasificacionServiceTest {

    @Mock
    private ClasificacionRepository clasificacionRepository;

    @InjectMocks
    private ClasificacionService clasificacionService;

    private Clasificacion clasificacion;

    @BeforeEach
    void setUp() {
        clasificacion = new Clasificacion();
        clasificacion.setId(2);
        clasificacion.setClasificacion_name("Clasificacion Test");
        clasificacion.setDescripcion("Clasificacion Test");
    }

    @Test
    void getAllClasificaciones_ShouldReturnListClasificacionDTO() {

        when(clasificacionRepository.findAll()).thenReturn(Arrays.asList(clasificacion));

        List<ClasificacionDTO> result = clasificacionService.Get();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(clasificacion.getId(), result.get(0).getId());
        assertEquals(clasificacion.getClasificacion_name(), result.get(0).getClasificacion_name());
    }
        @Test
    void getClasificatioId_ShouldReturnClasificacionDTO() {

        when(clasificacionRepository.findById(1)).thenReturn(Optional.of(clasificacion));

        ClasificacionDTO result = clasificacionService.GetById(1);

        assertNotNull(result);
        assertEquals(clasificacion.getId(), result.getId());
        assertEquals(clasificacion.getClasificacion_name(), result.getClasificacion_name());
    }

}
