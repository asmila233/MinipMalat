package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    protected Ray axisRay;
    protected double radious;


    @Override
    public Vector getNormal(Point3D po) {
        return null;
    }

    public Tube(Ray axisRay, double radious) {
        this.axisRay = axisRay;
        this.radious = radious;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadious() {
        return radious;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radious=" + radious +
                '}';
    }
}
