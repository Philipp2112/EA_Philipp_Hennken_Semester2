package org.example.communication;

public class DroneControllerMain
{
    public static void main(String[] args)
    {
        DroneControllClient droneClient = new DroneControllClient();
        DroneControllServer droneServer = new DroneControllServer(droneClient.getDrone());
        Thread clientThread = new Thread(droneClient);
        Thread serverThread = new Thread(droneServer);
        clientThread.start();
        serverThread.start();
    }


}
