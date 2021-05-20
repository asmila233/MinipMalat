package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * this class goal is to throw ray and back color in the scene
 */
public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        return null;
    }
}
