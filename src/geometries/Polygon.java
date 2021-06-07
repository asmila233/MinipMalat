package geometries;

import java.util.ArrayList;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    /**
     *
     * @param point
     * @return the normal of the plane the polygon is lying on.
     */
    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal();
    }

    /**
     * returns a list of geopoints contains all the intersection with the polygon
     * @param R
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray R) {
        if (vertices.size()==3)
        {
            // first we find the intersections with the plane and then we check wether it is in the polygon or not.
            var x = plane.findGeoIntersections(R);
            List<GeoPoint> res = new ArrayList<>();

            Vector u1,u2,u3;
            try {
                u1= vertices.get(0).subtract(R.getPo());
                u2= vertices.get(1).subtract(R.getPo());
                u3= vertices.get(2).subtract(R.getPo());
            }
            catch (Exception ex)
            {
                // start in the triangle
                return null;
            }
            Vector V1,V2,V3;
            V1= u1.crossProduct(u2).normalize();
            V2= u2.crossProduct(u3).normalize();
            V3= u3.crossProduct(u1).normalize();

            double d1,d2,d3;
            d1= Util.alignZero(V1.dotProduct(R.getDir()));
            d2= Util.alignZero(V2.dotProduct(R.getDir()));
            d3= Util.alignZero(V3.dotProduct(R.getDir()));
            if ((d1>0&&d2>0&&d3>0)||(d1<0&&d2<0&&d3<0)){

                List<GeoPoint> result = new ArrayList<>();

                if(x == null)
                    return null;

                for (int i = 0; i < x.size(); i++) {
                    GeoPoint assist = new GeoPoint(this,x.get(i).point);
                    result.add(assist);
                }

                return result;
            }
            return null;
        }
        return null;
    }
}
