package com.gussoft.message;

import javax.swing.*;
import java.net.URL;
import java.util.Enumeration;

public class MessageBS {
    public static void main(String[] args) {
        MessageBS message = new MessageBS();
        System.out.println("Welcome to the Jungle!");
        //getClass().getResource(path).toString()
        ImageIcon icon = new ImageIcon("warn.png");//"src/images/turtle64.png");
        message.ShowMessageBS("Error info", "prueba 1", 0, icon);

    }

    private ImageIcon image(String path) {
        return new ImageIcon(getClass().getResource(path).toString());
    }

    /**
     * @icon = 0, icon Err
     * @icon = 1, icon info
     * @icon = 2, icon warn
     * @icon = 3, icon quest
     * */
    public void ShowMessageBS(String title, String body, int icon, ImageIcon image){
        JOptionPane.showMessageDialog(null,body, title, icon, image);
    }
}
