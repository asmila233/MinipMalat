package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

public interface Intersectable {
    /**
     * calculates the intersections of the ray with the geometries.
     * @param R
     * @return
     */
    List<Point3D> findIntersections(Ray R);

}
