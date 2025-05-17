package com.alldata.jproject.controller;

import com.alldata.jproject.entities.User;
import com.alldata.jproject.models.UserDTO;
import com.alldata.jproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final UserServiceImpl userService;

    @Autowired
    public RegisterController(UserServiceImpl userService){
        this.userService = userService;
    }

//    //el post debe tener la misma ruta en el controlador y en el html
//    @PostMapping("/register/save")
//    public String userRegistration(@RequestParam String fname, @RequestParam String email, @RequestBody User user){
//        //agregar datos a la db
//        userService.registerNewUser(user);
//        return "login";
//    }

    @PostMapping("/register/save")
    public ResponseEntity<String> userRegistration(@ModelAttribute User user){
        //agregar datos a la db
        userService.registerNewUser(user);
        return ResponseEntity.ok("login");
    }
}
