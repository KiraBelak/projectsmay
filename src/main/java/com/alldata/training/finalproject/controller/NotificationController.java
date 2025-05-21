package com.alldata.training.finalproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alldata.training.finalproject.config.MyWebSocketHandler;
import com.alldata.training.finalproject.exception.NotificationErrorException;
import com.alldata.training.finalproject.model.Notification;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private MyWebSocketHandler webSocketHandler;

    @PostMapping("/send")
    public String sendMessage(@RequestBody Notification notification) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");            
            webSocketHandler.broadcast(LocalDateTime.now().format(formatter) + ": " + notification.getMessage());
            return "Notification published!";
        } catch (Exception e) {
            throw new NotificationErrorException(e.getMessage());
        }
    }
}
