package com.gussoft.textract;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class RenderText {
    public static void main(String[] args) {
        Font javaFont = new Font(Font.SANS_SERIF, Font.PLAIN, 6);
        String fall = "Firmado digitalmente por el Ing: HUAMAN BERROCAL GUSTAVO Motivo: Soy el Autor del Documento Fecha: 14/02/2022 16:56";
        String texto = "Firmado digitalmente por: Gustavo Huaman Berrocal documento de pruebas para verificar que siempre se adecue el texto al rectangulo algo extra para mas Fecha: 11/01/2022 15:27";
        String text2 = "Firmado digitalmente por: SISTEMA DE AGENTE AUTOMATIZADO - TEST MAS CONTENIDO PARA EL PUESTO DEL TRABAJADOR O EMPLEADO FIRMA Fecha: 11/01/2022 15:27";
        String text0 = "Firmado digitalmente por: SISTEMA DE AGENTE AUTOMATIZADO - TEST MAS CONTENIDO PARA EL PUESTO DEL TRABAJADOR O EMPLEADO FIRMA 4 FIRMA tags y VISTOS<br> Fecha: 11/01/2022 15:27";
        String text3 = "Firmado digitalmente por: elver galarga chota razon firmado por el glorioso area de sistemas Fecha: 07/02/2022 19:26";
        String text4 = "firmado digitalmente por: sistema de agente automatizado - test mas contenido para el puesto del trabajador o empleado firma 1000 firmas tags y vistos ejemplo cualquiera ojala funcione fecha: 07/02/2022 19:26";
        SignatureFieldParameters sp = new SignatureFieldParameters();
        sp.setPage(1);
        sp.setOriginX(470.0f);
        sp.setOriginY(0.0f);
        sp.setWidth(140.0f);
        sp.setHeight(50.0f);
        //Map<Integer, Integer> res = contarPalabras(texto);
        String res = calcularAnchoyAlto(javaFont,sp,text2, false);
        System.out.println(res);
    }

    private static String calculateWidthAndHeigth(Font font, SignatureFieldParameters fieldParameters, String text, boolean useAllWidth) {
        String[] split = text.split("\\n");
        int width = (int) ((int) fieldParameters.getWidth() / 1.7F);

        if (useAllWidth) {
            width = (int) fieldParameters.getWidth();
        }

        StringBuilder newText = new StringBuilder();
        for (String texto : split) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            int fontWidth = (int) Math.ceil(font.getStringBounds(texto, frc).getWidth());
            if (fontWidth > width) {
                double percentage = Math.round(calculatePercentage(width, fontWidth));
                double sizeDimension = texto.length() * (percentage / 100.0f);

                double tempFontWidth;
                String shortText;
                int subString;
                do {
                    shortText = texto.substring(0, (int) Math.round(sizeDimension));
                    tempFontWidth = Math.ceil(font.getStringBounds(shortText, frc).getWidth());
                    subString = shortText.length();
                    sizeDimension--;
                } while (tempFontWidth >= width);

                newText.append(texto, 0, (int) Math.round(sizeDimension)).append("\n");
                newText.append(calculateWidthAndHeigth(font, fieldParameters, texto.substring(subString - 1), useAllWidth));
            } else {
                newText.append(texto).append("\n");
            }
        }
        return newText.toString();
    }

    public static double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }

    public static List<Integer> contarPalabras(String texto) {
        int contador = 1, position;
        List<Integer> coord = new ArrayList<>();
        texto = texto.trim();
        if (texto.isEmpty()) {
            contador = 0;
        } else {
            position = texto.indexOf(" ");
            while (position != -1) {
                coord.add(position);
                contador++;
                position = texto.indexOf(" ", position + 1);
            }
        }
        return coord;
    }

    public static String calcularAnchoyAlto(Font font, SignatureFieldParameters fieldParameters, String text, boolean useAllWidth){
        String[] split = text.split("\\n");
        int width = (int) ((int) fieldParameters.getWidth() / 1.7F);

        if (useAllWidth) {
            width = (int) fieldParameters.getWidth();
        }

        StringBuilder newText = new StringBuilder();
        for (String texto : split) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
            int fontWidth = (int) Math.ceil(font.getStringBounds(texto, frc).getWidth());
            if (fontWidth > width) {
                double percentage = Math.round(calculatePercentage(width, fontWidth));
                double sizeDimension = texto.length() * (percentage / 100.0f);

                int subString = 0;
                double tempFontWidth;
                String shortText;
                String newtext = "";
                do {
                    shortText = texto.substring(0, (int)Math.round(sizeDimension));
                    tempFontWidth = Math.ceil(font.getStringBounds(shortText, frc).getWidth());
                    List<Integer> spacing = contarPalabras(texto);
                    for (Integer space : spacing){
                        if (space <= shortText.length()){
                            subString = space;
                            newtext = texto.substring(space + 1);
                        }
                    }
                    --sizeDimension;
                } while (tempFontWidth >= (double)width);

                newText.append(texto, 0, Math.round((float)subString)).append("\n");
                newText.append(calcularAnchoyAlto(font, fieldParameters, newtext, useAllWidth));
            } else {
                newText.append(texto).append("\n");
            }
        }
        return newText.toString();
    }

}
