package com.alldata.CliProManagementTool.Controllers;/*
 * @created 23/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import com.alldata.CliProManagementTool.Security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtUtil jwtUtil = new JwtUtil();

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        if("noktu".equals(username) && "123321".equals(password)){
            return jwtUtil.generateToken(username);
        }else{
            throw new RuntimeException("Not valid credentials");
        }
    }
}
