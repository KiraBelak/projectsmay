package com.alldata.training.finalproject.exception;

public class NotificationErrorException extends RuntimeException {
    private String message;

    public NotificationErrorException () {

    }

    public NotificationErrorException (String message) {
        super(message);
        this.message = message;
    }
}
