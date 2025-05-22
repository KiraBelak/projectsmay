package com.alldata.CliProManagementTool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DoublePaymentException extends RuntimeException {
    private static final long serialVersionUID= 1L;
    public DoublePaymentException(String message) {
        super(message);
    }
}
