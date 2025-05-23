package com.finalproject.gamestop.service;

import com.finalproject.gamestop.dto.AccessoryDTO;
import com.finalproject.gamestop.mapper.ProductMapper;
import com.finalproject.gamestop.model.Accessory;
import com.finalproject.gamestop.repository.AccessoryRepository;
import com.finalproject.gamestop.service.impl.AccessoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessoryServiceTest {

    @Mock
    private AccessoryRepository accessoryRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private AccessoryServiceImpl accessoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAccessoryDTOs() {
        Accessory accessory = new Accessory();
        accessory.setId(1L);
        when(accessoryRepository.findAll()).thenReturn(Collections.singletonList(accessory));
        when(productMapper.accessoryToAccessoryDTO(any())).thenReturn(new AccessoryDTO());

        assertEquals(1, accessoryService.getAllAccessoryDTOs().size());
    }

    @Test
    void testGetAccessoryDTOById() {
        Accessory accessory = new Accessory();
        accessory.setId(1L);
        when(accessoryRepository.findById(1L)).thenReturn(Optional.of(accessory));
        when(productMapper.accessoryToAccessoryDTO(any())).thenReturn(new AccessoryDTO());

        AccessoryDTO dto = accessoryService.getAccessoryDTOById(1L);
        assertNotNull(dto);
    }
}
