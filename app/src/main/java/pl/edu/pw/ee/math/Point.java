package pl.edu.pw.ee.math;

import org.ejml.simple.SimpleMatrix;

public class Point extends SimpleMatrix {

    public Point(double x, double y, double z) {
        super(new double[][] {
            {x},
            {y},
            {z}
        });
    }

    protected Point(double[][] mat) {
        super(mat);
    }

    public static Point fromMatrix(SimpleMatrix mat) {
        return new Point(mat.get(0), mat.get(1), mat.get(2));
    }

    public double x() {
        return get(0);
    }

    public double y() {
        return get(1);
    }

    public double z() {
        return get(2);
    }

}