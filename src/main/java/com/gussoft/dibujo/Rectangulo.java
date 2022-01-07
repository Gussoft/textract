package com.gussoft.dibujo;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Rectangulo extends JPanel{

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.drawRect(10, 10, 80, 30);
        g.drawRoundRect(100, 10, 80, 30, 15, 15);

        int thickness = 4;

        for (int i = 0; i <= thickness; i++)
            g.draw3DRect(200 - i, 10 - i, 80 + 2 * i, 30 + 2 * i, true);
        for (int i = 0; i < thickness; i++)
            g.draw3DRect(200 - i, 50 - i, 80 + 2 * i, 30 + 2 * i, false);

        g.drawOval(10, 100, 80, 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("DrawRect");
        frame.setSize(300, 200);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                System.out.println("Perro");
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.add(new Rectangulo());

        frame.show();
    }
}
