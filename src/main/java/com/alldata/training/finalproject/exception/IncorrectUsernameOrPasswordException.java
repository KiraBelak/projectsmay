package com.alldata.training.finalproject.exception;

public class IncorrectUsernameOrPasswordException extends RuntimeException {
    private String message;

    public IncorrectUsernameOrPasswordException () {

    }

    public IncorrectUsernameOrPasswordException (String message) {
        super(message);
        this.message = message;
    }
}
