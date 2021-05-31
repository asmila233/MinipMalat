package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class PointLight extends Light implements LightSource {


   //fields
    private Point3D position;
    private double kQ;
    private double kL;
    private double kC;

    //setters
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }

//constructors
    /**
     * @param position
     * @param intensity_
     */
    protected PointLight(Point3D position,Color intensity_) {
        super(intensity_);
        this.position=position;
        kQ= 0;
        kL=0;
        kC=1;
    }

    /**
     * @param intensity_
     * @param position
     * @param kQ
     * @param kL
     * @param kC
     */
    public PointLight(Color intensity_, Point3D position, double kQ, double kL, double kC) {
        super(intensity_);
        this.position = position;
        this.kQ = kQ;
        this.kL = kL;
        this.kC = kC;
    }

    // the implements of the interface
    @Override
    public Color getIntensity(Point3D p) {
        double d = position.distance(p);
        double k = kC+kL*d+kQ*d*d;
        return getIntensity().scale(1/k);
    }

    @Override
    public Vector getL(Point3D p) {
        return  p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }
}
