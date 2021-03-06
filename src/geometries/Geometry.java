package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public abstract class Geometry implements Intersectable {
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    Material material=new Material();

    public Material getMaterial() {
        return material;
    }

    abstract public Vector getNormal(Point3D po);
    // default value of the emission
    protected Color emission = Color.BLACK;
    //getter
    public Color getEmission() {
        return emission;
    }
    //setter
    public Geometry setEmission(Color color)
    {
        emission=color;
        return this;
    };
}

