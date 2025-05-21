package com.alldata.training.finalproject.exception;

public class NoStudentFoundException extends RuntimeException {
    private String message;

    public NoStudentFoundException () {

    }

    public NoStudentFoundException (String message) {
        super(message);
        this.message = message;
    }
}
