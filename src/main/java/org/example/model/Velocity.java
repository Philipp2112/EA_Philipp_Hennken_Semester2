package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Velocity
{
    private double xVelocity;
    private double yVelocity;
    private double zVelocity;

    private Vector3D velocityVector = new Vector3D(xVelocity, yVelocity, zVelocity);

    public Velocity(Vector3D velocityVector)
    {
        this.velocityVector = velocityVector;
    }

    public double getxVelocity()
    {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity)
    {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity()
    {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity)
    {
        this.yVelocity = yVelocity;
    }

    public double getzVelocity()
    {
        return zVelocity;
    }

    public void setzVelocity(double zVelocity)
    {
        this.zVelocity = zVelocity;
    }

    public Vector3D getVelocityVector()
    {
        return velocityVector;
    }

    public void setVelocityVector(Vector3D velocityVector)
    {
        this.velocityVector = velocityVector;
    }

    @Override
    public String toString()
    {
        return "Velocity{" +
                velocityVector +
                '}';
    }
}
