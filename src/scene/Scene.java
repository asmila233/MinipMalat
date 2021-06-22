package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    String name;
    Color background;
    AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<LightSource>();

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * basic constructor with the name only
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        background = Color.BLACK;
        geometries = new Geometries();
        ambientLight=new AmbientLight();
    }

    // setters and getters
    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public Camera setCamera(Camera camera) {
        return camera;
    }
}