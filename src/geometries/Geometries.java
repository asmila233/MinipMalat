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

    public void add(Intersectable geometries)
    {
        list.add(geometries);
    }

/*    @Override
    public List<GeoPoint> findIntersections(Ray R) {
       if (list.isEmpty()){
           return null;
       }
       List <Point3D> result = new ArrayList<>();
       List <Point3D> assist = new ArrayList<>();
       for(int i = 0; i<list.size(); i++){
           var x = list.get(i).findIntersections(R);
           if(x != null) {
               result.addAll(x);
           }
       }
if (result.size()==0) return null;
       return result;

    }*/

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


    public List<GeoPoint> findGeoIntersections(Ray lightRay, double distance, LightSource light) {
        var list = findGeoIntersections(lightRay);
        if (Double.isFinite(distance))
            return list;//is direction light and his always infinity
        List<Intersectable.GeoPoint> ret= new ArrayList<>();
        for (var p:list) {
            if (light.getDistance(p.point)<=distance)
                ret.add(p);
        }
        return ret;
    }
}
