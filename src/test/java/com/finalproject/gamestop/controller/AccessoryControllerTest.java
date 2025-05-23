package com.finalproject.gamestop.controller;

import com.finalproject.gamestop.dto.AccessoryDTO;
import com.finalproject.gamestop.security.JwtAuthEntryPoint;
import com.finalproject.gamestop.security.JwtAuthenticationFilter;
import com.finalproject.gamestop.security.JwtTokenProvider;
import com.finalproject.gamestop.service.AccessoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccessoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class AccessoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccessoryService accessoryService;
    @MockitoBean
    private JwtTokenProvider jwtTokenProvider;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    private AccessoryDTO accessoryDTO;

    @BeforeEach
    void setUp() {
        accessoryDTO = new AccessoryDTO();
        accessoryDTO.setId(1L);
        accessoryDTO.setName("Gaming Headset");
        accessoryDTO.setDescription("High-quality sound");
        accessoryDTO.setPrice(99.99);
        accessoryDTO.setStock(50);
        accessoryDTO.setBrand("Logitech");
        accessoryDTO.setType("Headset");
        accessoryDTO.setCompatibility("PC");
    }

    @Test
    void testGetAllAccessories() throws Exception {
        when(accessoryService.getAllAccessoryDTOs()).thenReturn(List.of(accessoryDTO));

        mockMvc.perform(get("/api/accessories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gaming Headset"))
                .andExpect(jsonPath("$[0].price").value(99.99));
    }

    @Test
    void testCreateAccessory() throws Exception {
        when(accessoryService.createAccessory(any(AccessoryDTO.class))).thenReturn(accessoryDTO);

        mockMvc.perform(post("/api/accessories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accessoryDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Gaming Headset"));

        verify(accessoryService).createAccessory(any(AccessoryDTO.class));
    }

    @Test
    void testGetAccessoryByIdNotFound() throws Exception {
        when(accessoryService.getAccessoryDTOById(999L))
                .thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/accessories/999"))
                .andExpect(status().isNotFound());
    }
}
