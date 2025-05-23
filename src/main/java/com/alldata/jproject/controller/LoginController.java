package com.alldata.jproject.controller;

import com.alldata.jproject.models.AccountDTO;
import com.alldata.jproject.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    //spring security espera que se haga un post a login por defecto
    //IMPORTANTE siempre hacer coincidir los nombres de spring security para password y email con el formulario de login
//    @PostMapping("/login/save")
//    public String login(@RequestBody AccountDTO accountDTO){
//        Authentication auth = authManager.authenticate(
//          new UsernamePasswordAuthenticationToken(accountDTO.getEmail(), accountDTO.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(auth);
//        String token = tokenProvider.createNewToken(auth);
//
//        return "store";
//    }
//
    @GetMapping("/login/signin")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            Model model) {
        if (error != null) {
            model.addAttribute("error", "Credenciales inválidas");
        }
        return "login_page";
    }
}
