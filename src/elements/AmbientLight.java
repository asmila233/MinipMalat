package elements;

import primitives.Color;

public class AmbientLight {
    Color intensity ;

    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    public AmbientLight(Color ia, double ka) {
        intensity = ia.scale(ka);
    }

    public Color getIntensity() {
        return intensity;
    }
}
