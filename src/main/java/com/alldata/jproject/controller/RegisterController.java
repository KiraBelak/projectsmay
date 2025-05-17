package com.alldata.jproject.controller;

import com.alldata.jproject.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    //el post debe tener la misma ruta en el controlador y en el html
    @PostMapping("/register/save")
    public String userRegistration(@RequestParam String fname, @RequestParam String email, Model model){
        //agregar datos a la db
        return "login";
    }
}
