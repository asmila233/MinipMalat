package elements;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class CameraIntegrationTests {
    // i took all the tests directly from the course slides
    @Test
    void TestIntersectionWithCam(){
        // sphere's tests.
        // TC01
        Sphere sphere = new Sphere(new Point3D(0,0,-3), 1);

        Camera camera = new Camera(Point3D.ZERO, new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(sphereNumberOfIntersections(camera,sphere),2,"error, wrong intersections number");

        //TC02
        sphere = new Sphere(new Point3D(0,0,-2.5), 2.5);

        camera = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(sphereNumberOfIntersections(camera,sphere),18,"error, wrong intersections number");

        //TC03
        sphere = new Sphere(new Point3D(0,0,-2), 2);

        camera = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(sphereNumberOfIntersections(camera,sphere),10,"error, wrong intersections number");

        //TC04
        sphere = new Sphere(new Point3D(0,0,-2), 4);

        camera = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(sphereNumberOfIntersections(camera,sphere),9,"error, wrong intersections number");

        //TC05
        sphere = new Sphere(new Point3D(0,0,1), 0.5);

        camera = new Camera(new Point3D(0,0,0), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(sphereNumberOfIntersections(camera,sphere),0,"error, wrong intersections number");
// plane's tests
        //TC06
        Plane plane = new Plane(new Point3D(0,0,-3),new Vector(0,0,-1));

        camera = new Camera(new Point3D(0,0,0), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(planeNumberOfIntersections(camera,plane),9,"error, wrong intersections number");

        //TC07
        plane = new Plane(new Point3D(3.5,2,2),new Vector(1d/3,0,-2d/3));
        camera = new Camera(new Point3D(0,0,0.5), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(planeNumberOfIntersections(camera,plane),9,"error, wrong intersections number");

        //TC08
        camera = new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1),new Vector(0, 1, 0))
                .setDistance(1)
                .setViewPlaneSize(3, 3);
        plane = new Plane(new Point3D(3.5,2,2),new Vector(0.5,0,-0.5));

        assertEquals(planeNumberOfIntersections(camera,plane),6,"error, wrong intersections number");
// triangle's tests.
        //TC09
        Polygon triangle = new Polygon(new Point3D(0, 1, -2),
                new Point3D(-1, -1, -2),new Point3D(1, -1, -2));

        camera = new Camera(new Point3D(0,0,0), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(polygonNumberOfIntersections(camera,triangle),1,"error, wrong intersections number");

        //TC10
        triangle = new Polygon(new Point3D(0, 20, -2),
                new Point3D (-1, -1, -2),new Point3D(1, -1, -2));

        camera = new Camera(new Point3D(0,0,0), new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(1)
                .setViewPlaneSize(3,3);

        assertEquals(polygonNumberOfIntersections(camera,triangle),2,"error, wrong intersections number");

    }

    /**
     * counts all the intersections with spheres for each pixel and sums them.
     * @param camera
     * @param sphere
     * @return
     */
    private int sphereNumberOfIntersections(Camera camera, Sphere sphere){
        List<Point3D> intersect = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3,3,i,j);
                List<Point3D> lst = sphere.findIntersections(ray);

                if(lst != null){
                    if(intersect == null){
                        intersect = new ArrayList<>();
                    }
                    intersect.addAll(lst);
                }

            }
        }
        if(intersect == null)
            return 0;
        return intersect.size();
    }
    /**
     * counts all the intersections with planes for each pixel and sums them.
     * @param camera
     * @param plane
     * @return
     */
    private int planeNumberOfIntersections(Camera camera, Plane plane){
        List<Point3D> intersect = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3,3,i,j);
                List<Point3D> lst = plane.findIntersections(ray);

                if(lst != null){
                    if(intersect == null){
                        intersect = new ArrayList<>();
                    }
                    intersect.addAll(lst);
                }

            }
        }
        if(intersect == null)
            return 0;
        return intersect.size();
    }
    /**
     * counts all the intersections with polygons for each pixel and sums them.
     * @param camera
     * @param polygon
     * @return
     */
    private int polygonNumberOfIntersections(Camera camera, Polygon polygon){
        List<Point3D> intersect = null;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3,3,i,j);
                List<Point3D> lst = polygon.findIntersections(ray);

                if(lst != null){
                    if(intersect == null){
                        intersect = new ArrayList<>();
                    }
                    intersect.addAll(lst);
                }

            }
        }
        if(intersect == null)
            return 0;
        return intersect.size();
    }
}