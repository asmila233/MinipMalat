package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;

import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * this class goal is to throw ray and back color in the scene
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * constant for moving the heads of shading rays.
     */
    private static final double DELTA = 0.1;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     *check what intersections there are from the scene, and if there are not any of them retuen the color of the background
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {

        var point3DS = scene.geometries.findGeoIntersections(ray);
        if(point3DS==null)
            return scene.getBackground();
        var close= ray.findClosestGeoPoint(point3DS);
        return calcColor(close,ray.getDir());
    }

    /**
     * return true if the point is not shaded and have a clean straight lint to lightsource
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.geometries
                .findGeoIntersections(lightRay, light.getDistance(geopoint.point));
        return intersections.isEmpty();
    }

    private Color calcColor (GeoPoint p, Vector V)
    {
           var ambientLight=  scene.getAmbientLight();
           var intensity = ambientLight.getIntensity();
           var color = intensity.add(p.geometry.getEmission());
        /**
         *  due model pong
         * 𝒌𝑫 ∙ |𝒍 ∙ 𝒏 |∙ 𝑰a+𝒌𝑺 ∙( 𝒎𝒂𝒙 𝟎, −𝒗 ∙ 𝒓)^(n*sh)*ia
         */
        for (var i :scene.lights)
        {
            var material=p.geometry.getMaterial();
            var Il = i.getIntensity(p.point);
            if (Il==Color.BLACK)
                break;
            var n=p.geometry.getNormal(p.point).normalize();
            var l = i.getL(p.point).normalize();
            var dot = n.dotProduct(l);
            var kd_factor= Math.abs(dot)* material.getKd();
            //ks
            var r= l.subtract(n.scale(2*dot)).normalize();
            var dotRV= -V.dotProduct(r);
            var max = Math.max(dotRV,0);
            double ks_factor = Math.pow(max,material.getnShininess()) *material.getKs();
            var add = Il.scale(ks_factor+kd_factor);
            color= color.add(add);
        }
        return color;
    }
}
