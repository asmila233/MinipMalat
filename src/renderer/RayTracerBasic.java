package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

/**
 * this class goal is to throw ray and back color in the scene
 */
public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     *check what intersectins there are from the scene, and if there are not any of them retuen the color of the background
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {

        var point3DS = scene.geometries.findGeoIntersections(ray);
        if(point3DS==null)
            return scene.getBackground();
        var close= ray.findClosestGeoPoint(point3DS);
        return calcColor(close);
    }
    private Color calcColor (GeoPoint p)
    {

        return scene.getAmbientLight().getIntensity().add(p.geometry.getEmission());
    }
}
