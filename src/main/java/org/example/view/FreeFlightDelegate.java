package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import org.example.model.DroneController;
import javafx.beans.binding.Bindings;

import java.io.IOException;


public class FreeFlightDelegate
{
    private static String commandToController = "notMoving";
    public Label anzeigeX;
    public Label anzeigeY;
    public Label anzeigeZ;
    public Label anzeigeGeschwindigkeit;

    @FXML
    private void initialize()
    {
        System.out.println("binding wird gemacht");
        Bindings.bindBidirectional(anzeigeX.textProperty(), DroneController.getClassInstance().getXKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeY.textProperty(), DroneController.getClassInstance().getYKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeZ.textProperty(), DroneController.getClassInstance().getZKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeGeschwindigkeit.textProperty(), DroneController.getClassInstance().getGeschwindigkeitsProperty(), new NumberStringConverter());
    }

    public void onSendWClick()
    {
        commandToController = "W";
    }

    public void onSendAClick()
    {
        commandToController = "A";
    }

    public void onSendDClick()
    {
        commandToController = "X";
    }

    public void onSendSClick()
    {
        commandToController = "S";
    }

    public void onSendStopClick()
    {
        commandToController = "notMoving";
    }

    public void onSendUpClick()
    {
        commandToController = "Up";
    }

    public void onSendDownClick()
    {
        commandToController = "Down";
    }

    public void onSendTurnLeftClick()
    {
        commandToController = "TurnLeft";
    }

    public void onSendTurnRightClick()
    {
        commandToController = "TurnRight";
    }

    public static String getCommandToController()
    {
        return commandToController;
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
