package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable {
    
    abstract public Vector getNormal(Point3D po);
    
    protected Color emission = Color.BLACK;
    public Color getEmission() {
        return emission;
    }

    public abstract Intersectable setEmission(Color color);
}

