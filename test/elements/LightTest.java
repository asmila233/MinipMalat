package elements;


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
class LightsTests {
    private Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
    private Camera camera1 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(150, 150) //
            .setDistance(1000);
    private Camera camera2 = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setViewPlaneSize(200, 200) //
            .setDistance(1000);

    private static Geometry triangle1 = new Polygon( //
            new Point3D(-150, -150, -150), new Point3D(150, -150, -150), new Point3D(75, 75, -150));
    private static Geometry triangle2 = new Polygon( //
            new Point3D(-150, -150, -150), new Point3D(-70, 70, -50), new Point3D(75, 75, -150));
    private static Geometry sphere = new Sphere(new Point3D(0, 0, -50), 50) //
            .setEmission(new Color(java.awt.Color.BLUE)) //
            .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(100));

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new DirectionalLight(new Vector(1, 1, -1), new Color(500, 300, 0)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera1) //
                .setRayTracerBasic(new RayTracerBasic(scene1));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new PointLight(new Point3D(-50, -50, 50), new Color(500, 300, 0))//
                .setKL(0.00001).setKQ(0.000001));

        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera1) //
                .setRayTracerBasic(new RayTracerBasic(scene1));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void sphereSpot() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(new Point3D(-50, -50, 50), new Color(500, 300, 0), new Vector(1, 1, -2)) //
                .setKL(0.00001).setKQ(0.00000001));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera1) //
                .setRayTracerBasic(new RayTracerBasic(scene1));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * all light test
     */
    @Test
    public void sphereAllLight() {
        scene1.geometries.add(sphere);
        scene1.lights.add(new SpotLight(new Point3D(75, 0, 25), new Color(0, 700,0 ), new Vector(-1, 0, 0)) //
                .setKL(0.00001).setKQ(0.00000001));
        scene1.lights.add(new PointLight(new Point3D(50, -50, 50), new Color(800, 0, 0)) //
                .setKL(0.00001).setKQ(0.00000001));
        scene1.lights.add(new DirectionalLight(new Vector(0, 0, -1), new Color(0, 200, 0)));

        ImageWriter imageWriter = new ImageWriter("sphereAllLight", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera1) //
                .setRayTracerBasic(new RayTracerBasic(scene1));
        render.renderImage();
        render.writeToImage();
    }


    /**
     * Produce a picture of a two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300))); //
        scene2.geometries.add(triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300)));
        scene2.lights.add(new DirectionalLight(new Vector(0, 0, -1), new Color(300, 150, 150)));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera2) //
                .setRayTracerBasic(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * this is test of all type of light
     */
    @Test
    public void trianglesAllLight(){
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300))); //
        scene2.geometries.add(triangle2.setMaterial(new Material().setKd(0.8).setKs(0.2).setnShininess(300)));
        scene2.lights.add(new DirectionalLight(new Vector(0, 0, -1), new Color(0, 200, 0)));
        scene2.lights.add(new SpotLight(new Point3D(-40,-60,-120), new Color(500, 0, 0), new Vector(-2, -2, -1)).setKL(0.0000005).setKQ(0.0000005));
        scene2.lights.add(new PointLight(new Point3D(10,-10,-130), new Color(0, 0, 500)).setKL(0.000005).setKQ(0.000005));
        ImageWriter imageWriter = new ImageWriter("trianglesAllLight", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera2) //
                .setRayTracerBasic(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }
    /**
     * Produce a picture of a two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300))); //
        scene2.geometries.add(triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
        scene2.lights.add(new PointLight(new Point3D(10, -10, -130), new Color(500, 250, 250)) //
                .setKL(0.0005).setKQ(0.0005));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera2) //
                .setRayTracerBasic(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light
     */
    @Test
    public void trianglesSpot() {
        scene2.geometries.add(triangle1.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
        scene2.geometries.add(triangle2.setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(300)));
        scene2.lights.add(new SpotLight(new Point3D(10, -10, -130), new Color(500, 250, 250), new Vector(-2, -2, -1)) //
                .setKL(0.0001).setKQ(0.000005));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        Render render = new Render()//
                .setImage(imageWriter) //
                .setCam(camera2) //
                .setRayTracerBasic(new RayTracerBasic(scene2));
        render.renderImage();
        render.writeToImage();
    }

}
