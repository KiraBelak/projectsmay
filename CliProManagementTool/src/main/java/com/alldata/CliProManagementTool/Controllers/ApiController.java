package com.alldata.CliProManagementTool.Controllers;/*
 * @created 23/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping("/hello")
    public String hello(){
        return "Hi, you are authenticated!";
    }
}
