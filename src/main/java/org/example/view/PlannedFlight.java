package org.example.view;

import org.example.client.Strings;
import org.example.model.DroneController;
import org.example.model.Position;

public class PlannedFlight implements Runnable
{

    private DroneController droneController;

    public PlannedFlight()
    {
       this.droneController = DroneController.getClassInstance();
    }

    @Override
    public void run()
    {
        for (Position position:Planning.getPositions())
        {
            double destinyX = position.getX();
            double destinyY = position.getY();
            double destinyZ = position.getZ();
            flyToDestiny(destinyX,destinyY,destinyZ);
            try
            {
                //TODO Konstante
                Thread.sleep(5000);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    private void flyToDestiny(double destinyX, double destinyY, double destinyZ)
    {
        //TODO Konstante
        flyToYDestinyAndStop(130);
        flyToXDestinyAndStop(destinyX);
        flyToZDestinyAndStop(destinyZ);
        flyToYDestinyAndStop(destinyY);

    }

    private void flyToYDestinyAndStop(double destinyY)
    {
        if (droneController.getYKoordinateProperty().getValue() <= destinyY)
        { //TODO Konstanten
            while (droneController.getYKoordinateProperty().getValue() <= destinyY - 20.6)
            {
                FreeFlightDelegate.setCommandToController(Strings.UP);
            }
        }
        else
        {
            while (droneController.getYKoordinateProperty().getValue() >= destinyY + 20.6)
            {
                FreeFlightDelegate.setCommandToController(Strings.DOWN);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }

    private void flyToZDestinyAndStop(double destinyZ)
    {
        if (droneController.getZKoordinateProperty().getValue() <= destinyZ)
        {
            while (droneController.getZKoordinateProperty().getValue() <= destinyZ - 20)
            {
                FreeFlightDelegate.setCommandToController(Strings.W);
            }
        }
        else
        {
            while (droneController.getZKoordinateProperty().getValue() >= destinyZ + 20)
            {
                FreeFlightDelegate.setCommandToController(Strings.S);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }

    private void flyToXDestinyAndStop(double destinyX)
    {
        if (droneController.getXKoordinateProperty().getValue() <= destinyX)
        {
            while (droneController.getXKoordinateProperty().getValue() <= destinyX - 7.5)
            {
                FreeFlightDelegate.setCommandToController(Strings.D);
            }
        }
        else
        {
            while (droneController.getXKoordinateProperty().getValue() >= destinyX + 7.5)
            {
                FreeFlightDelegate.setCommandToController(Strings.A);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }
}
