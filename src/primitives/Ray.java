package primitives;

import geometries.Intersectable.GeoPoint;
import geometries.Polygon;


import java.util.List;
import java.util.Objects;

public class Ray {
    Point3D po;
    Vector dir;

    /**
     * constructor, puts the normalize vector and a point in the ray
     * @param po
     * @param vec
     */
    public Ray(Point3D po, Vector vec) {
        var nirmol = vec.length();

        dir = new Vector((vec.head.x.coord) / nirmol, (vec.head.y.coord) / nirmol, (vec.head.z.coord) / nirmol);
        this.po = po;
    }

    /**
     * returns the multipication of a point with a number
     * @param t
     * @return
     */
    public Point3D getPoint(double t)
    {
        if (t<0)
            throw new IllegalArgumentException("negative number");
        dir.scale(t);
        var x = po.add(dir.scale(t));
        return x;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return po.equals(ray.po) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "po=" + po +
                ", dir=" + dir +
                '}';
    }

    /**
     * finds the closest point out of a list of points
     * @param list
     * @return
     */
    public Point3D findClosestPoint(List<Point3D> list){
        if(list.size() == 0)
            return null;
        double check = (po.distance(list.get(0)));
        Point3D result = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if(po.distance(list.get(i)) < check){
                check = po.distance(list.get(i));
                result = list.get(i);
            }
        }
        return result;
    }

    /**
     * finds the closest Geopoint to the head of the ray
     * @param list
     * @return
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> list){
        if(list.size() == 0)
            return null;
        double check = (po.distance(list.get(0).point));
        GeoPoint result = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if(po.distance(list.get(i).point) < check){
                check = po.distance(list.get(i).point);
                result = list.get(i);
            }
        }
        return result;
    }
//getters
    public Point3D getPo() {
        return po;
    }

    public Vector getDir() {
        return dir;
    }
}
