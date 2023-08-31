package org.example.control;
import com.google.gson.Gson;
import org.example.res.Constants;
import org.example.res.Strings;
import org.example.model.DataPackage;
import org.example.model.Drone;
import org.example.model.Position;
import org.example.model.Velocity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/** This class represents a server for handling request of the planning software and sends data of a drone
 * it gets from Unity.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class DroneControllServer implements Runnable
{

    private ServerSocket serverSocket;
    private final Drone drone;
    private DataPackage dataPackage = new DataPackage(new Position(0,0,0), 0.0 , new Velocity(0,0,0));

    /** This constructor sets the drone to the given drone.
     *
     * @param drone Drone object that is associated with the server.
     * @pre drone must not be null.
     * @post The DroneControllServer instance is created with the specified Drone object.
     */
    public DroneControllServer(Drone drone)
    {
        this.drone = drone;
    }

    /** This method runs the Thread for the server of the droneController wich sends data to the planning software if
     * it gets a request.
     *
     * @throws RuntimeException if an I/O error occurs during the server operation.
     * @pre The server socket port must be available and not already in use.
     * @post The server is running and handling client connections for data exchange with the drone.
     */
    @Override
    public void run()
    {
        try
        {
            serverSocket = new ServerSocket(Constants.PORT_CONTROLLER_PLANNING);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        Socket client;
        PrintWriter printWriter;
        BufferedReader bufferedReader = null;
        Gson gson = new Gson();
        try
        {
            System.out.println(Strings.SERVER_IS_RUNNING);
            while(true)
            {
                dataPackage.setPosition(drone.getPosition());
                dataPackage.setLidarSensor(drone.getLidarSensor().getDistanceToGround());
                dataPackage.setVelocity(drone.getVelocity());
                client = serverSocket.accept();
                printWriter = new PrintWriter(client.getOutputStream(), true);
                printWriter.println(gson.toJson(dataPackage));
                bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                drone.setMovement(bufferedReader.readLine());
                printWriter.close();
                client.close();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
