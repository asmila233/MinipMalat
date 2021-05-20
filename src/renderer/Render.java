package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * this class take sence and make from then picture
 */
public class Render  {
    ImageWriter print;
    Camera cam;
    RayTracerBasic rayTracerBasic;


    public Render setCam(Camera cam) {
        this.cam = cam;
        return this;
    }

    public Render setPrint(ImageWriter print) {
        this.print = print;
        return this;
    }
}
