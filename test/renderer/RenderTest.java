package renderer;

import static org.junit.jupiter.api.Assertions.*;


import elements.*;
import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
class RenderTests {
    private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setDistance(100) //
            .setViewPlaneSize(500, 500);

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
                .setBackground(new Color(75, 127, 90));

        scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 50));
        // right
        scene.geometries.add(new Polygon(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100))); // up
        // left
        scene.geometries.add(new Polygon(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100))); // up
        // right
        scene.geometries.add(new Polygon(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100))); // down
        // left
        scene.geometries.add(new Polygon(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100))); // down


        ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
        Render render = new Render() //
                .setImage(imageWriter) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));

        render.renderImage();
        render.printGrid(100, new Color(java.awt.Color.YELLOW));
        render.writeToImage();
    }

    /**
     * Test for XML based scene - for bonus
     */

    // For stage 6 - please disregard in stage 5
    /**
     * Produce a scene with basic 3D model - including individual lights of the bodies
     * and render it into a png image with a grid
     */
    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2)); //

        scene.geometries.add(new Sphere( new Point3D(0, 0, -100),50) //
                .setEmission(new Color(java.awt.Color.CYAN))); //
        scene.geometries.add(new Polygon(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)) // up left
                .setEmission(new Color(java.awt.Color.GREEN)));
        scene.geometries.add(new Polygon(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100))); // up right
        scene.geometries.add(new Polygon(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)) // down left
                .setEmission(new Color(java.awt.Color.RED)));
        scene.geometries.add(new Polygon(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100)) // down right
                .setEmission(new Color(java.awt.Color.BLUE)));

        ImageWriter imageWriter = new ImageWriter("color render test", 1000, 1000);
        Render render = new Render() //
                .setImage(imageWriter) //
                .setCam(camera) //
                .setRayTracerBasic(new RayTracerBasic(scene));

        render.renderImage();
        render.printGrid(100, new Color(java.awt.Color.WHITE));
        render.writeToImage();
    }
}