package com.gussoft.bigsignerclient;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LoggerBSService implements Datos<LoggerBS>{

    private final List<LoggerBS> lista;
    private final String ruta = "LogBS.log";
    private boolean resp;
    private LoggerBS datos;
    private Metods<LoggerBS> metodos;
    private final String name = "info";

    public LoggerBSService() {
        lista = new ArrayList<>();
        metodos = new Metods<>(lista);
        cargarLista();
    }

    private void cargarLista() {
        LoggerBS obj;
        for (String dato : Acceso.cargarArchivo(ruta)) {
            StringTokenizer st = new StringTokenizer(dato, "-");
            obj = new LoggerBS(st.nextToken(), st.nextToken(), st.nextToken());
            metodos.addLine(obj);
        }
    }

    @Override
    public boolean insertLog(LoggerBS obj) {
        resp = false;
        PrintWriter pw;
        FileWriter fw;
        try {
            fw = new FileWriter(name + "/" + ruta);
            pw = new PrintWriter(fw);
            obj = new LoggerBS(obj.getHora(), obj.getHeader(), obj.getMessage());
            metodos.addLine(obj);
            for (int i = 0; i < metodos.countLine(); i++) {
                datos = metodos.getLine(i);
                pw.println(String.valueOf(datos.getHora() + "-" + datos.getHeader() + "-" + datos.getMessage()));
            }
            pw.close();
            resp = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al Insertar LoggerBS : " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    @Override
    public boolean updateLog(LoggerBS obj) {
        return false;
    }

    @Override
    public int searchCode(int code) {
        return 0;
    }
}
