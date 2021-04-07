package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        Point3D p1 = new Point3D(1, 2, 3);
        if (!Point3D.ZERO.equals(p1.add(new Vector(-1, -2, -3))))
            fail("ERROR: Point + Vector does not work correctly");

    }

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        Point3D p1 = new Point3D(1, 2, 3);
        if (!new Vector(1, 1, 1).equals(new Point3D(2, 3, 4).subtract(p1)))
            fail("ERROR: Point - Point does not work correctly");
    }
}