package es.spring.deery.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Images {

    public static BufferedImage toImage(byte[] data) {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        BufferedImage img = null;
        try {
            img = ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }
}
