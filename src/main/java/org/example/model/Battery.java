package org.example.model;


import org.example.control.Timer;

public class Battery extends Actuator
{
    private int batteryLevel;
    //capacity of the battery in mAh
    private int batteryCapacity = 3000;
    private int chargeLevel = 3000;


    @Override
    public Object calculateActuatorData()
    {
        batteryLevel = calculateBatteryLevel(chargeLevel);
        return batteryLevel;
    }

    private int calculateBatteryLevel(int stateOfCharge)
    {
        return stateOfCharge - calculateTimeInAir();
    }
    private int calculateTimeInAir()
    {
        return Timer.calculatePassedMinutesSinceLastMethodCall();
    }

    public int getBatteryLevel()
    {
        return batteryLevel;
    }
}
