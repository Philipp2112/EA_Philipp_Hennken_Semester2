package org.example.model;

/** THis class implements a drone with all its components.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Drone
{
    private Battery battery;
    private Velocity velocity = new Velocity(0,0,0);
    private Position position;
    private String movement;
    private LidarSensor lidarSensor = new LidarSensor(0.0);

    /** This constructor creates a new Drone with the specified position and a Battery with chargeLevel one.
     *
     * @param position The initial position of the drone.
     * @pre The position parameter must be a valid Position object.
     * @post A new instance of the Drone class is created.
     */
    public Drone(Position position)
    {
        this.position = position;
        this.battery = new Battery(1);
    }

    public Velocity getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Velocity velocity)
    {
        this.velocity = velocity;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public String getMovement()
    {
        return movement;
    }

    public void setMovement(String movement)
    {
        this.movement = movement;
    }

    public void setBattery(Battery battery)
    {
        this.battery = battery;
    }

    public Battery getBattery()
    {
        return battery;
    }

    public LidarSensor getLidarSensor()
    {
        return lidarSensor;
    }

    public void setLidarSensor(LidarSensor lidarSensor)
    {
        this.lidarSensor = lidarSensor;
    }
}
