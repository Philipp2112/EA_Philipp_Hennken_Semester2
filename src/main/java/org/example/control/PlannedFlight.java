package org.example.control;

import org.example.res.Constants;
import org.example.res.Strings;
import org.example.model.Position;
import org.example.view.FreeFlightDelegate;
import org.example.view.Planning;

/** This class plans a flight to every coordinate the user puts in the list of the GUI.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class PlannedFlight implements Runnable
{

    private final DroneController droneController;

    /** This constructor sets an object of DroneController to the DroneController instance and returns it.
     *
     * @pre The DroneController instance must be not null.
     * @post The droneController field is assigned an instance of the DroneController class.
     */
    public PlannedFlight()
    {
       this.droneController = DroneController.getClassInstance();
    }

    /**This method runs the thread that lets the drone fly to every coordinate in the table of the GUI.
     *
     * @throws RuntimeException if an InterruptedException occurs during the thread sleep.
     * @pre
     * @post The drone has flown to each destination specified in the Planning.
     *
     */
    @Override
    public void run()
    {
        for (Position position: Planning.getPositions())
        {
            double destinationX = position.getX();
            double destinationY = position.getY();
            double destinationZ = position.getZ();
            flyToDestination(destinationX,destinationY,destinationZ);
            try
            {
                Thread.sleep(Constants.WAIT_AT_DESTINATION_SLEEP);
            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    /**This method lets the drone fly to the correct destination.
     *
     * @param destinationX The x-coordinate of the destination position.
     * @param destinationY The y-coordinate of the destination position.
     * @param destinationZ The z-coordinate of the destination position.
     * @pre The drone must be in a navigable state.
     * @post The drone has flown to the destination coordinates.
     */
    private void flyToDestination(double destinationX, double destinationY, double destinationZ)
    {
        flyToYDestinationAndStop(Constants.MAX_HIGHT_OF_MOUNTAINS_PLUS_TWENTY);
        flyToXDestinationAndStop(destinationX);
        flyToZDestinationAndStop(destinationZ);
        flyToYDestinationAndStop(destinationY);
    }

    /** This method lets the drone fly to the correct y-coordinate.
     *
     * @param destinationY The y-coordinate destination to which the drone should be moved.
     * @pre The destinationY parameter must be within the valid range of y-coordinates.
     * @post The drone has reached the specified y-coordinate destination and is hovering in place.
     */
    private void flyToYDestinationAndStop(double destinationY)
    {
        if (droneController.getYKoordinateProperty().getValue() <= destinationY)
        {
            while (droneController.getYKoordinateProperty().getValue() <= destinationY - Constants.BRAKING_DISTANCE_Y)
            {
                FreeFlightDelegate.setCommandToController(Strings.UP);
            }
        }
        else
        {
            while (droneController.getYKoordinateProperty().getValue() >= destinationY + Constants.BRAKING_DISTANCE_Y)
            {
                FreeFlightDelegate.setCommandToController(Strings.DOWN);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }

    /** This method lets the drone fly to the correct z-coordinate.
     *
     * @param destinationZ The z-coordinate destination to which the drone should be moved.
     * @pre The destinationZ parameter must be within the valid range of z-coordinates.
     * @post The drone has reached the specified y-coordinate destination and is hovering in place.
     */
    private void flyToZDestinationAndStop(double destinationZ)
    {
        if (droneController.getZKoordinateProperty().getValue() <= destinationZ)
        {
            while (droneController.getZKoordinateProperty().getValue() <= destinationZ - Constants.BRAKING_DISTANCE_Z)
            {
                FreeFlightDelegate.setCommandToController(Strings.W);
            }
        }
        else
        {
            while (droneController.getZKoordinateProperty().getValue() >= destinationZ + Constants.BRAKING_DISTANCE_Z)
            {
                FreeFlightDelegate.setCommandToController(Strings.S);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }

    /** This method lets the drone fly to the correct x-coordinate.
     *
     * @param destinationX The x-coordinate destination to which the drone should be moved.
     * @pre The destinationX parameter must be within the valid range of x-coordinates.
     * @post The drone has reached the specified x-coordinate destination and is hovering in place.
     */
    private void flyToXDestinationAndStop(double destinationX)
    {
        if (droneController.getXKoordinateProperty().getValue() <= destinationX)
        {
            while (droneController.getXKoordinateProperty().getValue() <= destinationX - Constants.BRAKING_DISTANCE_X)
            {
                FreeFlightDelegate.setCommandToController(Strings.D);
            }
        }
        else
        {
            while (droneController.getXKoordinateProperty().getValue() >= destinationX + Constants.BRAKING_DISTANCE_X)
            {
                FreeFlightDelegate.setCommandToController(Strings.A);
            }
        }
        FreeFlightDelegate.setCommandToController(Strings.NOT_MOVING);
    }
}
