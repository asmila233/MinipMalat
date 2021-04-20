package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

import java.util.ArrayList;

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
    public void testFindIntsersections() {
        //EP
        // TC01: Ray's line is going to the plane (1 point)
        Point3D Q0 = new Point3D(1, 5, 9);
        Point3D p0 = new Point3D(1, 1, 1);

        Vector v = new Vector(1, 2, 3);
        Vector N = new Vector(1, 1, 1);

        Plane ex = new Plane(Q0, N);
        Ray ray = new Ray(p0, v);

        List<Point3D> result = new ArrayList();
        result.add(new Point3D(3, 5, 7));
        assertArrayEquals(result.toArray(), ex.findIntersections(ray).toArray());
        result.clear();

        //TC02: Ray's line is going to the other direction and not to the plane.
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(1, 1, 1);

        v = new Vector(-1, -2, -3);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));
        //BVA
        //TC03: Ray is parallel to the plane and not included in it
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(1, 1, 1);

        v = new Vector(1, -2, 1);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

        //TC04: Ray is parallel to the plane and included in it
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(5, 5, 5);

        v = new Vector(1, -2, 1);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

        //TC05: ray is orthogonal to the plane and p0 is before the plane
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(1, 1, 1);

        v = new Vector(1, 1, 1);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        result.add(new Point3D(5, 5, 5));
        assertArrayEquals(result.toArray(), ex.findIntersections(ray).toArray());
        result.clear();

        //TC06: ray is orthogonal to the plane and p0 is on the plane
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(5, 5, 5);

        v = new Vector(1, 1, 1);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

        //TC07: ray is orthogonal to the plane and p0 is over the plane
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(2, 6, 10);

        v = new Vector(1, 1, 1);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

        //TC08: ray is not parallel to the plane and p0 is in the plane
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(5, 5, 5);

        v = new Vector(1, 2, 3);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

        //TC09: Ray is neither orthogonal nor parallel to the plane and begins in
        //the same point which appears as reference point in the plane (Q)
        Q0 = new Point3D(1, 5, 9);
        p0 = new Point3D(1, 5, 9);

        v = new Vector(1, 2, 3);
        N = new Vector(1, 1, 1);

        ex = new Plane(Q0, N);
        ray = new Ray(p0, v);
        assertNull(ex.findIntersections(ray));

    }
}