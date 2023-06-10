package org.example.model;

public class Velocity
{
    private double xVelocity;
    private double yVelocity;
    private double zVelocity;

    private double velocity = 0.0;

    public Velocity(double velocityVector)
    {
        this.velocity = velocityVector;
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

    public double getVelocity()
    {
        return velocity;
    }

    public void setVelocity(double velocity)
    {
        this.velocity = velocity;
    }

    @Override
    public String toString()
    {
        return "Velocity{" +
                velocity +
                '}';
    }
}
