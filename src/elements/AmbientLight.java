package elements;

import primitives.Color;


public class AmbientLight {
    Color intensity ;

    /**
     * in default the intensity field are initiated with the color black
     */
    public AmbientLight() {
        this.intensity = Color.BLACK;
    }

    /**
     * puts in the intensity field the given color multiplied by the ka param
     * @param ia
     * @param ka
     */
    public AmbientLight(Color ia, double ka) {
        intensity = ia.scale(ka);
    }

    public Color getIntensity() {
        return intensity;
    }
}
