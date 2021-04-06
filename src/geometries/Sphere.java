package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry {

    protected Point3D center;
    protected double radious;

    public Sphere(Point3D center, double radious) {
        center = center;
        radious = radious;
    }

    @Override
    public Vector getNormal(Point3D po) {
        return null;
    }

    public Point3D getCenter() {
        return center;
    }

    public double getRadious() {
        return radious;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radious=" + radious +
                '}';
    }
}
