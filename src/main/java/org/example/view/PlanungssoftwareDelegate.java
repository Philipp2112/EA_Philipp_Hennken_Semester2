package org.example.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.converter.NumberStringConverter;
import org.example.model.DroneController;
import javafx.beans.binding.Bindings;


public class PlanungssoftwareDelegate
{
    public Label anzeigeX;

    @FXML
    private void initalize()
    {
        Bindings.bindBidirectional(anzeigeX.textProperty(), DroneController.getClassInstance().xKoordinateProperty(), new NumberStringConverter());
    }
}
