package com.gussoft.textract;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ImageDownload {

    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        String local = "C:\\Users\\LENOVO\\Desktop\\in.png";
        String net = "https://rutaout.s3.amazonaws.com/in_i.png";
        String imgs = "https://rutaout.s3.amazonaws.com/in.png";
        String imgl = "http://pruebas.del.pe/DBNET_DEL\\librerias\\img\\firma_ava.png";
        String img = local;
        /*String response = UrlExists(img);
        if (response.equals("URL")){
            response = ImageNet(img);
        }else{
            response = ImageLocal(img);
        }
        System.out.println("response = " + response);*/
        if (urlValidator(img)) {
            img = ImageLink(img);
            System.out.print("La url dada " + img + " es válida");
        }
        else
            System.out.print("La url dada " + img + " no es válida");


    }

    public static String getExtension(String file) {
        return file.replaceAll("^.*\\.(.*)$", "$1");
    }

    public static String ImageNet(String input){
        String salida = "";
        if (input.contains("_s_")){
            String ext = getExtension(input);
            try(InputStream in = new URL(input).openStream()){
                File output = File.createTempFile("image","." + ext);
                Files.copy(in, Paths.get(output.getPath()), REPLACE_EXISTING);
                salida = output.getAbsolutePath();
                System.out.println("Net");
            }catch (MalformedURLException ex){
                System.out.println("No es net");
                salida = ImageLocal(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Imagen Correcta!");
            salida = ImageLink(input);
        }
        return salida;
    }

    public static String ImageLocal(String input){
        String ext = getExtension(input);
        String salida = "";
        try
        {
            File output = File.createTempFile("image","." + ext);
            InputStream in = new FileInputStream(input);
            OutputStream out = new FileOutputStream(output);

            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            salida = output.getAbsolutePath();
            in.close();
            out.close();
            System.out.println("Local");
        }catch (Exception e){
            e.printStackTrace();
        }
        return salida;
    }

    public static String ImageLink(String input){
        String salida = "";
        if (input.contains("\\")){
            salida = input.replaceAll("\\\\", "/");
        }else{
            salida = input;
        }
        return salida;
    }

    public static String UrlExists(String input){
        try {
            input = ImageLink(input);
            final URL url = new URL(input);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();
            if (responseCode == 200) {
                return "URL";
            }
        } catch (MalformedURLException e){
            return "LOCAL";
        } catch (IOException e){
            e.printStackTrace();
        }
        return "null";
    }
    public static boolean urlValidator(String url)
    {
        String uri = ImageLink(url);
        try {
            new URL(uri).toURI();
            return true;
        }
        catch (URISyntaxException exception) {
            return false;
        }

        catch (MalformedURLException exception) {
            return false;
        }
    }
}
