package org.example.control;

/**This class starts threads for the DroneController.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class DroneControllerMain
{
    /**This method is called when the programm is executed.
     * It starts every Thread needed for the connections.
     *
     * @param args The command-line arguments passed to the program.
     * @pre
     * @post startThreads() is called.
     */
    public static void main(String[] args)
    {
        startThreads();
    }

    /** This method starts the client and server threads for the connections of the drone-controllers and Unity.
     * @pre None.
     * @post The client and server threads are created and started for drone control.
     * @post The drone control operations are performed concurrently by the client and server threads.
     */
    private static void startThreads()
    {
        DroneControllClient droneClient = new DroneControllClient();
        DroneControllServer droneServer = new DroneControllServer(droneClient.getDrone());
        Thread clientThread = new Thread(droneClient);
        Thread serverThread = new Thread(droneServer);
        clientThread.start();
        serverThread.start();
    }


}
