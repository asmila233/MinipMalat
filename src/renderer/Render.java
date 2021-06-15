package renderer;

import elements.Camera;
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
public class Render  {
    ImageWriter image;
    Camera cam;
    RayTracerBasic rayTracerBasic;

    public Render setRayTracer(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;return this;
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
     *     creates a ray for each pixel and checks what color there is on that pixel and writes it to the image
     *     after the improvement the function sends various rays per pixel.
     */
    public void renderImage()
    {
        if (image ==null||cam==null||rayTracerBasic==null)
        {
            throw new MissingResourceException("error",this.getClass().toString(),"error");
        }
        int x = image.getNx();
        int y = image.getNy();
        for (int i=0;i<x;i++)
            for(int j=0;j<y;j++)
            {
                var rays = cam.constructsRaysThroughPixel(x,y,i,j);
                var col= calcAverageColor(rays);
                //var ray = cam.constructRayThroughPixel(x,y,i,j);
                //var col = rayTracerBasic.traceRay(ray);
                image.writePixel(i,j,col);
            }
    }


    /**
     * his responsible is to take a list of rays and calculate average of they color
     * @param list the list of ray
     * @return the average color of the ray
     */
    private Color calcAverageColor(List<Ray> list)
    {
        int size = list.size();
        Color sum = new Color(0,0,0);
        for (var i :list) {
            sum = sum.add(rayTracerBasic.traceRay(i));
        }
        return  sum.scale((double) 1/size);
    }


    /**
     * puts a grid on the picture
     * @param interval how many rows how many columns
     * @param color the color of the lines making the grid
     */
    public void printGrid(int interval, Color color)
    {
        //do grid on picture
        if (image ==null)
        {
            //there is no image exist
            throw new MissingResourceException("error",this.getClass().toString(),"error");
        }
        if (interval<=0)
        {
            //incorrect number for grid
            throw new IllegalArgumentException("interval is zero");
        }
        //grt size
        int x = image.getNx();
        int y = image.getNy();
        //see where i should put grid and run on the times i need grid and put
        for (int i=1; i<x/interval; i++)
            //run all the line
            for (int j=0;j<y;j++)
            image.writePixel(i*interval,j,color);
        for (int i=1;i<y/interval;i++)
            //run all the line
            for (int j=0;j<x;j++)
                image.writePixel(j,i*interval,color);

    }

    /**
     * puts the image in a file
     */
    public void writeToImage()
    {
        if (image==null)
            throw new IllegalArgumentException("image is unrest");
        image.writeToImage();
    }
}
