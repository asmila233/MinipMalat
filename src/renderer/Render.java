package renderer;

import elements.Camera;
import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * this class take sence and make from then picture
 */
public class Render {
    ImageWriter image;
    Camera cam;
    RayTracerBasic rayTracerBasic;
    private static final int MAX_OF_LEVEL_OF_PIXEL = 3;
    //1->3X3
    //2->5X5
    //3->9X9
    //N->A(n-1)*2-1

    public Render setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;
        return this;
    }

    public Render setCamera(Camera cam) {
        this.cam = cam;
        return this;
    }

    public Render setImageWriter(ImageWriter image) {
        this.image = image;
        return this;
    }

    /**
     * creates a ray for each pixel and checks what color there is on that pixel and writes it to the image
     * after the improvement the function sends various rays per pixel.
     *
     * @param flag 0-> Without improvement
     *             1-> super sampling improvement
     *             else->  super sampling Adaptable improvement
     */
    public void renderImage(int flag) {
        if (image == null || cam == null || rayTracerBasic == null) {
            throw new MissingResourceException("error", this.getClass().toString(), "error");
        }
        int x = image.getNx();
        int y = image.getNy();
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                Color col = new Color(0, 0, 0);

                if (flag == 0) {
                    //Without improvement
                    var ray = cam.constructRayThroughPixel(x, y, i, j);
                    col = rayTracerBasic.traceRay(ray);
                } else if (flag == 1) {
                    var rays = cam.constructsRaysThroughPixel(x, y, i, j);
                    col = calcAverageColor(rays);
                } else {
                    col = calcColorOfPixel(x, y, i, j);
                }

                image.writePixel(i, j, col);
            }
    }


    /**
     * his responsible is to take a list of rays and calculate average of they color
     *
     * @param list the list of ray
     * @return the average color of the ray
     */
    private Color calcAverageColor(List<Ray> list) {
        int size = list.size();
        Color sum = new Color(0, 0, 0);
        for (var i : list) {
            sum = sum.add(rayTracerBasic.traceRay(i));
        }
        return sum.scale((double) 1 / size);
    }


    /**
     * puts a grid on the picture
     *
     * @param interval how many rows how many columns
     * @param color    the color of the lines making the grid
     */
    public void printGrid(int interval, Color color) {
        //do grid on picture
        if (image == null) {
            //there is no image exist
            throw new MissingResourceException("error", this.getClass().toString(), "error");
        }
        if (interval <= 0) {
            //incorrect number for grid
            throw new IllegalArgumentException("interval is zero");
        }
        //grt size
        int x = image.getNx();
        int y = image.getNy();
        //see where i should put grid and run on the times i need grid and put
        for (int i = 1; i < x / interval; i++)
            //run all the line
            for (int j = 0; j < y; j++)
                image.writePixel(i * interval, j, color);
        for (int i = 1; i < y / interval; i++)
            //run all the line
            for (int j = 0; j < x; j++)
                image.writePixel(j, i * interval, color);

    }

    /**
     * puts the image in a file
     */
    public void writeToImage() {
        if (image == null)
            throw new IllegalArgumentException("image is unrest");
        image.writeToImage();
    }

    /**
     * project 2- update of super sampling
     *
     * @author y_elnatan
     * the idea is make 2 function who make recursia
     * The idea is to create two functions
     * Which are a recursion
     * <p>
     * The first function is responsible for the body of the recursion
     * While the second is helped so we get
     * The pixel and send rays if they are equal
     * In their color return the color otherwise send
     * For the second function that divides the pixel by 4
     * And sends an average of the color in each small pixel
     * The calculation is done by the first function.
     * <p>
     * on thier function this lettes
     * <p>
     * this is a pixel
     * ->
     * a---b---c
     * |||||||||
     * d---e---f
     * |||||||||
     * g---h---i
     */
    private Color calcColorOfPixel(int x, int y, int i, int j) {
        List<Point3D> point4 = cam.pointsOfPixel(x, y, i, j);
        return calcColorOfPixelsOnSuperSamplingUpdate(point4.get(0), point4.get(1), point4.get(2), point4.get(3), 0);
    }

    /**
     * @param a
     * @param c
     * @param g
     * @param i
     * @param level
     * @return the color of pixel
     */
    private Color calcColorOfPixelsOnSuperSamplingUpdate(Point3D a, Point3D c, Point3D g, Point3D i, int level) {
        var aGeo = getGeometry(a);
        var cGeo = getGeometry(c);
        var gGeo = getGeometry(g);
        var iGeo = getGeometry(i);

        double distanceX = a.distance(c);
        double distanceY = a.distance(g);
        Vector addXAxis = c.subtract(a).normalize().scale(distanceX / 2.0);
        Vector addYAxis = g.subtract(a).normalize().scale(distanceY / 2.0);

        var e = a.add(addXAxis).add(addYAxis);

        // we cant develop forever and we must to do average
        if (MAX_OF_LEVEL_OF_PIXEL == level) {
            return getColorOfPoint(e);
        }
        // its okay because all the color is equals
        else if (aGeo == null && cGeo == null && gGeo == null && iGeo == null) {
            return new Color(java.awt.Color.black);
        } else if (aGeo == null || cGeo == null || gGeo == null || iGeo == null) {
            return splitPixelTo4Pixels(a, c, g, i, level + 1);
        } else if (aGeo.equals(cGeo) && aGeo.equals(gGeo) && a.equals(iGeo)) {
            return getColorOfPoint(e);
        } else {
            return splitPixelTo4Pixels(a, c, g, i, level + 1);
        }

    }

    /**
     * @param a
     * @param c
     * @param g
     * @param i
     * @param level
     * @return
     */
    private Color splitPixelTo4Pixels(Point3D a, Point3D c, Point3D g, Point3D i, int level) {
        double distanceX = a.distance(c);
        double distanceY = a.distance(g);
        Vector addXAxis = c.subtract(a).normalize().scale(distanceX / 2.0);
        Vector addYAxis = g.subtract(a).normalize().scale(distanceY / 2.0);

        var b = a.add(addXAxis);
        var d = a.add(addYAxis);
        var e = a.add(addXAxis).add(addYAxis);
        var f = c.add(addYAxis);
        var h = g.add(addXAxis);

        var col1 = calcColorOfPixelsOnSuperSamplingUpdate(a, b, d, e, level);
        var col2 = calcColorOfPixelsOnSuperSamplingUpdate(b, c, e, f, level);
        var col3 = calcColorOfPixelsOnSuperSamplingUpdate(d, e, g, h, level);
        var col4 = calcColorOfPixelsOnSuperSamplingUpdate(e, f, h, i, level);
        return col1.add(col3, col2, col4).scale(0.25);
    }

    /**
     * @param p the point on the view plane
     * @return the color of the ray
     */
    private Color getColorOfPoint(Point3D p) {
        var r = cam.getRayForPointOnTheViewPlane(p);
        return rayTracerBasic.traceRay(r);
    }

    /**
     * @param p the point on the view plane
     * @return find the Closest Intersection of the ray
     */
    private Geometry getGeometry(Point3D p) {
        var r = cam.getRayForPointOnTheViewPlane(p);
        var ret = rayTracerBasic.findClosestIntersection(r);
        if (ret == null)
            return null;
        else return ret.geometry;
    }


    /**
     * An interface by which the user can view the image according to the enhancements he wants
     */

    /**
     * Without improvement
     */
    public void renderImage() {
        renderImage(0);
    }

    /**
     *  super sampling improvement
     */
    public void renderImageWithImageEnhancement()
    {
        renderImage(1);
    }

    /**
     *  super sampling Adaptable improvement
     */
    public void  renderImageWithImprovedRunningTime()
    {
        renderImage(2);
    }

}
