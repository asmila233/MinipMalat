package primitives;

public class Material {
    double Ks,Kd;
    int nShininess;

    public double getKs() {
        return Ks;
    }

    public double getKd() {
        return Kd;
    }

    public int getnShininess() {
        return nShininess;
    }

    /**
     *
     * @param ks
     * @return this
     */
    public Material setKs(double ks) {
        Ks = ks;
        return this;
    }

    /**
     *
     * @param kd
     * @return this
     */
    public Material setKd(double kd) {
        Kd = kd;
        return this;
    }

    /**
     *
     * @param nShininess
     * @return this
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * empty
     */
    public Material() {
        Ks=0;
        Kd=0;
        nShininess=0;
    }

}
