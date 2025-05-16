package com.curso.proyectofinal.exception;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorDetails {
    private Map<String, String> validationErrors;

    public ValidationErrorResponse(Date timestamp, String message, String details,
            Map<String, String> validationErrors) {
        super(timestamp, message, details);
        this.validationErrors = validationErrors;
    }
}
