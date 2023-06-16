package org.example.model;

public class Position
{
    private double x;

    //height of the drone is y
    private double y;
    private double z;

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
