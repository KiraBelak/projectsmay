package com.alldata.jproject.models;

public class JwTResponseDTO {
    private String token;
    private String tokenType = "Bearer";

    public JwTResponseDTO(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
