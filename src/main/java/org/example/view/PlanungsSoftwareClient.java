package org.example.view;

import com.google.gson.Gson;
import javafx.application.Platform;
import org.example.client.MeineKonstanten;
import org.example.model.Drone;
import org.example.model.DroneController;
import org.example.model.Position;
import org.example.model.Velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlanungsSoftwareClient implements Runnable
{
    private String outputStringToController;
    private Drone drone = new Drone(new Position(0,0,0));

    @Override
    public void run()
    {
        System.out.println("qwwefdq");
        try
        {
            Gson gson = new Gson();
            BufferedReader inFromDroneControllerServer = null;
            PrintWriter outToDroneController = null;
            int i = 0;
            while (true)
            {
                Socket clientSocket = new Socket("localhost", 55555);
                inFromDroneControllerServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //System.out.println(FreeFlightDelegate.getCommandToController());
                outToDroneController = new PrintWriter(clientSocket.getOutputStream(), true);
                outToDroneController.println(FreeFlightDelegate.getCommandToController());
                drone.setPosition(gson.fromJson(inFromDroneControllerServer.readLine(),Position.class));
                drone.setVelocity(new Velocity(DroneController.calculateSpeed(drone.getPosition().getX(), drone.getPosition().getY(), drone.getPosition().getZ(), drone.getPreviousPosition().getX(), drone.getPreviousPosition().getY(), drone.getPreviousPosition().getZ())));
                drone.setPreviousPosition(drone.getPosition());
                Platform.runLater(() ->
                {
                    DroneController.getClassInstance().getXKoordinateProperty().setValue(drone.getPosition().getX());
                    DroneController.getClassInstance().getYKoordinateProperty().setValue(drone.getPosition().getY());
                    DroneController.getClassInstance().getZKoordinateProperty().setValue(drone.getPosition().getZ());
                    DroneController.getClassInstance().getGeschwindigkeitsProperty().setValue(drone.getVelocity());
                });
                Thread.sleep(MeineKonstanten.GET_DATA_SLEEP);
            }
        } catch(UnknownHostException e)
            {
                throw new RuntimeException(e);
            } catch(IOException e)
            {
                throw new RuntimeException(e);
            } catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

    }
    public void setOutputStringToController(String outputStringToController)
    {
        this.outputStringToController = outputStringToController;
    }
}

