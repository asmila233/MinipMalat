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
     * @return the direction of the light
     */
    @Override
    public Vector getL(Point3D p) {
        return direction;
    }

    /**
     * @param point
     * @return infinity
     */
    @Override
    public double getDistance(Point3D point) {
        // the distance from my to the sum or else direction light is infinity and this him define
        return Double.POSITIVE_INFINITY;
    }
}
