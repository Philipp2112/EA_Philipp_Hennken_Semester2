package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Drone
{
    private Battery battery;
    private Thrust thrust;
    private Orientation orientation;
    private Velocity velocity = new Velocity(0.0);
    private Position position;
    private Position previousPosition;
    private String movement;

    public Drone(Position position)
    {
        this.position = position;
    }


    public Number calculateVelocity()
    {
        Vector3D velocityVector = new Vector3D(getPosition().getX() - getPreviousPosition().getX(), 0 , getPosition().getZ() - getPreviousPosition().getZ());
        this.velocity = new Velocity(velocityVector.getY());
        //velocityVector.scalarMultiply(1/MeineKonstanten.GET_DATA_SLEEP);
        return velocityVector.getX();
    }

    public Orientation getOrientation()
    {
        return orientation;
    }

    public void setOrientation(Orientation orientation)
    {
        this.orientation = orientation;
    }

    public Position getPreviousPosition()
    {
        return previousPosition;
    }

    public void setPreviousPosition(Position previousPosition)
    {
        this.previousPosition = previousPosition;
    }

    public Thrust getThrust()
    {
        return thrust;
    }

    public void setThrust(Thrust thrust)
    {
        this.thrust = thrust;
    }

    public Number getVelocity()
    {
        return velocity.getVelocity();
    }

    public void setVelocity(Velocity velocity)
    {
        this.velocity = velocity;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public String getMovement()
    {
        return movement;
    }

    public void setMovement(String movement)
    {
        this.movement = movement;
    }
}
