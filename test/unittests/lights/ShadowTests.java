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

        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, 200), 5) // sphere for
                        .setMaterial(new Material().setKs(0.8).setShininess(30).setKt(0.95)).setEmission(new Color(300,0,0)),
                new Sphere(new Point3D(-50, 0, 0), 25) //
                        .setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30).setkd(0.9)).setEmission(new Color(0,150,0)), //
                new Sphere(new Point3D(0, 50, 0), 25) //
                        .setMaterial(new Material().setkd(0.9).setKs(0.5).setShininess(30)).setEmission(new Color(0,150,0)), //
                new Sphere(new Point3D(50, 0, 0), 25) //
                .setMaterial(new Material().setKs(0.8).setkd(0.9).setShininess(30)).setEmission(new Color(0,150,0)),
                new Polygon(new Point3D(100,100,-75),new Point3D(-100,100,-100),new Point3D(100,-100,-100))
                        .setEmission(new Color(java.awt.Color.black)).setMaterial(new Material().setKr(1).setKs(0.8).setkd(0.9).setShininess(30))
                ,new Polygon(new Point3D(-100,-100,-75),new Point3D(-100,100,-100),new Point3D(100,-100,-100))
                        .setEmission(new Color(java.awt.Color.black)).setMaterial(new Material().setKs(0.8).setkd(0.9).setKr(1).setShininess(30)));

        scene.lights.add( //
                new SpotLight(new Point3D(0, 0, 200), new Color(0, 0, 150), new Vector(0,0 , -1)) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("my test", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

}
