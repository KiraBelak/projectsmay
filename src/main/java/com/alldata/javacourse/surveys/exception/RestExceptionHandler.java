package com.alldata.javacourse.surveys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestExceptionMessage> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestExceptionMessage.createRestExceptionMessage(e));
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<RestExceptionMessage> handleAuthException(AuthorizationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(RestExceptionMessage.createRestExceptionMessage(e.getMessage()));
    }
}
