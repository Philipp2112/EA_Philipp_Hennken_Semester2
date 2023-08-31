package org.example.control;
import com.google.gson.Gson;
import org.example.res.Constants;
import org.example.res.Strings;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**This class is the client of the DroneController setting up a connection to Unity.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class DroneControllClient implements Runnable
{
    private Socket socket = null;
    private final Drone drone = DroneController.getClassInstance().getDrone();
    private DataPackage dataPackage = new DataPackage(new Position(0,0,0), 0.0, new Velocity(0,0,0));

    /**
     Runs the communication loop between the controller and the Unity simulation.This method establishes a socket connection to the Unity simulation at the specified host and port.
     It continuously sends movement commands to the simulation and receives data packages containing drone position and lidar sensor information. The loop runs indefinitely until the program is terminated.
     @pre The host and port values must be valid and accessible.
     @post The drone's position and lidar sensor information are updated based on the received data package.
     */
    public void run()
    {
        socket = buildConnection(Strings.LOCALHOST, Constants.PORT_CONTROLLER_UNITY);
        Gson gsonObject = new Gson();

        while (true)
        {
            try
            {
                dataPackage = gsonObject.fromJson(sendCommandAndWaitForAnswer(socket, gsonObject.toJson(drone.getMovement())),DataPackage.class);
            } catch (ConnectionLostException e)
            {
                throw new RuntimeException(e);
            }
            drone.setPosition(dataPackage.getPosition());
            drone.getLidarSensor().setDistanceToGround(dataPackage.getLidarSensor());
            drone.setVelocity(dataPackage.getVelocity());
            System.out.println(drone.getVelocity().toString());
            sleep(Constants.GET_DATA_SLEEP);
        }
    }


    /**
     This method pauses the current thread for the given duration, allowing other threads to execute.
     The duration is specified in milliseconds.
     @param duration The duration in milliseconds for which the thread should be sleeping.
     @throws RuntimeException if the thread is interrupted while sleeping.
     @pre The duration must be a non-negative value.
     @post The execution of the current thread is paused for the specified duration.
     */
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

    /**
     * It sends the specified command to the simulation and waits for the response.
     * The response is returned as a string.
     *
     *@param socket The socket used for communication with the Unity simulation.
     *@param command The command to be sent to the Unity simulation.
     *@return The response received from the Unity simulation.
     *@throws RuntimeException if an I/O error occurs during the communication.
     *@pre The socket must be open and connected to the Unity simulation.
     *@pre The command must be a non-null string.
     *@post The command is sent to the Unity simulation, and the response is returned as a string.
     *@post The input and output streams with the Unity simulation are properly closed.
     */
    private String sendCommandAndWaitForAnswer(Socket socket, String command) throws ConnectionLostException
    {
        PrintWriter outToUnity = null;
        BufferedReader inFromUnity = null;
        String inFromUnityString;
        try
        {
            outToUnity = new PrintWriter(socket.getOutputStream());
            inFromUnity = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outToUnity.println(command);
            outToUnity.flush();
            inFromUnityString = inFromUnity.readLine();
            return inFromUnityString;
        }
        catch (IOException | NullPointerException exception)
        {
            throw new ConnectionLostException(Strings.CONNECTION_LOST_TO_UNITY);
        }
    }


    /** This method sets up a connection between a host and port and this Socket.
     *
     *@param host The host address to connect to.
     *@param port The port number to connect to.
     *@return The socket object representing the connection to the host and port.
     *@pre The host must be a valid IP address or domain name.
     *@post If an exception occurs during the connection building process, an appropriate error message is printed.
     */
    private Socket buildConnection(String host, int port)
    {
        Socket newSocket = null;
        try
        {
            newSocket = new Socket(host, port);
        }
        catch (UnknownHostException e)
        {
            System.out.println(Strings.ERROR_IP_ADRESSE);
        }
        catch (IOException ee)
        {
            System.out.println(Strings.IO_ERROR_DURING_CONNECTION_BUILD);
        }
        catch (SecurityException eee)
        {
            System.out.println(Strings.CONNECTION_DENIED);
        }
        catch (IllegalArgumentException eeee)
        {
            System.out.println(Strings.ILLEGAL_PORT);
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
