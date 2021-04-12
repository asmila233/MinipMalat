package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere implements Geometry {

    protected Point3D center;
    protected double radious;

    public Sphere(Point3D center, double radious) {
        this.center = new Point3D(center.getX(),center.getY(),center.getZ());
        this.radious = radious;
    }

    @Override
    public Vector getNormal(Point3D po) {
        // זה פשוט הוקטור מהמרכז למעטפת
        return new Vector(po.subtract(center).getHead()).normalize();
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

    @Override
    public List<Point3D> findIntersections(Ray R) {
        return null;
    }
}
