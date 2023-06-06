package org.example.model;

public class HightSensor extends Sensor
{

    @Override
    public Object calculateSensorData(Object data)
    {
        return null;
    }
    private float hight;

    public float getHight()
    {
        return hight;
    }

    public void setHight(float hight)
    {
        this.hight = hight;
    }
}
