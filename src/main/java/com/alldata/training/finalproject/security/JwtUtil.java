package com.alldata.training.finalproject.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${secret.jwt.key}")
    private String secretJwtKey;

    @Value("${expiration.time}")
    private int expirationTime;

    private Key key;

    public JwtUtil(@Value("${secret.jwt.key}") String jwtSecret) {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //public boolean validateToken(String token, UserDetails userDetails) {
    //    final String username = extractUsername(token);
    //    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    //}

    //private boolean isTokenExpired(String token) {
    //    final Date expiration = Jwts.parser().setSigningKey(secretJwtKey).parseClaimsJws(token).getBody().getExpiration();
    //    return expiration.before(new Date());
    //}
}