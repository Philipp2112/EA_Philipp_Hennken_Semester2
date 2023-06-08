package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.example.client.MeineKonstanten;

public class Drone
{
    private Battery battery;
    private Thrust thrust;
    private Orientation orientation;
    private Velocity velocity;
    private Position position;

    private Position previousPosition;

    public Drone()
    {

    }

    public Drone(Battery battery, Thrust thrust, Orientation orientation, Velocity velocity, Position position)
    {
        this.battery = battery;
        this.thrust = thrust;
        this.orientation = orientation;
        this.velocity = velocity;
        this.position = position;
    }

    public Velocity calculateVelocity()
    {
        Vector3D velocityVector = new Vector3D(getPosition().getxCoordinate() - getPreviousPosition().getxCoordinate(), 0 , getPosition().getzCoordinate() - getPreviousPosition().getzCoordinate());
        //velocityVector.scalarMultiply(1/MeineKonstanten.GET_DATA_SLEEP);
        return new Velocity(velocityVector);
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

    public Velocity getVelocity()
    {
        return velocity;
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
}
