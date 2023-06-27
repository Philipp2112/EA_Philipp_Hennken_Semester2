package org.example.model;

/**This class keeps a position with its attributes.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Position
{
    private double x;

    //height of the drone is y
    private double y;
    private double z;

    /** Creates a position object in a three-dimensional map.
     * @param x The X coordinate.
     * @param y The Y coordinate.
     * @param z The Z coordinate.
     * @pre The coordinates must be valid values.
     * @post A Position object is created with the specified coordinates.
     *
     */
    public Position(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

   // private Vector3D positionVector = new Vector3D(xCoordinate, yCoordinate, zCoordinate);


    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(double z)
    {
        this.z = z;
    }

    @Override
    public String toString()
    {
        return "Position{" +
                "xCoordinate=" + x +
                ", yCoordinate=" + y +
                ", zCoordinate=" + z +
                '}';
    }
}
