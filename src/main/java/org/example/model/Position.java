package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Position
{
    public Position(Vector3D positionVector)
    {
        this.positionVector = positionVector;
        this.xCoordinate = positionVector.getX();
        this.yCoordinate = positionVector.getY();
        this.zCoordinate = positionVector.getZ();
    }

    private double xCoordinate;
    //height of the drone is y
    private double yCoordinate;
    private double zCoordinate;

    private Vector3D positionVector = new Vector3D(xCoordinate, yCoordinate, zCoordinate);


    public double getxCoordinate()
    {
        return xCoordinate;
    }

    public void setxCoordinate(double xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }

    public double getyCoordinate()
    {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate)
    {
        this.yCoordinate = yCoordinate;
    }

    public double getzCoordinate()
    {
        return zCoordinate;
    }

    public void setzCoordinate(double zCoordinate)
    {
        this.zCoordinate = zCoordinate;
    }

    public Vector3D getPositionVector()
    {
        return positionVector;
    }

    public void setPositionVector(Vector3D positionVector)
    {
        this.positionVector = positionVector;
    }

    @Override
    public String toString()
    {
        return "Position{\n" +
                positionVector.getX() + "\n"  + positionVector.getY() + "\n" + positionVector.getZ() +
                "}\n\n";
    }
}
