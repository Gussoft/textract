package com.gussoft.sendmail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;


public class Send {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle");
        String Asunto = "Este es un correo de prueba con imagen";
        String mensaje = "<h1>Prueba 3</h1> viendo como se envia un correo electronico <br>desde una cuenta de Gmail en java! Sgundo Ejemplo<br>Gracias por su atencion!";
        String image = "C:\\Users\\LENOVO\\Desktop\\in.png";
        sendMail("creazygus@gmail.com", Asunto, mensaje, image);
    }

    public static void sendMail(String goCorreo, String Asunto, String message, String images){
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.host","smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable","true");
            properties.setProperty("mail.smtp.port","587");
            properties.setProperty("mail.smtp.auth","true");

            Session session = Session.getDefaultInstance(properties);
            String correoEmi = "sapitoelbarbaro@gmail.com";
            String password = "password";

            BodyPart texto = new MimeBodyPart();
            texto.setContent(message, "text/html");

            File img = new File(images);
            BodyPart image = new MimeBodyPart();
            image.setDataHandler(new DataHandler(new FileDataSource(img)));
            image.setFileName(img.getName());

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            multipart.addBodyPart(image);

            MimeMessage messages = new MimeMessage(session);
            messages.setFrom(new InternetAddress(correoEmi));
            messages.addRecipient(Message.RecipientType.TO,new InternetAddress(goCorreo));
            messages.setSubject(Asunto);
            messages.setContent(multipart);
            //messages.setText(message); // Texto solo

            Transport transport = session.getTransport("smtp");
            transport.connect(correoEmi,password);
            transport.sendMessage(messages, messages.getRecipients(Message.RecipientType.TO));
            transport.close();

            System.out.println("Mensaje Enviado");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
