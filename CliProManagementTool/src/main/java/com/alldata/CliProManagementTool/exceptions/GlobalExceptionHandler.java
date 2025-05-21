package com.alldata.CliProManagementTool.exceptions;/*
 * @created 20/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.w3c.dom.ranges.RangeException;

import java.security.Timestamp;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensajeExceptionDTO> executionTimeError(RuntimeException runtimeException){
        MensajeExceptionDTO errorMessage = new MensajeExceptionDTO(runtimeException.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        errorMessage.setMessage("Error during runtime: "+ errorMessage.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(doublePaymentException.class)
    public ResponseEntity<MensajeExceptionDTO> conflictOnPayment(RuntimeException runtimeException){
        MensajeExceptionDTO errorMessage = new MensajeExceptionDTO(runtimeException.getMessage(),HttpStatus.CONFLICT.value(), LocalDateTime.now());
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }

}
