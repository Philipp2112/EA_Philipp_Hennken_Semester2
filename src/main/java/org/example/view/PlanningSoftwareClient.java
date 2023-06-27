package org.example.view;

import com.google.gson.Gson;
import javafx.application.Platform;
import org.example.client.Constants;
import org.example.client.Strings;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**This class
 * @author philipp.hennken
 * @version 18.0.2
 */
public class PlanningSoftwareClient implements Runnable
{
    private final Drone drone = DroneController.getClassInstance().getDrone();
    private DataPackage dataPackage = new DataPackage(new Position(0,0,0), 0.0, new Velocity(0,0,0));

    /** Runs the continuous loop to communicate with the drone controller server and update the drone's state.
     * @pre The drone object and its components are initialized.
     * @post The drone's attributes are updated.
     */
    @Override
    public synchronized void run()
    {
        try
        {
            Gson gson = new Gson();
            BufferedReader inFromDroneControllerServer = null;
            PrintWriter outToDroneController = null;
            int i = 0;
            while (true)
            {
                Socket clientSocket = new Socket(Strings.LOCALHOST, Constants.PORT_CONTROLLER_PLANNING);
                inFromDroneControllerServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                outToDroneController = new PrintWriter(clientSocket.getOutputStream(), true);
                outToDroneController.println(FreeFlightDelegate.getCommandToController());
                dataPackage = gson.fromJson(inFromDroneControllerServer.readLine(),DataPackage.class);
                drone.getBattery().calculateActuatorData();
                drone.setLidarSensor(new LidarSensor(dataPackage.getLidarSensor()));
                drone.setPosition(dataPackage.getPosition());
                drone.setVelocity(new Velocity(drone.getVelocity().getxVelocity(), drone.getVelocity().getyVelocity(), drone.getVelocity().getzVelocity()));
                Platform.runLater(() ->
                {
                    setPropertyValues();

                });
                Thread.sleep(Constants.GET_DATA_SLEEP);
            }
        } catch(IOException | InterruptedException e)
            {
                throw new RuntimeException(e);
            }

    }

    /**Sets the property values of the drone controller based on the current state of the drone.
     * @pre The drone object and its components are initialized.
     * @post The property values of the drone controller are updated.
     */
    private void setPropertyValues()
    {
        DroneController.getClassInstance().getXKoordinateProperty().setValue(drone.getPosition().getX());
        DroneController.getClassInstance().getYKoordinateProperty().setValue(drone.getPosition().getY());
        DroneController.getClassInstance().getZKoordinateProperty().setValue(drone.getPosition().getZ());
        DroneController.getClassInstance().getxVelocityProperty().setValue(drone.getVelocity().getxVelocity());
        DroneController.getClassInstance().getyVelocityProperty().setValue(drone.getVelocity().getyVelocity());
        DroneController.getClassInstance().getzVelocityProperty().setValue(drone.getVelocity().getzVelocity());
        DroneController.getClassInstance().getChargeLevelProperty().setValue(drone.getBattery().getChargeLevel());
        DroneController.getClassInstance().getDistanceToGroundProperty().setValue(drone.getLidarSensor().getDistanceToGround());
    }
}

