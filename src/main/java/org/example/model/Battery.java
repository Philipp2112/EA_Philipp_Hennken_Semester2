package org.example.model;


import org.example.control.Timer;

public class Battery extends Actuator
{
    private double chargeLevel;

    public Battery(double chargeLevel)
    {
        Timer.start();
        this.chargeLevel = chargeLevel;
        calculateActuatorData();
    }

    @Override
    public void calculateActuatorData()
    {
            chargeLevel = chargeLevel - 0.000005;
            //TODO korrekten Wert finden
    }

    public double getChargeLevel()
    {
        return chargeLevel;
    }

    public void setChargeLevel(double chargeLevel)
    {
        this.chargeLevel = chargeLevel;
    }
}
