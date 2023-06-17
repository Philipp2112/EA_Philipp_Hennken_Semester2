package org.example.view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import org.example.client.Strings;
import org.example.model.DroneController;
import org.example.model.Position;

public class PlannedFlight implements Runnable
{

    @Override
    public void run()
    {
        double destinyX = Planning.getPositions().get(0).getX();
        double destinyY = Planning.getPositions().get(0).getY();
        double destinyZ = Planning.getPositions().get(0).getZ();
        while(DroneController.getClassInstance().getYKoordinateProperty().getValue() <= 130)
        {
            FreeFlightDelegate.setCommandToController(Strings.UP);
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);

        if (DroneController.getClassInstance().getXKoordinateProperty().getValue() <= destinyX)
        {
            while (DroneController.getClassInstance().getXKoordinateProperty().getValue() <= destinyX - 7.5)
            {
                FreeFlightDelegate.setCommandToController(Strings.D);
            }
        }
        else
        {
            while (DroneController.getClassInstance().getXKoordinateProperty().getValue() >= destinyX + 7.5)
            {
                FreeFlightDelegate.setCommandToController(Strings.A);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);

        if (DroneController.getClassInstance().getZKoordinateProperty().getValue() <= destinyZ)
        {
            while (DroneController.getClassInstance().getZKoordinateProperty().getValue() <= destinyZ - 20)
            {
                FreeFlightDelegate.setCommandToController(Strings.W);
            }
        }
        else
        {
            while (DroneController.getClassInstance().getZKoordinateProperty().getValue() >= destinyZ + 20)
            {
                FreeFlightDelegate.setCommandToController(Strings.S);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);

        if (DroneController.getClassInstance().getYKoordinateProperty().getValue() <= destinyY)
        {
            while (DroneController.getClassInstance().getYKoordinateProperty().getValue() <= destinyY - 20.6)
            {
                FreeFlightDelegate.setCommandToController(Strings.UP);
            }
        }
        else
        {
            while (DroneController.getClassInstance().getYKoordinateProperty().getValue() >= destinyY + 20.6)
            {
                FreeFlightDelegate.setCommandToController(Strings.DOWN);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);

    }
    public void flyRoute(ObservableList<Position> positions)
    {
        for (Position position : positions)
        {
            double destinyX = position.getX();
            double destinyY = position.getY();
            double destinyZ = position.getZ();

            flyToDestiny(destinyX,destinyY,destinyZ);
        }
    }

    private void flyToDestiny(double destinyX, double destinyY, double destinyZ)
    {
        Platform.runLater(() ->
        {
            while(DroneController.getClassInstance().getYKoordinateProperty().getValue() <= 130)
            {
                FreeFlightDelegate.setCommandToController(Strings.UP);
            }
            FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
        });

    }
}
