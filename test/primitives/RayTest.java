package primitives;

import geometries.Intersectable.GeoPoint;
import geometries.Sphere;
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
  @Test
  void testFindClosestGeoPoint() {
    //TC11: empty list
    Ray ray = new Ray(new Point3D(0,0,0), new Vector(1,1,1));
    Sphere sphere1 = new Sphere(new Point3D(0,0,0),2);
    Sphere sphere2 = new Sphere(new Point3D(0,0,0),3);
    Sphere sphere3 = new Sphere(new Point3D(0,0,0),4);


    List<GeoPoint> list = new ArrayList<>();

    assertEquals(null, ray.findClosestGeoPoint(list));

    //TC12:first point is the closest point
    list.add(new GeoPoint(sphere1 ,new Point3D(2,0,0)));
    list.add(new GeoPoint(sphere2 ,new Point3D(2,1,2)));

    assertEquals(new GeoPoint(sphere1 ,new Point3D(2,0,0)), ray.findClosestGeoPoint(list));

    //TC13: last point is the closest point
    list.clear();
    list.add(new GeoPoint(sphere2 ,new Point3D(2,1,2)));
    list.add(new GeoPoint(sphere1 ,new Point3D(2,0,0)));

    assertEquals(new GeoPoint(sphere1 ,new Point3D(2,0,0)), ray.findClosestGeoPoint(list));

    //TC01: a point in the middle is the closest point
    list.clear();
    list.add(new GeoPoint(sphere2 ,new Point3D(2,1,2)));
    list.add(new GeoPoint(sphere1 ,new Point3D(2,0,0)));
    list.add(new GeoPoint(sphere3 ,new Point3D(4,0,0)));

    assertEquals(new GeoPoint(sphere1 ,new Point3D(2,0,0)), ray.findClosestGeoPoint(list));



  }
}