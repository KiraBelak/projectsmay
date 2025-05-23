package com.alldata.javacourse.surveys.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthorizationException extends AuthenticationException {
    public AuthorizationException(String msg) {
        super(msg);
    }
}
