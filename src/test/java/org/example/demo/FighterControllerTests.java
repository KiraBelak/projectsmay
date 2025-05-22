package org.example.demo;

import org.example.demo.controller.FighterController;
import org.example.demo.exceptions.ControllerExceptionHandler;
import org.example.demo.service.FighterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class FighterControllerTests {

    private MockMvc mockMvc;

    @Mock
    private FighterService fighterService;

    @InjectMocks
    private FighterController fighterController;

    @BeforeEach
    void setUp() {
        // Initialise Mockito annotations (@Mock / @InjectMocks)
        MockitoAnnotations.openMocks(this);
        // Build MockMvc and register the @ControllerAdvice
        mockMvc = MockMvcBuilders.standaloneSetup(fighterController)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("GET /api/fighters/{id} – NullPointerException handled by ControllerExceptionHandler")
    void getFighterById_shouldReturnBadRequest_whenNullPointerThrown() throws Exception {

        Mockito.when(fighterService.getFighterById(anyLong()))
                .thenThrow(new NullPointerException("fighter not found"));

        mockMvc.perform(get("/api/fighters/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("fighter not found")));
    }

    @Test
    //@DisplayName("GET /api/fighters/{id} – NullPointerException handled by ControllerExceptionHandler")
    void invalid_createFighter_shouldThrow() throws Exception {


        Mockito.when(fighterService.getFighterById(anyLong()));

        mockMvc.perform(get("/api/fighters/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(containsString("fighter not found")));
    }
}
