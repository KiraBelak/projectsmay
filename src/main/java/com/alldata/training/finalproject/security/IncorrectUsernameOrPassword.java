package com.alldata.training.finalproject.security;

public class IncorrectUsernameOrPassword extends RuntimeException {
    private String message;

    public IncorrectUsernameOrPassword () {

    }

    public IncorrectUsernameOrPassword (String message) {
        super(message);
        this.message = message;
    }
}
