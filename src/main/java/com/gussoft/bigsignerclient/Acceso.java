package com.gussoft.bigsignerclient;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Acceso {
    
    public static List<String> cargarArchivo(String ruta){
        String name = "info";
        List<String> lista = null;
        FileReader fi;
        BufferedReader bu;
        String linea;
        File carpeta = new File(name);
        File archivo = new File(name + "/" + ruta);
        try{
            if (!carpeta.exists()) {
                FileUtils.forceMkdir(carpeta);
            }
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            lista = new ArrayList<>();
            fi = new FileReader(name + "/" + ruta);
            bu = new BufferedReader(fi);
            while((linea = bu.readLine()) != null){
                lista.add(linea);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al cargar Archivo: " + archivo.getName() + " -> " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
