package com.gussoft.bigsignerclient;

import java.util.Date;

public class LoggerController {

    private final LoggerBSService datos;
    private final LoggerBS obj;

    public LoggerController() {
        this.datos = new LoggerBSService();
        this.obj = new LoggerBS();
    }

    public String info(String header, String message) {
        obj.setHora(new Date().toString());
        obj.setHeader("[INFO] " + header);
        obj.setMessage(message);
        if (datos.insertLog(obj)) {
            return "Ok";
        } else {
            return "Error en el registro!";
        }
    }

    public String error(String header, String message) {
        obj.setHora(new Date().toString());
        obj.setHeader("[ERROR] " + header);
        obj.setMessage(message);
        if (datos.insertLog(obj)) {
            return "Ok";
        } else {
            return "Error en el registro!";
        }
    }
}
