package com.alldata.jproject.models;

import java.time.LocalDateTime;

public class ExceptionMessageDTO {
    private String mensaje;
    private int codigoError;
    private LocalDateTime timestamp;

    public ExceptionMessageDTO(String mensaje, int codigoError) {
        this.mensaje = mensaje;
        this.codigoError = codigoError;
        this.timestamp = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(int codigoError) {
        this.codigoError = codigoError;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
