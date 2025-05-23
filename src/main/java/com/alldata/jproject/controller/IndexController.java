package com.alldata.jproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Rest controller solo se utiliza para devolver datos en backend
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/login_page")
    public String login(){
        return "login_page";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }
}
