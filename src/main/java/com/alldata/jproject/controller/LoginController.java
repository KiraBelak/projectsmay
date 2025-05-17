package com.alldata.jproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @PostMapping("/login/send_data")
    public String login(){
        //agregar chequeo de datos con db
        return "store";
    }
}
