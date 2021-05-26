package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.*;
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
        return calcColor(close,ray.getDir());
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
