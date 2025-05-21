package com.alldata.training.finalproject.exception;

public class NoTeacherFoundException extends RuntimeException {
    private String message;

    public NoTeacherFoundException () {

    }

    public NoTeacherFoundException (String message) {
        super(message);
        this.message = message;
    }
}
