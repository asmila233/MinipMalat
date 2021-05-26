package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    private Vector direction;


    /**
     * @param direction
     * @param intensity_
     */
    protected DirectionalLight(Vector direction,Color intensity_) {
        super(intensity_);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point3D p) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }
}
