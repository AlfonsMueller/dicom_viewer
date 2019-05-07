package at.mueller.alfons;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel {
    private BufferedImage img;

    public ImagePanel(){
        try {
            dicom_reader dr = new dicom_reader();
            img= ImageIO.read(new File("image2.jpeg"));
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null)
            g.drawImage(img, 0, 0, this);
    }
}
