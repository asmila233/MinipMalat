package primitives;

import java.util.Objects;

public class Ray {
    Point3D po;
    Vector dir;

    public Ray(Point3D po, Vector vec) {
        var nirmol = (vec.head.y.coord*vec.head.y.coord);
        nirmol += (vec.head.x.coord*vec.head.x.coord);
        nirmol += (vec.head.y.coord*vec.head.y.coord);
        nirmol = Math.sqrt(nirmol);

        dir = new Vector((vec.head.x.coord)/nirmol,(vec.head.y.coord)/nirmol,(vec.head.z.coord)/nirmol);
        this.po = po;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return po.equals(ray.po) && dir.equals(ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "po=" + po +
                ", dir=" + dir +
                '}';
    }

    public Point3D getPo() {
        return po;
    }

    public Vector getDir() {
        return dir;
    }
}
