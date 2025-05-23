package com.alldata.CliProManagementTool.Security;/*
 * @created 23/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "twister_cable_tortora_seguridad_totalextremonium!!!009901_pero_que_tan_largo_necesito_que_sea";
    private static final Key KEY = new SecretKeySpec(
            Base64.getEncoder().encode(SECRET_KEY.getBytes()),
            SignatureAlgorithm.HS256.getJcaName()
    );

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();
    }

    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        return !getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token){
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
