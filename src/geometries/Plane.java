package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry{
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

        // preventing a 0 vector to be produced
        if ((this.p0.getX().getCoord()) == (R.getPo().getX().getCoord()) &&
                (this.p0.getY().getCoord()) == (R.getPo().getY().getCoord())&&
                (this.p0.getZ().getCoord()) == (R.getPo().getZ().getCoord())) {
            return null;
        }
        Vector sub = new Vector(this.p0.getX().getCoord() - R.getPo().getX().getCoord(),
                this.p0.getY().getCoord() - R.getPo().getY().getCoord(),
                this.p0.getZ().getCoord() - R.getPo().getZ().getCoord());

        Vector N = new Vector(this.getNormal().getHead());

        double t = (N.dotProduct(sub) / N.dotProduct(R.getDir()));
        List<Point3D> result = new ArrayList<>();
        // in a cse of an infinity value in t
        if (t == t/2) {
            return null;
        }
        if(t > 0){
        Point3D res = R.getPo();
        res = res.add((R.getDir().scale(t)));

        result.add(res);
        return result;
        }
        return null;
    }

}
