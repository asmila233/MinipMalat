package geometries;

import elements.LightSource;
import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

public class Geometries implements Intersectable {
    private List<Intersectable> list;

    public Geometries() {
        list = new ArrayList<Intersectable>();
        // O(1) push and O(1) pull
    }

    public Geometries(Intersectable geometries) {
        list = new ArrayList<Intersectable>();
        // O(1) push and O(1) pull
        list.add(geometries);
    }

    public void add(Intersectable ... geometries)
    {
        for (var i :geometries) {
            list.add(i);
        }
    }

    /**
     * retruns a list of every intersection in the geometry.
     * @param R
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray R) {
        List<GeoPoint> result = new ArrayList<>();
        // to prevent an error in a case of an empty list.
        if(list.isEmpty())
            return null;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).findGeoIntersections(R) != null) {
                result.addAll(list.get(i).findGeoIntersections(R));
            }
        }
        return result.isEmpty() ? null : result;
    }

    /**
     * returns a list of geopoints containing all the intersections with geometries in the class that are before the light source.
     * @param lightRay
     * @param distance
     * @param light
     * @return
     */
    public List<GeoPoint> findGeoIntersections(Ray lightRay, double distance, LightSource light) {
        var list = findGeoIntersections(lightRay);
        List<Intersectable.GeoPoint> ret= new ArrayList<>();
        if (list==null)
                return ret;
        if (Double.isInfinite(distance))
            return ret;//is direction light and his always infinity
        // what we do here is that for each intersection we check if it is before the light or after
        for (var p:list) {
            if (light.getDistance(p.point)<=distance)
                ret.add(p);
        }
        return ret;
    }
}
