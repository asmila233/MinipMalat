package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;


public class Camera {

    private Point3D spot;
    private Vector up;
    private Vector to;
    private Vector right;
    private static final int x_rays_for_pixel = 20;
    private static final int y_rays_for_pixel = 20;

    public Camera( Point3D spot,Vector to, Vector up) {
        this.spot= spot;
        this.up = up;
        this.to = to;
        if (!Util.isZero(up.dotProduct(to)))
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

    /**
     * @param nY
     * @return the size of on pixel on y field
     */
    private float getSizeOfPixel_y(int nY)
    {
        return (float) height/ nY;
    }

    /**
     * @param nX
     * @return the size of on pixel on y field
     */
    private float getSizeOfPixel_x(int nX)
    {
        return (float) width/ nX;
    }

    /**
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return
     */
    private Point3D PixelIICenter(int nX, int nY, int j, int i)
    {
        //--------
        //do due the algorithm in "Recitation 4 - Camera, Ray Casting - upd. 23/04/21,page 12" at model
        //--------

        //Image center
        Point3D Pc= spot.add(to.scale(distance));

        //Ratio (pixel width & height)
        float Ry= getSizeOfPixel_y(nY);
        float Rx= getSizeOfPixel_x(nX);

        //Pixel[i,j] center

        float Yi = (float) -(i-(float)(nY-1)/2)*Ry;
        float Xj = (float) (j-(float)(nX-1)/2)*Rx;
        Point3D pIj= Pc;
        if (!Util.isZero(Xj))
            pIj= pIj.add(right.scale(Xj));
        if (!Util.isZero(Yi))
            pIj= pIj.add(up.scale(Yi));
        return pIj;
    }
    //methods
    /**
     * this responsible is to make a rays for one pixel
     * @param x
     * @param y
     * @param i
     * @param j
     * @return
     */
    public List<Ray> constructsRaysThroughPixel(int x, int y, int i, int j)
    {
        // the calculate is from the center and we need to move from -0.5 until 0.5
        // the grid of ray is 8*8
        // we need to know the vector of grid up and down
        var center = PixelIICenter( x,  y,  i,  j);
        var dis_y = getSizeOfPixel_y(y);
        var dis_x = getSizeOfPixel_x(x);
        var upLeft= center.add(up.scale(dis_y/2)).add(right.scale(-dis_x/2));
        var upRight= center.add(up.scale(dis_y/2)).add(right.scale(dis_x/2));
        var downLeft= center.add(up.scale(-dis_y/2)).add(right.scale(-dis_x/2));
        var downRight= center.add(up.scale(-dis_y/2)).add(right.scale(dis_x/2));
        return Util.GridOf4Point(spot,upLeft,upRight,downLeft,downRight,x_rays_for_pixel,y_rays_for_pixel);
    }
    public Ray constructRayThroughPixel(int nX, int nY, int i, int j)
    {
        //--------
        //do due the algorithm in "Recitation 4 - Camera, Ray Casting - upd. 23/04/21,page 12" at model
        //--------

        //Image center
        Point3D pIj = PixelIICenter( nX,  nY,  i,  j);

        //𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏
        Vector dirOFray= pIj.subtract(spot);

        //return ray
        return  new Ray(spot,dirOFray);
    }

}
