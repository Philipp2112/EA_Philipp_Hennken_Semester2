package org.example.model;

public abstract class Actuator
{
    Object ActuatorData;

    public abstract Object calculateActuatorData();

    public Object getActuatorData()
    {
        return ActuatorData;
    }

    public void setActuatorData(Object actuatorData)
    {
        ActuatorData = actuatorData;
    }
}
