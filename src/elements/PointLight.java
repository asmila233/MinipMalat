package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {


   //fields
    private Point3D position;
    private double kq;
    private double kl;
    private double kc;

    //setters
    public PointLight setKq(double kQ) {
        this.kq = kQ;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kl = kL;
        return this;
    }

    public PointLight setKc(double kC) {
        this.kc = kC;
        return this;
    }

//constructors
    /**
     * @param position
     * @param intensity_
     */
    public PointLight(Point3D position,Color intensity_) {
        super(intensity_);
        this.position=position;
        kq = 0;
        kl =0;
        kc =1;
    }

    /**
     * @param intensity_
     * @param position
     * @param kq
     * @param kl
     * @param kc
     */
    public PointLight(Color intensity_, Point3D position, double kq, double kl, double kc) {
        super(intensity_);
        this.position = position;
        this.kq = kq;
        this.kl = kl;
        this.kc = kc;
    }

    // the implements of the interface
    @Override
    public Color getIntensity(Point3D p) {
        double d = position.distance(p);
        double k = kc + kl *d+ kq *d*d;
        return getIntensity().scale(1/k);
    }

    /**
     *
     * @param p the palace who the light come
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return  p.subtract(position).normalize();
    }

    /**
     * returns the actual distance between the point and the head of the light
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }
}
