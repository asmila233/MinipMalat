package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        var t = new Tube(new Ray(new Point3D(0,0,0),new Vector(1,0,0)),1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        var v1= t.getNormal(new Point3D(2,0,1));
        if (v1.length()!=1)
            fail("the result must be normalize");
        if (!v1.equals(new Vector(0,0,1)))
            fail("wrong normal");
        // TC02: basis 1
        var v2= t.getNormal(new Point3D(0,0,0.5));
        if (v1.length()!=1)
            fail("the result must be normalize");
        if (!v1.equals(new Vector(-1,0,0)))
            fail("wrong normal");
        // =============== Boundary Values Tests ==================
        // TC11: middle
        var v3= t.getNormal(new Point3D(0,0,0));
        if (v1.length()!=1)
            fail("the result must be normalize");
        if (!v1.equals(new Vector(-1,0,0)))
            fail("wrong normal");
    }
}