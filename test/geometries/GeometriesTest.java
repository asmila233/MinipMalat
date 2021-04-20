package geometries;

import org.junit.jupiter.api.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        Geometries geo;
        var p1 = new Plane(new Point3D(1,1,1),new

                Vector(1,0,0));
        var p2 = new Plane(new Point3D(2,2,2),new Vector(1,0,0));
        var p3 = new Plane(new Point3D(3,3,3),new Vector(1,0,0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        geo= new Geometries();
        geo.add(p1);geo.add(p2);geo.add(p3);
        assertEquals(2,geo.findIntersections(new Ray(new Point3D(1.5,2,2),new Vector(1,0,0))).size(),"rong");
        // =============== Boundary Values Tests ==================
        // TC10: empty
        geo= new Geometries();
        assertEquals(0,geo.findIntersections(new Ray(new Point3D(2,2,2),new Vector(1,0,0))).size(),"empty must return zero");
        // TC11: no cutting in space
        geo= new Geometries();
        geo.add(p1);
        assertEquals(0,geo.findIntersections(new Ray(new Point3D(4,4,4),new Vector(1,0,0))).size(),"empty must return zero");
        // TC12: one cutting in space
        geo= new Geometries();
        geo.add(p1);geo.add(p2);geo.add(p3);
        assertEquals(1,geo.findIntersections(new Ray(new Point3D(2,3.5,3.5),new Vector(1,0,0))).size(),"rong");
        // TC12: all cutting in space
        assertEquals(3,geo.findIntersections(new Ray(new Point3D(0.1,0.1,0.1),new Vector(1,0,0))).size(),"rong");







    }
}