package org.example.model;

public class GpsSensor extends Sensor
{
    @Override
    public Object calculateSensorData(Object data)
    {
        return null;
    }

    private float xCoordinate;
    private float zCoordinate;



    public float getxCoordinate()
    {
        return xCoordinate;
    }

    public void setxCoordinate(float xCoordinate)
    {
        this.xCoordinate = xCoordinate;
    }

    public float getzCoordinate()
    {
        return zCoordinate;
    }

    public void setzCoordinate(float zCoordinate)
    {
        this.zCoordinate = zCoordinate;
    }
}
