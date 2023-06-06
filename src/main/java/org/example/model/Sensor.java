package org.example.model;

public abstract class Sensor
{
    Object sensorData;

    public abstract Object calculateSensorData(Object data);

    public Object getSensorData()
    {
        return sensorData;
    }

    public void setSensorData(Object sensorData)
    {
        this.sensorData = sensorData;
    }
}
