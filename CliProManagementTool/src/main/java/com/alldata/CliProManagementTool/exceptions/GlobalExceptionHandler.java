package com.alldata.CliProManagementTool.exceptions;/*
 * @created 20/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MensajeExceptionDTO> executionTimeError(RuntimeException runtimeException){
        MensajeExceptionDTO errorMessage = new MensajeExceptionDTO(runtimeException.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());

        errorMessage.setMessage("Error during runtime: "+ errorMessage.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DoublePaymentException.class)
    public ResponseEntity<MensajeExceptionDTO> conflictOnPayment(DoublePaymentException doublePaymentException){
        MensajeExceptionDTO errorMessage = new MensajeExceptionDTO(doublePaymentException.getMessage(),HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
        errorMessage.setMessage("Error during creation of payment, double payment attached to client and provider "+ errorMessage.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

}
