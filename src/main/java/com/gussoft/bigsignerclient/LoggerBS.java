package com.gussoft.bigsignerclient;

import java.util.Date;
import java.util.Objects;

public class LoggerBS {

    private String hora;
    private String header;
    private String message;

    public LoggerBS() {
    }

    public LoggerBS(String hora, String header, String message) {
        this.hora = hora;
        this.header = header;
        this.message = message;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoggerBS)) return false;
        LoggerBS loggerBS = (LoggerBS) o;
        return Objects.equals(hora, loggerBS.hora) && Objects.equals(header, loggerBS.header) && Objects.equals(message, loggerBS.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hora, header, message);
    }
}
