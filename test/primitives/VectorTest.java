package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================
        // TC10: zero vector
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
    }

    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        Vector vr = v1.crossProduct(v3);
        if (!isZero(vr.length() - v1.length() * v3.length()))
           fail("ERROR: crossProduct() wrong result length");
        if (!isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)))
            fail("ERROR: crossProduct() result is not orthogonal to its operands");

        // =============== Boundary Values Tests ==================
        // TC10: zero result
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}




    }

    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        if (!isZero(v1.dotProduct(v2) + 28))
            fail("ERROR: dotProduct() wrong value");

        // =============== Boundary Values Tests ==================
        // TC10: zero result
        if (!isZero(v1.dotProduct(v3)))
            fail("ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC10: simple vector

        Vector v1 = new Vector(1, 2, 3);
        if (!isZero(v1.lengthSquared() - 14))
            fail("ERROR: lengthSquared() wrong value");

    }

    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC10: simple vector

        if (!isZero(new Vector(0, 3, 4).length() - 5))
           fail("ERROR: length() wrong value");
    }

    @Test
    void testNormalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
    // ============ Equivalence Partitions Tests ==============
        // TC01: simple
        if (vCopy != vCopyNormalize)
            fail("ERROR: normalize() function creates a new vector");
        if (!isZero(vCopyNormalize.length() - 1))
            fail("ERROR: normalize() result is not a unit vector");

    }

    @Test
    void testNormalized() {
        Vector v = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        // TC01: is change?
        Vector u = v.normalized();
        if (u == v)
            fail("ERROR: normalizated() function does not create a new vector");
    }
}