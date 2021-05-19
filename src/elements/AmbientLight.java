package elements;

import primitives.Color;

public class AmbientLight {
    //fileds
    Color Ia;
    double Ka;

    public AmbientLight(Color ia, double ka) {
        Ia = ia;
        Ka = ka;
    }
}
