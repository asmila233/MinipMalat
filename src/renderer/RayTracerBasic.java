package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;

import primitives.*;
import scene.Scene;

import java.util.List;

/**
 * this class goal is to throw ray and back color in the scene
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * constant for moving the heads of shading rays.
     */
    private static final double DELTA = 0.1;
    /**
     * constant for the max amount of recursion loops
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     * constant for the min value of the returned value
     */
    private static final double MIN_CALC_COLOR_K = 0.001;


    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     *check what intersections there are from the scene, and if there are not any of them return the color of the background
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {

        var close= this.findClosestIntersection(ray);
        if(close == null)
            return scene.getBackground();
        return calcColor(close,ray);
    }

    /**
     * return true if the point is not shaded and have a clean straight line to light source
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
                .findGeoIntersections(lightRay, light.getDistance(geopoint.point), light);
        return intersections.isEmpty();
    }

    /**
     * calculates the returned color, including shades and reduction
     * @param p
     * @param R
     * @return
     */
    private Color calcColor (GeoPoint p, Ray R)
    {
        Vector V = R.getDir();
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
            if (unshaded(i,l,n,p)) {
                var dot = n.dotProduct(l);
                var kd_factor= Math.abs(dot)* material.getKd();
                //ks
                var r= l.subtract(n.scale(2*dot)).normalize();
                var dotRV= -V.dotProduct(r);
                var max = Math.max(dotRV,0);
                double ks_factor = Math.pow(max,material.getnShininess()) *material.getkS();
                var add = Il.scale(ks_factor+kd_factor);
                color= color.add(add);
            }
        }
        return color;
    }

    /**
     * construct a new ray of the refraction
     * @param ray
     * @param point
     * @return
     */
    public Ray refractedRay(Ray ray, GeoPoint point){
        Vector dir = ray.getDir();
        Vector norm = point.geometry.getNormal(point.point);
        Vector addition = norm.scale(DELTA);

        if(ray.getDir().dotProduct(norm) < 0)
            return new Ray(point.point.add(addition) , dir);

        else
            return new Ray(point.point.add(addition.scale(-1)), dir);
    }

    /**
     * construct a new ray of the reflection
     * @param ray
     * @param point
     * @return
     */
    public Ray reflectedRay(Ray ray, GeoPoint point){
        Vector dir = ray.getDir();
        Vector norm = point.geometry.getNormal(point.point);
        Vector addition = norm.scale(DELTA);

        if(ray.getDir().dotProduct(norm) < 0)
            return new Ray(point.point.add(addition) , dir.subtract((norm.crossProduct(dir.crossProduct(norm))).scale(2)));

        else
            return new Ray(point.point.add(addition.scale(-1)), dir.subtract((norm.crossProduct(dir.crossProduct(norm))).scale(-2)));
    }

    private GeoPoint findClosestIntersection(Ray ray){
        Point3D source = ray.getPo();
        GeoPoint result = null;
        double assist = Double.POSITIVE_INFINITY, temp;
        var contain = scene.geometries.findGeoIntersections(ray);

        for (var n:contain) {
            temp = source.distance(n.point);
            if(temp < assist){
                assist = temp;
                result = n;
            }
        }
        return result;
    }
}
