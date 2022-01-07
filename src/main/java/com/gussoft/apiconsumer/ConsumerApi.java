package com.gussoft.apiconsumer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsumerApi {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");

        String mp3 = "C:\\Users\\LENOVO\\Desktop\\redhot.mp3";
        String rdh = "C:\\Users\\LENOVO\\Desktop\\feria\\Red Hot Chili peppers Live at Slane Castle Full Concert.mp3";
        String gar = "C:\\Users\\LENOVO\\Desktop\\feria\\Guns and Roses - Live Tokyo 1992 (Full Concert) [HD].mp3";
        String out = "C:\\Users\\LENOVO\\Desktop\\radio3.p7m";
        String server = "http://localhost:9004";

        setFiletoSing(new File(rdh), new File(out), server);
    }

    public static void setFiletoSing(File fileIn, File fileOut, String server) {
        try {
            String parameters = server + "/api/cades/sign";
            URL url = new URL(parameters);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "audio/mpeg");

            final long documentLength = fileIn.length();
            conn.setFixedLengthStreamingMode(documentLength);

            OutputStream os = conn.getOutputStream();
            try (InputStream documentIS = new BufferedInputStream(new FileInputStream(fileIn))) {
                byte[] b = new byte[4096];

                int readCount;
                while (-1 != (readCount = documentIS.read(b))) {
                    os.write(b, 0, readCount);
                }
            }
            int statusCode = conn.getResponseCode();

            if (statusCode == 200){
                InputStream inputStream = conn.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(fileOut);
                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
                System.out.println("Se guardo el documento en : " + fileOut.getAbsolutePath());
            }else{
                System.out.println("algo fallo error : " + statusCode);
            }
            conn.disconnect();
            System.out.println(statusCode);

        }catch (IOException e){
            System.out.println("Error ... " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void getDocumentSing(String Server) throws IOException {
        URL url = new URL (Server);
        String json = "{\"code\": \"58a6dd9c417dd9bb00a5075d727ffb0ff769204248cab598880bb8ffd3dbcd83\", \"documentId\": \"2725\"}";
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        int code = conn.getResponseCode();
        System.out.println(code);
        InputStream inputStream = conn.getInputStream();
        String saveFilePath = "C:\\Users\\LENOVO\\Desktop\\demoFirmado.pdf";
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);

        int bytesRead = -1;
        byte[] buffer = new byte[4096];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();

        System.out.println("File downloaded");
    }

}
