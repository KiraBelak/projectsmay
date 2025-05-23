package com.alldata.javacourse.surveys.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public record RestExceptionMessage(String message, String stacktrace) {

    public static RestExceptionMessage createRestExceptionMessage(String message) {
        return new RestExceptionMessage(message, null);
    }

    public static RestExceptionMessage createRestExceptionMessage(Throwable throwable) {
        // Convert stacktrace to string
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return new RestExceptionMessage(throwable.getMessage(), sw.toString());
    }
}
