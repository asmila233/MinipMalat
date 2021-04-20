package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
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
        Point3D P0 = R.getPo();
        var vec = R.getDir();
        Vector u;
        var ret = new ArrayList<Point3D>();
        try {
            u= center.subtract(P0);
        }
        catch (Exception ex)
        {
            //zero distance
            //in the middle of sphere
            ret.add(R.getPoint(radious));
            return ret;
        }

        var tm = Util.alignZero(u.dotProduct(vec));

        double d = Util.alignZero(Math.sqrt(Util.alignZero(u.lengthSquared()-(tm*tm))));

        if (d>=radious)
            return null;
        double th = Util.alignZero(Math.sqrt(Util.alignZero(radious*radious-(d*d))));

        double t1=Util.alignZero(tm+th);
        double t2=Util.alignZero(tm-th);

        if (t1>0)
            ret.add(R.getPoint(t1));
        if (t2>0)
            ret.add(R.getPoint(t2));
        if (ret.size()>0)
            return ret;
        return null;
    }
}
