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
        return calcColor(close,ray,MAX_CALC_COLOR_LEVEL,DELTA);
    }

    /**
     * return true if the point is not shaded and have a clean straight line to light source
     * @param light
     * @param l
     * @param n for the add delta
     * @param geopoint
     * @return the transparency
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        var addi= n.scale(DELTA);
        Ray lightRay =  constructRefractedRay(geopoint.point , lightDirection,n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = scene.geometries.findGeoIntersections(lightRay,lightDistance,light);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

    /**
     * calculates the returned color, including shades and reduction
     * @param p
     * @param R
     * @return
     */

    private Color calcLocalEffects (GeoPoint p, Ray R,double k)
    {
        Vector V = R.getDir();
        var color= new Color(Color.BLACK);
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
            var dot =alignZero( n.dotProduct(l));
            if(dot*n.dotProduct(V)>0){
                double ktr = transparency(i,l,n,p);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    var kd_factor= Math.abs(dot)* material.getKd();
                    //ks
                    var r= l.subtract(n.scale(2*dot)).normalize();
                    var dotRV= -V.dotProduct(r);
                    var max = Math.max(dotRV,0);
                    double ks_factor = Math.pow(max,material.getnShininess()) *material.getks();
                    var add = Il.scale(ks_factor+kd_factor).scale(ktr);
                    color= color.add(add);
                }
            }
        }
        return color;
    }

    /**
     * @param p point
     * @param R the ray of the view from the camera
     * @param level the level of the
     * @param k
     * @return the color of this point
     */
    private Color calcColor (GeoPoint p, Ray R,int level,double k)
    {
        Vector V = R.getDir();
        var ambientLight=  scene.getAmbientLight();
        var intensity = ambientLight.getIntensity();
        var color = intensity.add(p.geometry.getEmission());
        Color local= calcLocalEffects(p,R,k);
        color= color.add(local);
        color = (1 == level) ? color : color.add(calcGlobalEffects(p, R.getDir(), level, k));
        return color;
    }

    /**
     * this function is to crate a ray for Reflected and Refracted
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK; Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * @param point
     * @param v
     * @param n
     * @return RefractedRay
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {

        //Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Vector addition = n.scale(DELTA);

        if(v.dotProduct(n) < 0)
            return new Ray(point.add(addition.scale(-1)) , v);

        else
            return new Ray(point.add(addition), v);
    }

    /**
     * @param point
     * @param v
     * @param n
     * @return ReflectedRay
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        Vector addition = n.scale(DELTA);

        if(v.dotProduct(n) < 0)
            return new Ray(point.add(addition) , v.subtract((n.scale(v.dotProduct(n) *2))));

        else
            return new Ray(point.add(addition.scale(-1)), v.subtract((n.scale(v.dotProduct(n) *2))));

    }

    /**
     * do the calc of the global
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.getBackground() : calcColor(gp, ray, level-1, kkx)
        ).scale(kx);
    }


    /**
     * returns the closest intersection to the start of the ray to simulate shading
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray){
        Point3D source = ray.getPo();
        GeoPoint result = null;
        double assist = Double.POSITIVE_INFINITY, temp;
        var contain = scene.geometries.findGeoIntersections(ray);
        if (contain==null)return null;
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
