package primitives;

/**
 * this class is define the fiscal counties of the geometry in the space
 */
public class Material {

    /**
     * kS - specular
     * kD - diffuse
     * kR - reflection
     * kT - Transparency
     */
    public double kS,kD,kT,kR;

    int nShininess;
// getters
    public double getks() {
        return kS;
    }

    public double getKd() {
        return kD;
    }

    public int getnShininess() {
        return nShininess;
    }

    /**
     * setter
     * @param kT
     * @return
     */
    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * setter
     * @param kR
     * @return
     */
    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * getter
     * @param kS
     * @return this
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * setter
     * @param kd
     * @return this
     */
    public Material setkd(double kd) {
        kD = kd;
        return this;
    }

    /**
     *builder style setter
     * @param nShininess
     * @return this
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     *default constructor
     * empty
     */
    public Material() {
        kS =0.0;
        kD=0.0;
        kT = 0.0;
        kR = 0.0;
        nShininess=0;
    }

}
