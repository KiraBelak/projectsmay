package com.alldata.training.finalproject.exception;

public class NoLoggedInException extends RuntimeException {
    private String message;

    public NoLoggedInException () {

    }

    public NoLoggedInException (String message) {
        super(message);
        this.message = message;
    }
}
