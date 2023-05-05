package pl.edu.pw.ee;

import org.ejml.simple.SimpleMatrix;

import pl.edu.pw.ee.math.Point;
import pl.edu.pw.ee.math.Vector;

public class LightSource {

    private Point position;
    private Vector color;
    
    public LightSource(Point position, Vector color) {
        this.position = position;
        this.color = color;
    }

    public Vector getColor() {
        return color;
    }

    public Point getPosition() {
        return position;
    }

    public void rotate(SimpleMatrix rotationMatrix) {
        position = Point.fromMatrix(rotationMatrix.mult(position));
    }

}
