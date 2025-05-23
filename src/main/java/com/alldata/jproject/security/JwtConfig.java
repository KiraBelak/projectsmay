package com.alldata.jproject.security;

import com.alldata.jproject.service.impl.CustomUserDetailService;
import com.alldata.jproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Autowired
    CustomUserDetailService customUserDetailService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //se deshabilita csrf para acceder a h2
                .csrf(csrf->csrf.disable())
                //se deshabilitan los iframes para que no sean interceptados por el filtro
                .headers(headers-> headers.frameOptions(frameOptions -> frameOptions.disable()))
                //STATEELSS se utiliza para puro API rest
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/", "/login/**", "/register").permitAll()
                                .requestMatchers("/store/**").hasRole("ADMIN")
                                .requestMatchers("/store/user/**").hasRole("USER")
                                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login/signin")
                        //son los valores del formulario
                        .loginProcessingUrl("/login/signin")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/store", true)
                        .failureUrl("/login/signin?error=true")
                        .permitAll())
                .logout(logout -> logout
                        //TODO agregar pagina logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-sucess")
                        .invalidateHttpSession(true)
                        //TODO agregar cookies
                        .deleteCookies("COOKIE")
                        .permitAll());

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
