package pl.edu.pw.ee.math;

import org.ejml.simple.SimpleMatrix;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
    }

    public Vector(Point from, Point to) {
        super(to.x() - from.x(), to.y() - from.y(), to.z() - from.z());
    }

    public static Vector fromMatrix(SimpleMatrix mat) {
        return new Vector(mat.get(0), mat.get(1), mat.get(2));
    }

    public Vector cross(Vector other) {
        return new Vector(
            y() * other.z() - z() * other.y(),
            z() * other.x() - x() * other.z(),
            x() * other.y() - y() * other.x()
        );
    }

    public Vector bisectorWith(Vector other) {
        return fromMatrix(normalize().plus(other.normalize())).normalize();
    }

    public Vector normalize() {
        double len = Math.sqrt(x() * x() + y() * y() + z() * z());
        return fromMatrix(scale(1. / len));
    }
    
}