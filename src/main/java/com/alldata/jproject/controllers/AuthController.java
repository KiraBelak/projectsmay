package com.alldata.jproject.controllers;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alldata.jproject.models.Usuario;
import com.alldata.jproject.modelsDTO.AuthResponseDTO;
import com.alldata.jproject.modelsDTO.LoginDTO;
import com.alldata.jproject.modelsDTO.UsuarioDTO;
import com.alldata.jproject.repositories.UsuarioRepository;
import com.alldata.jproject.security.TokenProvider;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserName(),
                        loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registerUser(@RequestBody UsuarioDTO usuarioDTO) {
        // Validar si el usuario ya existe
        if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Crear nuevo usuario
        Usuario user = new Usuario();
        user.setUsername(usuarioDTO.getUsername());
        user.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        // Asignar roles
        HashSet<String> roles = new HashSet<>();
        if (usuarioDTO.getRoles() != null && !usuarioDTO.getRoles().isEmpty()) {
            roles.addAll(usuarioDTO.getRoles());
        } else {
            roles.add("cashier");
        }
        user.setRoles(roles);
        usuarioRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

}
