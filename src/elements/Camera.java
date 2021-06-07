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

    /**
     * constructor with all the needed parameters' vectors and a starting point.
     * @param spot
     * @param to
     * @param up
     */
    public Camera(    Point3D spot,Vector to, Vector up) {
        this.spot= spot;
        this.up = up;
        this.to = to;
        if (!Util.isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("illegal parameters");
        right = to.crossProduct(up).normalize();
    }

    //getters
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

    // setters

    public Camera setViewPlaneSize(double width, double height)
    {
        this.width = width;
        this.height = height;
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
        //--------
        //do due the algorithm in "Recitation 4 - Camera, Ray Casting - upd. 23/04/21,page 12" at model
        //--------

        //Image center
        Point3D Pc= spot.add(to.scale(distance));

        //Ratio (pixel width & height)
        float Ry= (float) height/ nY;
        float Rx= (float) width/ nX;

        //Pixel[i,j] center

        float Yi = (float) -(i-(float)(nY-1)/2)*Ry;
        float Xj = (float) (j-(float)(nX-1)/2)*Rx;
        Point3D pIj= Pc;
        if (!Util.isZero(Xj))
             pIj= pIj.add(right.scale(Xj));
        if (!Util.isZero(Yi))
            pIj= pIj.add(up.scale(Yi));

        //𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏
        Vector dirOFray= pIj.subtract(spot);

        //return ray
        return  new Ray(spot,dirOFray);
    }

}
