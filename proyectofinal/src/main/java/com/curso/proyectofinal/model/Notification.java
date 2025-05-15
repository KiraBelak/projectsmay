package com.curso.proyectofinal.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private String message;
    private String type;
    private LocalDateTime timestamp;
    private Object data;

    public Notification(String message, String type) {
        this.message = message;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public Notification(String message, String type, Object data) {
        this(message, type);
        this.data = data;
    }
}
