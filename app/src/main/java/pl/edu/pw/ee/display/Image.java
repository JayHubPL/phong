package pl.edu.pw.ee.display;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Image implements Shape {
    
    private BufferedImage bufImg;

    public Image(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public void paintShape(Graphics2D g2) {
        g2.drawImage(bufImg, 0, 0, null);
    }
    
}
