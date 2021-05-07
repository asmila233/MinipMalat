package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

public class Camera {

    private Point3D spot;
    private Vector up;
    private Vector to;
    private Vector right;

    public Camera(    Point3D spot,Vector up, Vector to) {
        this.spot= spot;
        this.up = up;
        this.to = to;
        if (Util.isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("illegal parameters");
        right = to.crossProduct(up).normalize();
    }

    //getter
    public Vector getUp() {
        return up;
    }
    public Vector getTo() {
        return to;
    }
    public Vector getRight() {
        return right;
    }
    public Point3D getSpot() {
        return spot;
    }
    //--------------
    //view plane
    //--------------
    private  double distance;
    private double width;
    private double height;

    // setter

    public Camera setViewPlaneSize(double width, double height)
    {
        this.width = width;
        this.height=height;
        return this;
    }

    public Camera setDistance(double distance)
    {
        this.distance=distance;
        return this;
    }

    //getter
    public double getDistance() {
        return distance;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    //methods

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i)
    {
        return null;
    }

}
