package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    String name;
    Color background;
    AmbientLight ambientLight;
    public Geometries geometries;

    /**
     * basic constructor with the name only
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        background = Color.BLACK;
        geometries = new Geometries();
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
}