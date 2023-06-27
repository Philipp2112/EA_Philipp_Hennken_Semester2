package org.example.model;

/**This class represents a lidarSensor of the drone measuring the distance from the drone to the ground.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class LidarSensor extends Sensor
{
    private double distanceToGround;

    /**
     * @param distanceToGround The distance to the ground measured by the Lidar sensor.
     * @pre The distance to the ground must be a valid value.
     * @post A LidarSensor object is created with the distanceToGround.
     */
    public LidarSensor(double distanceToGround)
    {
        this.distanceToGround = distanceToGround;
    }

    public double getDistanceToGround()
    {
        return distanceToGround;
    }

    public void setDistanceToGround(double distanceToGround)
    {
        this.distanceToGround = distanceToGround;
    }
}
