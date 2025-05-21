package com.alldata.CliProManagementTool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class doublePaymentException extends RuntimeException {
    private static final long serialVersionUID= 1L;
    public doublePaymentException(String message) {
        super(message);
    }
}
