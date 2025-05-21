package com.alldata.jproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtConfig {
    //agregar clases de userdetailsserviceimpl
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/h2-console/**", "/api/auth/**", "/oauth2**").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/user/**").hasRole("USER")
                                .requestMatchers("/api/**").authenticated()
                                .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/store")
                        //TODO agregar pagina de error
                        .failureUrl("/"))
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/store")
                        .permitAll())
                .logout(logout -> logout
                        //TODO agregar pagina logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-sucess")
                        .invalidateHttpSession(true)
                        //TODO agregar cookies
                        .deleteCookies("COOKIE")
                        .permitAll());

        //h2 console config
        httpSecurity.headers(headers-> headers.frameOptions(frameOptions -> frameOptions.disable()));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
