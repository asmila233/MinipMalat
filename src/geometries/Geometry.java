package geometries;

import primitives.Point3D;
import primitives.Vector;

public interface Geometry extends Intersectable {
    abstract public Vector getNormal(Point3D po);
}

