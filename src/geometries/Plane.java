package geometries;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public class Plane implements Geometry{
    protected Point3D p0;
    protected Vector norma;


    @Override
    public Vector getNormal(Point3D p0) {
        return norma.normalize();
    }

    public Plane(Point3D A, Point3D B, Point3D C)
    {
        Vector first = A.subtract(B);
        Vector second = B.subtract(C);

        norma =  first.crossProduct(second);

        p0 = B;
    }
    public Plane(Point3D A, Vector norm)
    {
        norma =  norm;
        p0 = A;
    }


    public Vector getNormal() {
        return norma;
    }

    public Point3D getP0() {
        return p0;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "p0=" + p0 +
                ", norma=" + norma +
                '}';
    }
}
