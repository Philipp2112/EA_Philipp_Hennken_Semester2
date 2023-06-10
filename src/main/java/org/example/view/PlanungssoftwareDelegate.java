package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import org.example.model.DroneController;
import javafx.beans.binding.Bindings;


public class PlanungssoftwareDelegate
{
    public Label anzeigeX;
    public Label anzeigeY;
    public Label anzeigeZ;
    public Label anzeigeGeschwindigkeit;

    @FXML
    private void initialize()
    {
        System.out.println("binding wird gemacht");
        Bindings.bindBidirectional(anzeigeX.textProperty(), DroneController.getClassInstance().xKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeY.textProperty(), DroneController.getClassInstance().yKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeZ.textProperty(), DroneController.getClassInstance().zKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeGeschwindigkeit.textProperty(), DroneController.getClassInstance().getGeschwindigkeitsProperty(), new NumberStringConverter());
    }
}
