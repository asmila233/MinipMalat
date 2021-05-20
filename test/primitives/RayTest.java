package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testFindClosestPoint() {
      //TC11: empty list
      Ray ray = new Ray(new Point3D(0,0,0), new Vector(1,1,1));
      List<Point3D> list = new ArrayList<>();

      assertEquals(null, ray.findClosestPoint(list));

      //TC12:first point is the closest point
        list.add(new Point3D(1,1,1));
        list.add(new Point3D(2,2,2));

        assertEquals(new Point3D(1,1,1), ray.findClosestPoint(list));

      //TC13: last point is the closest point
        list.clear();
        list.add(new Point3D(2,2,2));
        list.add(new Point3D(1,1,1));

        assertEquals(new Point3D(1,1,1), ray.findClosestPoint(list));

      //TC01: a point in the middle is the closest point
      list.clear();
      list.add(new Point3D(2,2,2));
      list.add(new Point3D(1,1,1));
      list.add(new Point3D(3,3,3));

      assertEquals(new Point3D(1,1,1), ray.findClosestPoint(list));

    }
}