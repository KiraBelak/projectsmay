package com.curso.proyectofinal.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.curso.proyectofinal.model.Notification;

@Controller
public class WebSocketController {

    @MessageMapping("/notification")
    @SendTo("/topic/global")
    public Notification sendNotification(Notification notification) {
        return notification;
    }
}
