package org.example.client;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.example.model.Drone;
import org.example.model.GpsSensor;
import org.example.model.HightSensor;
import org.example.model.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable
{
    private Socket socket = null;

    public void run()
    {
        socket = verbindungAufbauen("127.0.0.1", 10666);
        Drone drone = new Drone();
        HightSensor hightSensor = new HightSensor();
        GpsSensor gpsSensor = new GpsSensor();

        while (true)
        {
            String[] droneData = csvParser(sendeKommandoUndWarteAufAntwort(socket, Kommandos.GET_DATA));
            if (drone.getPosition() == null)
            {
                drone.setPreviousPosition(new Position(new Vector3D(-122,50,500)));
            } else
            {
                drone.setPreviousPosition(drone.getPosition());
            }
            hightSensor.setHight(Float.parseFloat(droneData[1]));
            gpsSensor.setxCoordinate(Float.parseFloat(droneData[0]));
            gpsSensor.setzCoordinate(Float.parseFloat(droneData[2]));
            drone.setPosition(new Position(new Vector3D(gpsSensor.getxCoordinate(), hightSensor.getHight(), gpsSensor.getzCoordinate())));
            System.out.println(drone.getPosition());
            System.out.println(drone.getPreviousPosition());
            System.out.println(drone.calculateVelocity());
            sendeKommandoUndWarteAufAntwort(socket, Kommandos.ADD_FORCE);
            sleep(MeineKonstanten.GET_DATA_SLEEP);
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

    private String sendeKommandoUndWarteAufAntwort(Socket socket, String command)
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
        {
            throw new RuntimeException(e);
        }
    }

    private String[] csvParser(String string)
    {
        String newString = string.replace(",", ".");
        String[] inputString = newString.split(";");
        return inputString;
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


}
