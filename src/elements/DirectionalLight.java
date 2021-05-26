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

    /**
     *
     * @param p the palace who the light come
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        return this.getIntensity();
    }

    /**
     *
     * @param p the palace who the light come
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return direction;
    }
}