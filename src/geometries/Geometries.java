package geometries;

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

    @Override
    public List<Point3D> findIntersections(Ray R) {
        return null;
    }
}
