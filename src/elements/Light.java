package elements;

import primitives.Color;

/**
 * this is the father of all type of light
 */
class Light {
    /**
     * @return the color of light
     */
    public Color getIntensity() {
        return intensity;
    }

    private Color intensity;

    /**
     * @param intensity_
     */
    protected Light(Color intensity_)
    {
        intensity = intensity_;
    }

}
