package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01:simple
        var v= new Plane(new Point3D(0,0,0),new Point3D(1,0,0),new Point3D(0,0,1)).getNormal();
        if (v.length()!=1)
            fail("the constructor don't normalize the normal");
        if (!v.equals(new Vector(0,-1,0))&&!v.equals(new Vector(0,1,0)))
            fail("wrong normal in constructor");

        // =============== Boundary Values Tests ==================

        // TC10: same point
        try {
            new Plane(new Point3D(0,0,0),new Point3D(0,0,0),new Point3D(0,0,1));
            fail("no Exception in same points");
        } catch (Exception e) {
        }

        // TC11: same line to the points
        try {
            new Plane(new Point3D(0,0,0),new Point3D(0,0,1),new Point3D(0,0,2));
            fail("no Exception in same lines");
        } catch (Exception e) {
        }
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC10: simple
        Plane P= new  Plane(new Point3D(0,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        var v= P.getNormal(new Point3D(0,0,0));
        if (v.length()!=1)
            fail("the result must be normalize");
        if (!v.equals(new Vector(1,0,0))&&!v.equals(new Vector(-1,0,0)))
            fail("wrong normal");
    }

    @Test
    void testFindIntersections()
    {

    }
}