package com.alldata.jproject.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    //clase para configurar el token
    @Value("${jwt.secret}")
    private String jwtPassword;

    @Value("${jwt.expirationMs}")
    private Long expirationMs;

    private Key key;

    public JwtTokenProvider(@Value("${jwt.secret}") String jwtPassword){
        this.key = Keys.hmacShaKeyFor(jwtPassword.getBytes());
    }

    public String createNewToken(Authentication auth){
        Date currentDate  = new Date();
        Date expirationDate = new Date(currentDate.getTime() + expirationMs);
        String username = auth.getName();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
