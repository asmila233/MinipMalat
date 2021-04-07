package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube implements Geometry{
    protected Ray axisRay;
    protected double radious;


    @Override
    public Vector getNormal(Point3D po) {
        var p0= axisRay.getPo();
        var v0= axisRay.getDir();
        var v = po.subtract(p0);
        var t= v.dotProduct(v0);
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
}
