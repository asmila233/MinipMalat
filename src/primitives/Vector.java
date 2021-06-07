package primitives;

import java.util.Objects;

public class Vector {
    Point3D head;

    /**
     * simple constructor
     * @param _head
     */
    public Vector(Point3D _head) {
// to prevent a zero vector
        if(_head != null) {
            if (_head.z.coord == 0 && _head.x.coord == 0 && _head.y.coord == 0)
                throw new IllegalArgumentException("vector zero is an illegal parameter");
            this.head = _head;
        }
        else
            throw new IllegalArgumentException("excepted a null");
    }

    /**
     * instead of a point we are given doubles
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(double _x, double _y, double _z) {
                if (_x == 0 && _z == 0 && _y == 0)
                    throw new IllegalArgumentException("vector zero is an illegal parameter");
                this.head = new Point3D(_x, _y, _z);
    }

    /**
     * instead of a point we are given coordinates
     * @param _x
     * @param _y
     * @param _z
     */
    public Vector(Coordinate _x, Coordinate _y, Coordinate _z) {
            if (_x.coord == 0 && _y.coord == 0 && _z.coord == 0)
                throw new IllegalArgumentException("vector zero is an illegal parameter");

        this.head = new Point3D(_x,_y,_z);
    }

    public Point3D getHead() {
        return head;
    }

    /**
     * addition between two vectors with the result being returned
     * @param vec
     * @return
     */
    public Vector add (Vector vec) {
        var x = head.x.coord + vec.head.x.coord;
        var y = head.y.coord + vec.head.y.coord;
        var z = head.z.coord + vec.head.z.coord;

        return new Vector(x,y,z);
    }

    /**
     * subtraction between two vectors with the result being returned
     * @param vec
     * @return
     */
    public Vector subtract (Vector vec) {
        var x = head.x.coord - vec.head.x.coord;
        var y = head.y.coord - vec.head.y.coord;
        var z = head.z.coord - vec.head.z.coord;

        return new Vector(x,y,z);
    }

    /**
     * returns the ressult of the vector after the multiplication
     * @param num
     * @return
     */
    public Vector scale (double num) {
         var assist = getHead();

         var help = new Point3D(assist.x.coord * num,assist.y.coord * num,assist.z.coord * num);

         return new Vector(help);
    }

    /**
     * vectoric multiplication
     * @param vec
     * @return
     */
    public Vector crossProduct (Vector vec) {
       var _x = head.y.coord*vec.head.z.coord - head.z.coord*vec.head.y.coord;
       var _y = head.z.coord*vec.head.x.coord - head.x.coord*vec.head.z.coord;
       var _z = head.x.coord*vec.head.y.coord - head.y.coord*vec.head.x.coord;

       return new Vector(_x,_y,_z);
    }

    /**
     * skalaric multiplication
     * @param vec
     * @return
     */
    public double dotProduct (Vector vec) {
        var rettype = vec.head.x.coord*this.head.x.coord+
                vec.head.y.coord*this.head.y.coord+
                vec.head.z.coord*this.head.z.coord;

        return rettype;
    }

    public double lengthSquared () {
    var ret = head.x.coord *head.x.coord;
    ret += head.y.coord *head.y.coord;
    ret += head.z.coord *head.z.coord;

    return ret;
    }

    public double length () {
return Math.sqrt(lengthSquared());
    }

    /**
     * normalize the vector and not returning the result but the current vector
     * @return
     */
    public Vector normalize () {
        var nirmol = this.length();

        head = new Point3D(head.x.coord/nirmol,head.y.coord/nirmol,head.z.coord/nirmol);

        return this;
    }

    /**
     * returns the normalized vector
     * @return
     */
    public Vector normalized () {
        return new Vector(head).normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector vector = (Vector) o;
        return getHead().equals(vector.getHead());
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }
}
