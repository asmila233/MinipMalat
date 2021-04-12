package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

public class Tube implements Geometry{
    protected Ray axisRay;
    protected double radious;


    @Override
    public Vector getNormal(Point3D po) {
        var p0= axisRay.getPo();
        var v0= axisRay.getDir();
        if (p0.equals(po))
            return v0.scale(-1).normalize();
        var v = po.subtract(p0);
        var t= v.dotProduct(v0);
        if (Util.isZero(t)){
            return v0.scale(-1).normalize();
        }
        var p1 = p0.add(v0.scale(t));
        return po.subtract(p1).normalize();
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

    @Override
    public List<Point3D> findIntersections(Ray R) {
        return null;
    }
}
