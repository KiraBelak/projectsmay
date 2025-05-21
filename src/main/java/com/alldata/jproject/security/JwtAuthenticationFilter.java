package com.alldata.jproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token =
    }

    private String getJwtf(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken.startsWith("Bearer ") && !bearerToken.isBlank()) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
