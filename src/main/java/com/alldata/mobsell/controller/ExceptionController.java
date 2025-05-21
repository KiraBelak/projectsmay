package com.alldata.mobsell.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/error")
public class ExceptionController {
    @PostMapping
    public void handleException(@RequestBody String message) {
        throw new RuntimeException(message);
    }
}
