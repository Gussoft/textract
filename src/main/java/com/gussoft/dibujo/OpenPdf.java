package com.gussoft.dibujo;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

public class OpenPdf {

    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        String file = "C:\\Users\\LENOVO\\Desktop\\F-Ejemplo 1.pdf";
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

            writer.setViewerPreferences(PdfWriter.PageModeUseOutlines);
            document.open();

            PdfContentByte cb = writer.getDirectContent();

            PdfTemplate template = cb.createTemplate(25, 25);

            template.moveTo(13, 0);
            template.lineTo(13, 25);
            template.moveTo(0, 13);
            template.lineTo(50, 13);
            template.stroke();

            cb.addTemplate(template, 380, 780);
            cb.addTemplate(template, 160, 480);
            cb.addTemplate(template, 450, 270);
            cb.addTemplate(template, 90, 90);

        } catch (Exception de) {
            de.printStackTrace();
        }
        document.close();
    }
}

