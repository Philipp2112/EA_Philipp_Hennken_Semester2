package org.example.model;

/**This class contains every data the program gets from unity.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class DataPackage
{
    private Position position;
    private Velocity velocity;
    private double lidarSensor;


    /**This constructor initializes the DataPackage object with the given position and lidar sensor data.
     * @param position The position data to be included in the DataPackage.
     * @param lidarSensor The lidar sensor data to be included in the DataPackage.
     * @pre The lidarSensor parameter must be a valid double value representing lidar sensor data.
     * @post The position and idarSensor field are assigned to the position data.
     */
    public DataPackage(Position position, double lidarSensor, Velocity velocity)
    {
        this.position = position;
        this.lidarSensor = lidarSensor;
        this.velocity = velocity;
    }

    public Position getPosition()
    {
        return position;
    }
    public double getLidarSensor()
    {
        return lidarSensor;
    }

    public void setLidarSensor(double lidarSensor)
    {
        this.lidarSensor = lidarSensor;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public Velocity getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Velocity velocity)
    {
        this.velocity = velocity;
    }
}
