package pl.edu.pw.ee;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;
import static pl.edu.pw.ee.display.DisplaySize.HALF_HEIGHT;
import static pl.edu.pw.ee.display.DisplaySize.HALF_WIDTH;
import static pl.edu.pw.ee.display.DisplaySize.HEIGHT;
import static pl.edu.pw.ee.display.DisplaySize.WIDTH;

import java.awt.image.BufferedImage;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.display.Color;
import pl.edu.pw.ee.display.Image;
import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.Vector;

public class PhongBall {

    private static final Point DEFAULT_BALL_POSITION = new Point(0., 0., 0.);
    private static final Point DEFAULT_CAMERA_POSITION = new Point(0., 0., 200.);

    private final Point cameraPosition;
    private final Point position;
    private final LightSource lightSource;
    private final int radius;
    private Material material;

    public PhongBall(int radius, Material material, LightSource lightSource, Point cameraPosition) {
        this.position = DEFAULT_BALL_POSITION;
        this.cameraPosition = cameraPosition;
        this.radius = radius;
        this.material = material;
        this.lightSource = lightSource;
    }

    public PhongBall(int radius, Material material, LightSource lightSource) {
        this(radius, material, lightSource, DEFAULT_CAMERA_POSITION);
    }

    public Image toImage() {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, TYPE_INT_ARGB);
        for (int x = -HALF_WIDTH; x < HALF_WIDTH; x++) {
            for (int y = -HALF_HEIGHT; y < HALF_HEIGHT; y++) {
                int currentPixelARGB = 0;
                double distance = Math.sqrt(x * x + y * y);
                if (distance < radius) {
                    Point P = new Point(x, y, Math.sqrt(radius * radius - distance * distance));
                    Vector I = calculateIllumination(P);
                    currentPixelARGB = Color.fromIllumination(I).toIntARGB();
                }
                img.setRGB(x + HALF_WIDTH, y + HALF_HEIGHT, currentPixelARGB);
            }
        }
        return new Image(img);
    }

    private Vector calculateIllumination(Point P) {
        Vector N = new Vector(position, P).normalize();
        Vector V = new Vector(P, cameraPosition).normalize();
        Vector L = new Vector(P, lightSource.getPosition()).normalize();
        Vector R = getReflection(N, L);
        if (V.dot(N) < 0) {
            return Vector.fromMatrix(ambient().plus(diffusion(N, L)));
        }
        return Vector.fromMatrix(ambient().plus(diffusion(N, L)).plus(specular(R, V)));
    }

    private static Vector getReflection(Vector normal, Vector light) {
        return Vector.fromMatrix(normal.scale(2 * normal.dot(light)).minus(light));
    }

    private SimpleMatrix ambient() {
        return lightSource.getColor().scale(material.k_a());
    }

    private SimpleMatrix diffusion(Vector normal, Vector light) {
        return lightSource.getColor().scale(Math.max(0., light.dot(normal)) * material.k_d());
    }

    private SimpleMatrix specular(Vector reflection, Vector viewer) {
        return lightSource.getColor().scale(Math.pow(Math.max(0., reflection.dot(viewer)), material.alpha()) * material.k_s());
    }

}
