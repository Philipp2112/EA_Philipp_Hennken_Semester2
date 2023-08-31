package org.example.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import org.example.res.Constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**This class represents a battery of a drone.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Battery extends Actuator
{
    private double chargeLevel;

    /**Creates a battery with a chargeLevel in percent.
     *
     * @param chargeLevel The initial charge level of the drone's battery.
     * @pre chargeLevel value must be a valid double integer.
     * @post the batteries charge level is set to the parameters value.
     * @post the batteryLevel has been calculated.
     */
    public Battery(double chargeLevel)
    {
        this.chargeLevel = chargeLevel;
        calculateActuatorData();
    }

    /** This method lowers the chargeLevel at every method call.
     *
     * @pre The chargeLevel is a valid double value.
     * @post The chargeLevel is 0.000002 percent points lower tha before.
     */
    @Override
    public void calculateActuatorData()
    {
            chargeLevel = chargeLevel - Constants.CHARGE_LEVEL_CONSTANT;
            if (chargeLevel <= 0)
            {
                System.exit(0);
            }
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
