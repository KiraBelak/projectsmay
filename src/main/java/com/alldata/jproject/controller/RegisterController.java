package com.alldata.jproject.controller;

import com.alldata.jproject.entities.User;
import com.alldata.jproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {
    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    public RegisterController(UserServiceImpl userService){
        this.userService = userService;
    }


    @PostMapping("/register/save")
    public String userRegistration(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
        userService.registerNewUser(user);

        return "login_page";
    }
}
