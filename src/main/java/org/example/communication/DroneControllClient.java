package org.example.communication;
import com.google.gson.Gson;
import org.example.client.Constants;
import org.example.client.Strings;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class DroneControllClient implements Runnable
{
    private Socket socket = null;
    private Drone drone = new Drone(new Position(0,0,0));

    public void run()
    {
        socket = verbindungAufbauen(Strings.LOCALHOST, 10666);
        Gson gsonObject = new Gson();

        while (true)
        {
            drone.setPosition(gsonObject.fromJson(sendCommandAndWaitForAnswer(socket, gsonObject.toJson(drone.getMovement())),Position.class));
            sleep(Constants.GET_DATA_SLEEP);
        }

    }



    private void sleep(long duration)
    {
        try
        {
            Thread.sleep(duration);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    private String sendCommandAndWaitForAnswer(Socket socket, String command)
    {
        PrintWriter outToUnity = null;
        BufferedReader inFromUnity = null;
        try
        {
            outToUnity = new PrintWriter(socket.getOutputStream());
            inFromUnity = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToUnity.println(command);
            outToUnity.flush();
            return inFromUnity.readLine();
        }
        catch (IOException e)
        {//TODO was passiert beim neustart von unity?
            throw new RuntimeException(e);
        }
    }


    private Socket verbindungAufbauen(String host, int port)
    {
        Socket newSocket = null;
        try
        {
            newSocket = new Socket(host, port);
        }
        catch (UnknownHostException e)
        {
            System.out.println(Strings.FEHLER_IP_ADRESSE);
        }
        catch (IOException ee)
        {
            System.out.println(Strings.IO_FEHLER_BEI_VERBINDUNGSAUFBAU);
        }
        catch (SecurityException eee)
        {
            System.out.println(Strings.VERBINDUNGSAUFBAU_NICHT_ERLAUBT);
        }
        catch (IllegalArgumentException eeee)
        {
            System.out.println(Strings.ILLEGALER_PORT);
        }
        finally
        {
            return newSocket;
        }
    }

    public Drone getDrone()
    {
        return drone;
    }
}
