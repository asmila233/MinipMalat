package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PolygonTest {

    /**
     * Test method for
     * {@link Geometries.Polygon# Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Colocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link Geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        var tre =new Polygon(new Point3D(1,1,0),new Point3D(1,0,0),new Point3D(2,0,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)),"Bad normal to trinagle");

    }

    @Test
    void testFindIntersections() {
        var tre =new Polygon(new Point3D(1,1,0),new Point3D(1,0,0),new Point3D(2,0,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple  test here
        var p1 = new Point3D(1.5,0.25,0);
        var result = tre.findIntersections(new Ray(new Point3D(1.5,0.25,5),new Vector(0,0,-1)));
        assertEquals( 1, result.size(),"Wrong number of points");
        assertEquals( List.of(p1), result,"Ray crosses triangle");

        //TC02: sharp angle
        assertNull( tre.findIntersections(new Ray(new Point3D(0.5,-0.5,5),new Vector(0,0,-1))),"RETURN NULL");

        //TC03: Obtuse angle
        assertNull( tre.findIntersections(new Ray(new Point3D(2,1,5),new Vector(0,0,-1))),"RETURN NULL");

        //TC04: Not the same plane
        assertNull( tre.findIntersections(new Ray(new Point3D(0.5,-0.5,5),new Vector(0,0,1))),"RETURN NULL");

        // =============== Boundary Values Tests ==================
        //TC10:On the rib
        assertNull( tre.findIntersections(new Ray(new Point3D(1,0.5,5),new Vector(0,0,-1))),"RETURN NULL");

        //TC11: On 2 ribs
        assertNull( tre.findIntersections(new Ray(new Point3D(1,0,5),new Vector(0,0,-1))),"RETURN NULL");

        //TC12:On the line of rib
        assertNull( tre.findIntersections(new Ray(new Point3D(5,0,5),new Vector(0,0,-1))),"RETURN NULL");

    }
}