package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.example.client.Constants;

public class Drone
{
    private Battery battery;
    private Velocity velocity = new Velocity(0);
    private Position position;
    private Position previousPosition = new Position(Constants.START_X_VALUE,Constants.START_Y_VALUE,Constants.START_Z_VALUE);
    private String movement;

    public Drone(Position position)
    {
        this.position = position;
        this.battery = new Battery(1);
    }

    public Position getPreviousPosition()
    {
        return previousPosition;
    }

    public void setPreviousPosition(Position previousPosition)
    {
        this.previousPosition = previousPosition;
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

    public void setBattery(Battery battery)
    {
        this.battery = battery;
    }

    public Battery getBattery()
    {
        return battery;
    }
}
