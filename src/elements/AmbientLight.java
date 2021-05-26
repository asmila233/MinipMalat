package elements;

import primitives.Color;


public class AmbientLight extends Light{

    /**
     * in default the intensity field are initiated with the color black
     */
    public AmbientLight()  {
        super( (Color.BLACK));
    }

    /**
     * puts in the intensity field the given color multiplied by the ka param
     * @param ia
     * @param ka
     */
    public AmbientLight(Color ia, double ka) {
        super(ia.scale(ka));
    }


}
