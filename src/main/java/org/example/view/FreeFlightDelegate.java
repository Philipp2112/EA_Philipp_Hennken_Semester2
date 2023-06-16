package org.example.view;

import javafx.beans.binding.Binding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.converter.NumberStringConverter;
import org.example.client.Strings;
import org.example.model.DroneController;
import javafx.beans.binding.Bindings;

import java.io.IOException;


public class FreeFlightDelegate
{
    private static String commandToController = Strings.NOT_MOVING;
    public Label anzeigeX;
    public Label anzeigeY;
    public Label anzeigeZ;
    public Label anzeigeGeschwindigkeit;
    public ProgressBar chargeLevel;
    public Label chargeLevelLabel;

    @FXML
    private void initialize()
    {
        Bindings.bindBidirectional(anzeigeX.textProperty(), DroneController.getClassInstance().getXKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeY.textProperty(), DroneController.getClassInstance().getYKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeZ.textProperty(), DroneController.getClassInstance().getZKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeGeschwindigkeit.textProperty(), DroneController.getClassInstance().getGeschwindigkeitsProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(chargeLevel.progressProperty(), DroneController.getClassInstance().getChargeLevelProperty());
        Bindings.bindBidirectional(chargeLevelLabel.textProperty(), DroneController.getClassInstance().getChargeLevelProperty(), new NumberStringConverter());
    }

    public void onSendWClick()
    {
        commandToController = Strings.W;
    }

    public void onSendAClick()
    {
        commandToController = Strings.A;
    }

    public void onSendDClick()
    {
        commandToController = Strings.D;
    }

    public void onSendSClick()
    {
        commandToController = Strings.S;
    }

    public void onSendStopClick()
    {
        commandToController = Strings.NOT_MOVING;
        System.out.println(chargeLevel.getProgress());
    }

    public void onSendUpClick()
    {
        commandToController = Strings.UP;
    }

    public void onSendDownClick()
    {
        commandToController = Strings.DOWN;
    }

    public void onSendTurnLeftClick()
    {
        commandToController = Strings.TURN_LEFT;
    }

    public void onSendTurnRightClick()
    {
        commandToController = Strings.TURN_RIGHT;
    }

    public static String getCommandToController()
    {
        return commandToController;
    }

    public static void setCommandToController(String command)
    {
        commandToController = command;
    }

    public void openMainMenu()
    {
    }

    public void startServer() throws IOException
    {

    }

    public void stopServer()
    {
        System.exit(32);
    }
}
