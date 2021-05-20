package renderer;
import primitives.Color;
import primitives.Ray;
import scene.*;

/**
 * this claas is abstract for render and his goal is to send ray to sence
 */
public abstract class RayTracerBase {
    Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    public abstract Color traceRay(Ray ray);
}
