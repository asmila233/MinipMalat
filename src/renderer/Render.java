package renderer;

import elements.Camera;
import primitives.Color;

import java.util.MissingResourceException;

/**
 * this class take sence and make from then picture
 */
public class Render  {
    ImageWriter image;
    Camera cam;
    RayTracerBasic rayTracerBasic;

    public Render setRayTracerBasic(RayTracerBasic rayTracerBasic) {
        this.rayTracerBasic = rayTracerBasic;return this;
    }

    public Render setCam(Camera cam) {
        this.cam = cam;
        return this;
    }

    public Render setImage(ImageWriter image) {
        this.image = image;
        return this;
    }

    /**
     *     creates a ray for each pixel and checks what color there is on that pixel and writes it to the image
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
                var ray = cam.constructRayThroughPixel(x,y,i,j);
                var col= rayTracerBasic.traceRay(ray);
                image.writePixel(i,j,col);
            }
    }
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
    public void writeToImage()
    {
        if (image==null)
            throw new IllegalArgumentException("image is unrest");
        image.writeToImage();
    }
}
