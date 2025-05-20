package com.alldata.mobsell.controller;

import com.alldata.mobsell.config.SecurityTestConfig;
import com.alldata.mobsell.dto.AuthRequest;
import com.alldata.mobsell.model.User;
import com.alldata.mobsell.security.JwtService;
import com.alldata.mobsell.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(SecurityTestConfig.class)
public class AuthControllerTest {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @MockitoBean
    private AuthenticationManager authenticationManager;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    private User testUser;
    private AuthRequest authRequest;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setEmail("test@example.com");
        testUser.setRole("ROLE_USER");

        authRequest = new AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password123");

        jwtToken = "test.jwt.token";
    }

    @Test
    void testRegisterSuccess() throws Exception {
        when(userService.loadUserByUsername("testuser")).thenThrow(new RuntimeException("User not found"));
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userService.save(any(User.class))).thenReturn(testUser);
        when(jwtService.generateToken(testUser)).thenReturn(jwtToken);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(jwtToken));

        verify(userService).loadUserByUsername("testuser");
        verify(passwordEncoder).encode("password123");
        verify(userService).save(any(User.class));
        verify(jwtService).generateToken(testUser);
    }

    @Test
    void testLoginSuccess() throws Exception {
        when(userService.loadUserByUsername("testuser")).thenReturn(testUser);
        when(jwtService.generateToken(testUser)).thenReturn(jwtToken);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(jwtToken));

        verify(authenticationManager).authenticate(
                new UsernamePasswordAuthenticationToken("testuser", "password123"));
        verify(userService).loadUserByUsername("testuser");
        verify(jwtService).generateToken(testUser);
    }

    @Test
    void testRegisterUserAlreadyExists() throws Exception {
        when(userService.loadUserByUsername("testuser")).thenReturn(testUser);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isBadRequest());

        verify(userService).loadUserByUsername("testuser");
        verify(userService, never()).save(any(User.class));
    }
}