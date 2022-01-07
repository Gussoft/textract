package com.gussoft.dibujo;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class createrec extends JComponent{
    private Point inicioArrastre;
    private Point finArrastre;
    private ArrayList<Shape> rectangulos = new ArrayList<Shape>();

    public createrec(JFrame applicationFrame, String doc) {
        super();
        SwingController controller = new SwingController();
        controller.setIsEmbeddedComponent(true);

        PropertiesManager properties = new PropertiesManager(System.getProperties(),
                ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
        properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "0.95f");
        SwingViewBuilder factory = new SwingViewBuilder(controller, properties);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        viewerComponentPanel.setPreferredSize(new Dimension(700, 700));
        viewerComponentPanel.setMaximumSize(new Dimension(700, 700));

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));
        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applicationFrame.setSize(600, 700);
        applicationFrame.setLocationRelativeTo(null);
        applicationFrame.getContentPane().add(viewerComponentPanel);

        applicationFrame.invalidate();
        controller.openDocument(doc);
        //controller.openFileInSomeViewer(null);
        //controller.openFile();
        applicationFrame.pack();
        applicationFrame.setVisible(true);

        addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) { // cuando se presiona el mouse
                inicioArrastre = new Point(e.getX(), e.getY());
                finArrastre = inicioArrastre;
                repaint();
            }

            public void mouseReleased(MouseEvent e) { // cuando se deja de presionar el mouse
                Shape rectangulo = crearRectangulo(inicioArrastre.x, inicioArrastre.y, e.getX(), e.getY());
                rectangulos.add(rectangulo);
                inicioArrastre = null;
                finArrastre = null;
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) { // cuando se esta arrastrando el mouse
                finArrastre = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.orange);

        for (Shape rectangulo : rectangulos) { // dibuja todos los rectangulos
            g2.fill(rectangulo);
        }
        if (inicioArrastre != null && finArrastre != null) { // se esta arrastrando el raton?
            Shape rectangulo = crearRectangulo(inicioArrastre.x, inicioArrastre.y, finArrastre.x, finArrastre.y);
            g2.draw(rectangulo);
        }
    }

    private Rectangle2D.Float crearRectangulo(int x1, int y1, int x2, int y2) {
        // ajusta el rectangulo
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    public static void main(String[] a3d) {
        /*JFrame ventana = new JFrame("Dibujar Rectangulos");
        ventana.setSize(600, 700);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(new createrec());
        ventana.setVisible(true);*/

        System.out.println("Welcome to the Jungle!");
        String filePath = "C:\\Users\\LENOVO\\Desktop\\demoS.pdf";
        String file = "C:\\Users\\LENOVO\\Desktop\\F-Ejemplo 1.pdf";
        VerPdf(filePath);
    }
    public static void VerPdf(String filePath) {


        JFrame applicationFrame = new JFrame("Vista Previa");


        //applicationFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //applicationFrame.add(viewerComponentPanel);//, BorderLayout.CENTER);



        //controller.openDocument(filePath);
        applicationFrame.add(new createrec(applicationFrame, filePath));

        // add the window event callback to dispose the controller and
        // currently open document.
        //applicationFrame.addWindowListener(controller);



    }
}
