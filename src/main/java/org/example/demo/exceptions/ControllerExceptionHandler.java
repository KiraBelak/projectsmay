package org.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleNullPointerException(NullPointerException e) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("exception", "NullPointerException");
        errorDetails.put("message", e.getMessage() != null ? e.getMessage() : "A null value was encountered");

        // Get the stack trace element where the exception occurred
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement element = stackTrace[0];
            errorDetails.put("className", element.getClassName());
            errorDetails.put("methodName", element.getMethodName());
            errorDetails.put("lineNumber", element.getLineNumber());
            errorDetails.put("fileName", element.getFileName());
        }
        return errorDetails;
    }
}