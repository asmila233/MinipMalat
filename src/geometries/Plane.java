package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        norma =  first.crossProduct(second).normalize();
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

    @Override
    public List<Point3D> findIntersections(Ray R) {
        Vector v = R.getDir();
        Point3D p = R.getPo();
        var dot = v.dotProduct(norma);
        if (Util.isZero(dot))
        {
            // This ray is either contained or parallel to the plane
            return null;
            // we cant return enfants points
        }
        else if (p.equals(p0))
        {
                 List<Point3D> list = new ArrayList<Point3D>();
                 list.add(p0);
                 return list;
        }
        else
        {
              var mone = p0.subtract(p).dotProduct(v);
              List<Point3D> list = new ArrayList<Point3D>();
              var t = mone/dot;
              if (t>=0)
                  list.add(R.getPoint(t));
              else
                  return null;
              return list;
        }
    }
}
