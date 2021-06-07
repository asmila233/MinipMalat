package primitives;

import java.util.Objects;

public class Point3D {

    public static final Point3D ZERO = new Point3D(0,0,0);
    Coordinate x;
    Coordinate y;
    Coordinate z;

    /**
     * construct a new point with the given coordinates.
     * @param _x
     * @param _y
     * @param _z
     */
    public Point3D(Coordinate _x, Coordinate _y, Coordinate _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    /**
     * turn the double numbers to coordinate and then construct a point
     * @param _x
     * @param _y
     * @param _z
     */
    public Point3D(double _x, double _y, double _z) {
        x = new Coordinate(_x);
        y = new Coordinate(_y);
        z = new Coordinate(_z);
    }

    /**
     * adds each coordinate of the vector to each coordinate of the point.
     * @param vec
     * @return
     */
    public Point3D add (Vector vec)
    {
        var p1 = vec.getHead();
        var x1 =  p1.x.coord + this.x.coord;
        var x2 =  p1.y.coord + this.y.coord;
        var x3 =  p1.z.coord + this.z.coord;

        return new Point3D(x1,x2,x3);
    }

    /**
     * returns a vector with the subtracted coordinates
     * @param po
     * @return
     */
    public Vector subtract (Point3D po)
    {
        var x1 =  this.x.coord - po.x.coord;
        var x2 =  this.y.coord - po.y.coord;
        var x3 =  this.z.coord - po.z.coord;

        return new Vector(x1,x2,x3);
    }

    /**
     * returns the squared distance from a given point to this point.
     * @param po
     * @return
     */
    public double distancesquared (Point3D po)
    {
          var x1 = (po.x.coord - this.x.coord);
          var x2 = (po.y.coord - this.y.coord);
          var x3 = (po.z.coord - this.z.coord);

          return (x1*x1 + x2*x2 + x3*x3);
    }

    /**
     * returns the squared root of the previous func distancesquared
     * @param po
     * @return
     */
    public double distance (Point3D po)
    {
          return Math.sqrt(distancesquared(po));
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point3D point3D = (Point3D) o;
        return x.equals(point3D.x) && y.equals(point3D.y) && z.equals(point3D.z);
    }
//getters
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    public Coordinate getZ() {
        return z;
    }
}
