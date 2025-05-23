package com.alldata.javacourse.surveys.auth;

import com.alldata.javacourse.surveys.exception.AuthorizationException;
import com.alldata.javacourse.surveys.model.User;
import com.alldata.javacourse.surveys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder encoder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService,
            PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("login")
    public ResponseEntity<Map<String, Object>> createAuthenticationToken(@RequestBody AuthDto authDto) {
        final User user;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    Objects.requireNonNull(authDto.username(), "Username cannot be empty"),
                    Objects.requireNonNull(authDto.password(), "Password cannot be empty")));

            user = userService.findByName(authDto.username())
                    .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        } catch (AuthenticationException e) {
            throw new AuthorizationException(e.getMessage());
        }

        final String token = jwtUtil.generateToken(user.getName());
        return ResponseEntity.ok(Map.of("token", token, "userId", user.getId()));
    }

    @PostMapping("register")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signupDto) {
        if (userService.findByName(Objects.requireNonNull(signupDto.name(), "Username cannot be empty")).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username already exists");
        }

        // Create new user's account
        User user = new User();
        user.setName(signupDto.name());
        user.setPassword(encoder.encode(Objects.requireNonNull(signupDto.password(), "Password cannot be empty")));
        user.setEmail(signupDto.email());
        user.getRoles().add(User.Role.USER);

        userService.save(user);

        return ResponseEntity.ok().build();
    }
}
