package pl.edu.pw.ee;

import java.util.Scanner;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Canvas;
import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.Vector;

public class App {
    
    private static final Point lightInitialPosition = new Point(150, 0, 150);
    private static final Vector lightColor = new Vector(1, 1, 1);
    private static final int ballRadius = 100;
    private static final SimpleMatrix rotationMatrix = calculateRotationMatrix(Math.toRadians(2));
    private static final int FRAME_DELAY = 50;

    public static void main(String[] args) throws InterruptedException {
        LightSource light = new LightSource(lightInitialPosition, lightColor);
        Material material = chooseMaterial();
        PhongBall ball = new PhongBall(ballRadius, material, light);
        while (true) {
            Canvas.getInstance().show(ball.toImage());
            light.rotate(rotationMatrix);
            Thread.sleep(FRAME_DELAY);
        }   
    }

    private static Material chooseMaterial() {
        try (Scanner input = new Scanner(System.in)) {
            return switch (input.nextInt()) {
                case 1 -> Material.SHINY_MATERIAL;
                case 2 -> Material.SEMI_SHINY_MATERIAL;
                case 3 -> Material.SEMI_MATTE_MATERIAL;
                default -> Material.MATTE_MATERIAL;
            };
        }
    }

    private static SimpleMatrix calculateRotationMatrix(double angle) {
        return new SimpleMatrix(new double[][] {
            {Math.cos(angle), -Math.sin(angle), 0.},
            {Math.sin(angle), Math.cos(angle), 0.},
            {0., 0., 1.}
        }).mult(new SimpleMatrix(new double[][] {
            {Math.cos(angle), 0., Math.sin(angle)},
            {0., 1., 0.},
            {-Math.sin(angle), 0., Math.cos(angle)}
        }));
    }

}
