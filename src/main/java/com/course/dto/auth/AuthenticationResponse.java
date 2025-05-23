package com.course.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String jwtToken;
    private String error;

    public AuthenticationResponse(String jwtToken, String username) {
        this.username = username;
        this.jwtToken = jwtToken;
    }

    public AuthenticationResponse(String error) {
        this.error = error;
    }
}
