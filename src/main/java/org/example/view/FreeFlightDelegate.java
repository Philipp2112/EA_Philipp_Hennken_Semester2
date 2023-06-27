package org.example.view;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import org.example.client.Constants;
import org.example.client.Strings;
import org.example.model.DroneController;


/**This class is the connection between the planned flight and the GUI.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class FreeFlightDelegate
{
    private static String commandToController = Strings.NOT_MOVING;
    public Label anzeigeX;
    public Label anzeigeY;
    public Label anzeigeZ;
    public Label anzeigeXVelocity;
    public ProgressBar chargeLevelProgressBar;
    public Label chargeLevelLabel;
    public Slider chargingSlider;
    public Label abstandZumBoden;
    public Label anzeigeYVelocity;
    public Label anzeigeZVelocity;

    /** Calls the method to set every binding.
     * Is called when the FreeFlightDelegate is referenced.
     *
     * @pre none.
     * @post Every binding will be made.
     */
    @FXML
    private void initialize()
    {
        Planning.setBindings(anzeigeX, anzeigeY, anzeigeZ, anzeigeXVelocity, abstandZumBoden, chargeLevelProgressBar, chargeLevelLabel, anzeigeYVelocity, anzeigeZVelocity);
    }

    /** If the W-Button is clicked. The commandToController value will be set to "W".
     * @pre W-Button is pushed.
     * @post commandToController value is "W".
     */
    public void onSendWClick()
    {
        commandToController = Strings.W;
    }

    /** If the A-Button is clicked. The commandToController value will be set to "A".
     @pre A-Button is pushed.
     @post commandToController value is "A".
     */
    public void onSendAClick()
    {
        commandToController = Strings.A;
    }

    /** If the D-Button is clicked. The commandToController value will be set to "D".
     *

     @pre D-Button is pushed.
     @post commandToController value is "D".
     */
    public void onSendDClick()
    {
        commandToController = Strings.D;
    }

    /** If the S-Button is clicked. The commandToController value will be set to "S".
     @pre S-Button is pushed.
     @post commandToController value is "S".
     */
    public void onSendSClick()
    {
        commandToController = Strings.S;
    }

    /** If the "notMoving" button is clicked. The commandToController value will be set to "notMoving".
     @pre "notMoving" button is pushed.
     @post commandToController value is "notMoving".
     */
    public void onSendStopClick()
    {
        commandToController = Strings.NOT_MOVING;
    }

    /** If the "up" button is clicked. The commandToController value will be set to "up".
     @pre "up" button is pushed.
     @post commandToController value is "up".
     */
    public void onSendUpClick()
    {
        commandToController = Strings.UP;
    }

    /** If the "down" button is clicked. The commandToController value will be set to "down".
     @pre "down" button is pushed.
     @post commandToController value is "down".
     */
    public void onSendDownClick()
    {
        commandToController = Strings.DOWN;
    }

    /** If the "turnLeft" button is clicked. The commandToController value will be set to "turnLeft".
     @pre "turnLeft" button is pushed.
     @post commandToController value is "turnLeft".
     */
    public void onSendTurnLeftClick()
    {
        commandToController = Strings.TURN_LEFT;
    }

    /** If the "turnRight" button is clicked. The commandToController value will be set to "turnRight".
     * @pre "turnRight" button is pushed.
     * @post commandToController value is "turnRight".
     */
    public void onSendTurnRightClick()
    {
        commandToController = Strings.TURN_RIGHT;
    }

    /**
     * If the chargeFrone button is clicked the chargeLevel will set to the sliders Value.
     * @pre The chargingSlider value is set.
     * @post The drone's battery charge level is updated.
     */
    public void chargeDrone()
    {
        DroneController.getClassInstance().getDrone().getBattery().setChargeLevel(chargingSlider.getValue() / Constants.NUMBER_TO_PERCENT);
    }



    public static String getCommandToController()
    {
        return commandToController;
    }

    public static void setCommandToController(String command)
    {
        commandToController = command;
    }

}
