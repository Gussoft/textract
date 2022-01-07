package com.gussoft.textract;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.common.views.AbstractPageViewComponent;
import org.icepdf.ri.common.views.DocumentViewController;
import org.icepdf.ri.common.views.DocumentViewModel;

import javax.swing.*;
import java.awt.*;


public class pdfviewer {
    public pdfviewer(DocumentViewController documentViewController,
                     AbstractPageViewComponent pageViewComponent,
                     DocumentViewModel documentViewModel) {

    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle!");
        String filePath = "C:\\Users\\LENOVO\\Desktop\\businessIT.pdf";
        String file = "C:\\Users\\LENOVO\\Desktop\\F-Ejemplo 1.pdf";
        VerPdf(filePath);
        //viewPdf(filePath);
    }

    public static void VerPdf(String filePath) {

        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);
        /*PropertiesManager properties = new PropertiesManager(System.getProperties(),
                ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_FIT, "false");
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_ROTATE, "false");
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_TOOL, "false");
        properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "1.25");
        properties.setBoolean(PropertiesManager.PROPERTY_SHOW_STATUSBAR_VIEWMODE, Boolean.FALSE);
        properties.set(PropertiesManager.PROPERTY_SHOW_TOOLBAR_PAGENAV, "false");
        ResourceBundle messageBundle = ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE);
        new FontPropertiesManager(properties, System.getProperties(), messageBundle);*/

        JPanel viewerComponentPanel = factory.buildViewerPanel();
        //viewerComponentPanel.
        viewerComponentPanel.setPreferredSize(new Dimension(700, 700));
        viewerComponentPanel.setMaximumSize(new Dimension(700, 700));
        //viewerComponentPanel.setBorder(BorderFactory.createTitledBorder(
        //        BorderFactory.createEtchedBorder(), "BigPrime"));
                //setBackground(Color.green);
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        applicationFrame.add(viewerComponentPanel, BorderLayout.CENTER);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        //applicationFrame.add(viewerComponentPanel);
        applicationFrame.invalidate();

        controller.openDocument(filePath);

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }

    public static void viewPdf(String file){
        // Instance the controller
        SwingController controller = new SwingController();
// We created the SwingViewFactory configured with the controller
        SwingViewBuilder factory = new SwingViewBuilder(controller);
// We use the factory to build a preconfigured JPanel
// with a full and active viewer user interface.
        JPanel viewerComponentPanel = null;
        viewerComponentPanel = factory.buildViewerPanel();
        viewerComponentPanel.setPreferredSize(new Dimension(700, 700));
        viewerComponentPanel.setMaximumSize(new Dimension(700, 700));
// We add keyboard command
        ComponentKeyBinding.install(controller, viewerComponentPanel);
// add interactive mouse link annotation support via callback
        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

// We add the component to visualize the report
        JFrame reportViewerContainer = new JFrame();
        reportViewerContainer.add(viewerComponentPanel, BorderLayout.CENTER);
        reportViewerContainer.invalidate();
// We open the generated document
        controller.openDocument(file);
        reportViewerContainer.pack();
        reportViewerContainer.setVisible(true);
    }
/*
    public void mouseMoved(MouseEvent e) throws InterruptedException {
        // change state of mouse from pointer to text selection icon
        Page currentPage = pageViewComponent.getPage();
        selectionMouseCursor(currentPage, e.getPoint());
    }

    private void selectionMouseCursor(Page currentPage, Point mouseLocation) throws InterruptedException {
        if (currentPage != null &&
                currentPage.isInitiated()) {
            // get page text
            PageText pageText = currentPage.getViewText();
            if (pageText != null) {

                // get page transform, same for all calculations
                AffineTransform pageTransform = currentPage.getPageTransform(
                        Page.BOUNDARY_CROPBOX,
                        documentViewModel.getViewRotation(),
                        documentViewModel.getViewZoom());

                ArrayList<LineText> pageLines = pageText.getPageLines();
                boolean found = false;
                Point2D.Float pageMouseLocation =
                        convertMouseToPageSpace(mouseLocation, pageTransform);
                for (LineText pageLine : pageLines) {
                    // check for containment, if so break into words.
                    if (pageLine.getBounds().contains(pageMouseLocation)) {
                        found = true;
                        documentViewController.setViewCursor(
                                DocumentViewController.CURSOR_TEXT_SELECTION);
                        break;
                    }
                }
                if (!found) {
                    documentViewController.setViewCursor(
                            DocumentViewController.CURSOR_SELECT);
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) {
        // double click we select the whole line.
        if (e.getClickCount() == 3) {
            Page currentPage = pageViewComponent.getPage();
            // handle text selection mouse coordinates
            Point mouseLocation = (Point) e.getPoint().clone();
            lineSelectHandler(currentPage, mouseLocation);
        }
        // single click we select word that was clicked.
        else if (e.getClickCount() == 2) {
            Page currentPage = pageViewComponent.getPage();
            // handle text selection mouse coordinates
            Point mouseLocation = (Point) e.getPoint().clone();
            wordSelectHandler(currentPage, mouseLocation);
        }
        // write out selected text.
        if (logger.isLoggable(Level.FINE)) {
            Page currentPage = pageViewComponent.getPage();
            // handle text selection mouse coordinates
            logger.fine(currentPage.getViewText().getSelected().toString());
        }

        documentViewController.clearSelectedAnnotations();
        if (pageViewComponent != null) {
            pageViewComponent.requestFocus();
        }

    }


    public void mousePressed(MouseEvent e) {

        documentViewController.clearSelectedText();
        selectedCount = 0;

        // text selection box.
        int x = e.getX();
        int y = e.getY();
        currentRect = new Rectangle(x, y, 0, 0);
        updateDrawableRect(pageViewComponent.getWidth(), pageViewComponent.getHeight());
        pageViewComponent.repaint();
    }*/
}
