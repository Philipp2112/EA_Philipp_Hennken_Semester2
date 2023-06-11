package org.example.communication;

import com.google.gson.Gson;
import org.example.model.Drone;
import org.example.model.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class DroneControllServer implements Runnable
{
    // Attribute
    private ServerSocket serverSocket;
    private Drone drone = new Drone(new Position(0,0,0));

    // Erstellt private Instanz der Klasse "Server" und macht diese so zu einem Singleton
    private static DroneControllServer instance;

    // Gibt die Instanz zurueck
    public static DroneControllServer getInstance()
    {
        // Es wird ueberprueft, ob eine Instanz noch nicht existiert
        // Ist dies der Fall, wird eine neue Instanz erstellt
        // Ist dies jedoch nicht der Fall, wird die existierende Instant zurueckgegeben
        return instance == null ? instance = new DroneControllServer() : instance;
    }

    // Konstruktor
    private DroneControllServer()
    {
        try
        {
            // Aus dem gespeicherten Port wird ein ServerSocket erstellt
            serverSocket = new ServerSocket(12345);
        }
        catch (IOException e)
        {
            // Falls dabei eine Input-/Output-Exception auftritt wird das Programm via RuntimeException beendet
            throw new RuntimeException(e);
        }
    }

    public DroneControllServer(Drone drone)
    {
        this.drone = drone;
    }


    @Override
    public void run()
    {
        try
        {
            // Aus dem gespeicherten Port wird ein ServerSocket erstellt
            serverSocket = new ServerSocket(55555);
        }
        catch (IOException e)
        {
            // Falls dabei eine Input-/Output-Exception auftritt wird das Programm via RuntimeException beendet
            throw new RuntimeException(e);
        }


        Socket client;
        PrintWriter printWriter;
        BufferedReader bufferedReader = null;
        Gson gson = new Gson();
        try
        {
            System.out.println("Server läuft");
            // Folgender Prozess wird wiederholt:
            while(true)
            {
                System.out.println(drone.getPosition().getX());
                // Sobald ein Client ein Socket oeffnet, wird dieses sofort akzeptiert
                client = serverSocket.accept();

                // Es wird ein PrintWriter zum OutputStreams des Clients geoeffnet
                printWriter = new PrintWriter(client.getOutputStream(), true);

                // Diese sendet alle Daten des Beispiel-Cars ueber die Methode "toJSON"
                printWriter.println(gson.toJson(drone.getPosition()).toString());

                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                drone.setMovement(bufferedReader.readLine());
                System.out.println(drone.getMovement());


                // Danach wird der PrintWriter und das Socket zum Client wieder geschlossen
                printWriter.close();
                client.close();
            }
        }
        catch (IOException e)
        {
            // Falls dabei eine Input-/Output-Exception auftritt wird das Programm via RuntimeException beendet
            throw new RuntimeException(e);
        }
    }
}
