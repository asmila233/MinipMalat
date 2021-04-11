package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC10: simple
        Sphere s = new Sphere(new Point3D(0,0,0),2);
        var p= new Point3D(0,0,2);
        var v= s.getNormal(p);
        if (v.length()!=1)
            fail("the result must be normalize");
        if (!v.equals(new Vector(0,0,1)))
            fail("wrong normal");

    }
}