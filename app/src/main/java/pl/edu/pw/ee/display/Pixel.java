package pl.edu.pw.ee.display;

import java.awt.Graphics2D;

public class Pixel implements Shape {

    private int x;
    private int y;
    private Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return 1;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public void paintShape(Graphics2D g2) {
        if (color != null) {
            g2.setColor(new java.awt.Color((int) color.getRed(), (int) color.getGreen(), (int) color.getBlue()));
            g2.fillRect(x, y, 1, 1);
        }
    }
    
}
