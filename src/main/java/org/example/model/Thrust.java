package org.example.model;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;

public class Thrust
{

    private Vector3D thrust;

    public Thrust(Vector3D thrust)
    {
        this.thrust = thrust;
    }

    public Vector3D getThrust()
    {
        return thrust;
    }



    public void setThrust(Vector3D thrust)
    {
        this.thrust = thrust;
    }

    @Override
    public String toString()
    {
        return "Thrust{" +
                thrust +
                '}';
    }
}
