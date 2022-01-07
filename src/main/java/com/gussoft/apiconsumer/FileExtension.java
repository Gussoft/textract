package com.gussoft.apiconsumer;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileExtension {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the Jungle!");
        String pdf = "C:\\Users\\LENOVO\\Desktop\\3page.pdf";
        String mp3 = "C:\\Users\\LENOVO\\Desktop\\4firmas.pdf";
        //System.out.println(getExtension(pdf));
        ChangeFileSignedWithSignedTimeStamp(new File(pdf),new File(mp3));
    }

    public static String getExtension(String file) {
        String ext = file.replaceAll("^.*\\.(.*)$", "$1");
        return "." + ext;
    }

    private static void ChangeFileSignedWithSignedTimeStamp(File signedTimes, File replace) throws FileNotFoundException {
        FileOutputStream signedDocumentFOS = new FileOutputStream(replace);
        try (FileInputStream tbsFIS = new FileInputStream(signedTimes)) {
            IOUtils.copy(tbsFIS, signedDocumentFOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
