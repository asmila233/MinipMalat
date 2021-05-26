package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * this is interface for  the type of light that have a direct
 */
public interface LightSource {
    /**
     * @param p the palace who the light come
     * @return the color on this light
     */
    public Color getIntensity(Point3D p);

    /**
     * @param p the palace who the light come
     * @return the vector of the light
     */
    public Vector getL(Point3D p);

}
