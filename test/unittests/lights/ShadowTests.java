package unittests.lights;

import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Testing basic shadows
 *
 * @author Dan
 */
public class ShadowTests {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    /**
     * Produce a picture of a sphere and triangle with point light and shade
     */
    @Test
    public void sphereTriangleInitial() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial1() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point3D(-70, 20, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -50, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial_1", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial2() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point3D(-10, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-60, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial_2", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial3() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point3D(-30, -10, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial_3", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial4() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)), //
                new Polygon(new Point3D(-20, 20, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-120, -120, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("shadowSphereTriangleInitial_4", 400, 400)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(48,90,74), 0.15));
        scene.setBackground(new Color(48,90,74));

        scene.geometries.add( //
//                new Polygon(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
//                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
//                new Polygon(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
//                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(40, 40, 115), new Color(700, 400, 400), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }



    @Test
    public void myTest() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        // size of wall
        int size = 150;

        // size of the light
        int light = size/2;

        // size of the light sphere
        int sphere = size/10;

        double e=  0.000001;

        scene.geometries.add(
                /**
                 * there are 3 different colors walls that will make a "room"
                 */
                //wall 1-blue
                new Polygon(new Point3D(0,0,0),new Point3D(size,0,0),new Point3D(0,size,0))
                        .setEmission(new Color(0,0,50)).setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30))

                ,new Polygon(new Point3D(size,size,0),new Point3D(size,0,0),new Point3D(0,size,0))
                        .setEmission(new Color(0,0,50)).setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30))

                //wall2- no color it is a mirror
                ,new Polygon(new Point3D(0,0,0),new Point3D(size,0,0),new Point3D(0,0,size))
                        .setEmission(new Color(0,0,0)).setMaterial(new Material().setKs(0.8).setkd(0.9).setKr(1).setShininess(30))

                ,new Polygon(new Point3D(size,0,size),new Point3D(size,0,0),new Point3D(0,0,size))
                        .setEmission(new Color(0,0,0)).setMaterial(new Material().setKs(0.8).setkd(0.9).setKr(1).setShininess(30))


                //wall3 - green
                ,new Polygon(new Point3D(0,0,0),new Point3D(0,size,0),new Point3D(0,0,size))
                        .setEmission(new Color(0,50,0)).setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30))

                ,new Polygon(new Point3D(0,size,size),new Point3D(0,size,0),new Point3D(0,0,size))
                        .setEmission(new Color(0,50,0)).setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30))

                /**
                 * there are 3 different colors sphere that will be lit with different types of light sources
                 */

                //blue

                ,new Sphere(new Point3D(light, light, 0), 30) //
                        .setEmission(new Color(0,0,400)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30).setKt(0))
                //red

                ,new Sphere(new Point3D(light, 0, light), 30) //
                        .setEmission(new Color(400,0,0)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30).setKt(0))

                //green

                ,new Sphere(new Point3D(0, light, light), 30) //
                        .setEmission(new Color(0,400,0)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setShininess(30).setKt(0))

                /**
                 * four full reflection spheres
                 * one in the middle when the triangles meet
                 * and the rest beneath the lighting sphere on each of the axis
                 */

                // triangles meet

                ,new Sphere(new Point3D(0, 0, 0), 30) //
                        .setEmission(new Color(0,0,0)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setKr(1).setShininess(30))
                // x axis

                ,new Sphere(new Point3D(light, 0, 0), 15) //
                .setEmission(new Color(0,0,0)) //
                .setMaterial(new Material().setkd(0.5).setKs(0.5).setKr(1).setShininess(30))

                // y axis

                ,new Sphere(new Point3D(0, light, 0), 15) //
                        .setEmission(new Color(0,0,0)) //
                        .setMaterial(new Material().setkd(0.5).setKs(0.5).setKr(1).setShininess(30))

                // z axis
                ,new Sphere(new Point3D(0, 0, light), 15) //
                .setEmission(new Color(0,0,0)) //
                .setMaterial(new Material().setkd(0.5).setKs(0.5).setKr(1).setShininess(30)));

        /**
         * lights
         * the blue sphere is lit by direction light
         * the red sphere is lit by spot light from inside
         * the green sphere is lit by point light from inside
         */
        scene.lights.add( // red
                new SpotLight(new Point3D(light,e,light), new Color(300, 300, 300), new Vector(0,0 , -1)) //
                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add( // green
                new PointLight(new Point3D(light,light,e),new Color(150,150,150))//
                        .setKl(1E-5).setKq(1.5E-7));

        scene.lights.add( // blue
                new DirectionalLight(new Vector(0,-1,0),new Color(150,150,150)));//

        var camera1 = new Camera(new Point3D(800,800,800),new Vector(-1,-1,-1),new Vector(-1,1,0)).setDistance(1100).setViewPlaneSize(200,200);
        Render render = new Render() //
                .setImageWriter(new ImageWriter("my test1", 600, 600)) //
                .setCamera(camera1) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
}
