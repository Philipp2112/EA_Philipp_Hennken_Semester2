package org.example.view;

import com.google.gson.Gson;
import javafx.application.Platform;
import org.example.model.Drone;
import org.example.model.DroneController;
import org.example.model.Position;

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
                System.out.println(PlanungssoftwareDelegate.getCommandToController());
                outToDroneController = new PrintWriter(clientSocket.getOutputStream(), true);
                outToDroneController.println(PlanungssoftwareDelegate.getCommandToController());
                drone.setPosition(gson.fromJson(inFromDroneControllerServer.readLine(),Position.class));
                Platform.runLater(() ->
                {
                    DroneController.getClassInstance().xKoordinateProperty().setValue(drone.getPosition().getX());
                    DroneController.getClassInstance().yKoordinateProperty().setValue(drone.getPosition().getY());
                    DroneController.getClassInstance().zKoordinateProperty().setValue(drone.getPosition().getZ());
                    DroneController.getClassInstance().getGeschwindigkeitsProperty().setValue(drone.getPosition().getX()-drone.getPosition().getZ());
                });
                Thread.sleep(1000);
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

