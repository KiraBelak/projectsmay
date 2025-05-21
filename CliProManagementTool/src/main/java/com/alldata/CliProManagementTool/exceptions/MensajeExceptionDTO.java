package com.alldata.CliProManagementTool.exceptions;/*
 * @created 20/05/2025
 * @project CliProManagementTool
 * @author Noktuos
 */

import java.time.LocalDateTime;
import java.util.Date;

public class MensajeExceptionDTO {

    private String message;
    private int errorCode;
    private LocalDateTime timeStamp;

    public MensajeExceptionDTO(String message, int errorCode, LocalDateTime timeStamp) {
        this.message = message;
        this.errorCode = errorCode;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
