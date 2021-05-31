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
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)), //
                new Polygon(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKL(1E-5).setKQ(1.5E-7));

        Render render = new Render(). //
                setImage(new ImageWriter("shadowSphereTriangleInitial", 400, 400)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial1() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)), //
                new Polygon(new Point3D(-70, 20, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -50, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKL(1E-5).setKQ(1.5E-7));

        Render render = new Render(). //
                setImage(new ImageWriter("shadowSphereTriangleInitial_1", 400, 400)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial2() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)), //
                new Polygon(new Point3D(-10, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-60, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKL(1E-5).setKQ(1.5E-7));

        Render render = new Render(). //
                setImage(new ImageWriter("shadowSphereTriangleInitial_2", 400, 400)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial3() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)), //
                new Polygon(new Point3D(-30, -10, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-100, -100, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKL(1E-5).setKQ(1.5E-7));

        Render render = new Render(). //
                setImage(new ImageWriter("shadowSphereTriangleInitial_3", 400, 400)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    @Test
    public void sphereTriangleInitial4() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)), //
                new Polygon(new Point3D(-20, 20, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(-120, -120, 200), new Color(400, 240, 0), new Vector(1, 1, -3)) //
                        .setKL(1E-5).setKQ(1.5E-7));

        Render render = new Render(). //
                setImage(new ImageWriter("shadowSphereTriangleInitial_4", 400, 400)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Polygon(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Polygon(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setnShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setkS(0.5).setnShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Point3D(40, 40, 115), new Color(700, 400, 400), new Vector(-1, -1, -4)) //
                        .setKL(4E-4).setKQ(2E-5));

        Render render = new Render() //
                .setImage(new ImageWriter("shadowTrianglesSphere", 600, 600)) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

}
