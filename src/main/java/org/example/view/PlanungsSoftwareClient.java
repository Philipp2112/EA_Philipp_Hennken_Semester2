package org.example.view;

import com.google.gson.Gson;
import javafx.application.Platform;
import org.example.model.Drone;
import org.example.model.DroneController;
import org.example.model.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class PlanungsSoftwareClient implements Runnable
{
    private Drone drone = new Drone(new Position(0,0,0));

    @Override
    public void run()
    {
        System.out.println("qwwefdq");
        try
        {
            Gson gson = new Gson();
            while (true)
            {
                Socket clientSocket = new Socket("localhost", 55555);
                BufferedReader inFromDroneControllerServer = null;
                inFromDroneControllerServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                drone.setPosition(gson.fromJson(inFromDroneControllerServer.readLine(),Position.class));
                Platform.runLater(() ->
                {
                    DroneController.getClassInstance().xKoordinateProperty().setValue(drone.getPosition().getX());
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
    }

