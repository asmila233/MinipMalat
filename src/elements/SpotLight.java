package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import java.time.temporal.ValueRange;

public class SpotLight extends PointLight{
    Vector direction;

    /**
     * @param position
     * @param intensity_
     * @param direction
     */
    public SpotLight(Point3D position, Color intensity_, Vector direction) {
        super(position, intensity_);
        this.direction = direction.normalize();
    }

    /**
     * @param intensity_
     * @param position
     * @param kQ
     * @param kL
     * @param kC
     * @param direction
     */
    public SpotLight(Color intensity_, Point3D position, double kQ, double kL, double kC, Vector direction) {
        super(intensity_, position, kQ, kL, kC);
        this.direction = direction.normalize();
    }

    /**
     *
     * @param p
     * @return the repeat color
     */
    @Override
    public Color getIntensity(Point3D p) {
        var color= super.getIntensity(p);
        var l = super.getL(p).normalize();
        var number= direction.dotProduct(l);
        double select= Math.max(number,0);

        return (color.scale(select));
    }


}
