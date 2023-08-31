package org.example.model;

import com.google.gson.annotations.SerializedName;

/**This class keeps a velocity with its attributes.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Velocity
{
    @SerializedName("x")
    private double xVelocity;
    @SerializedName("y")
    private double yVelocity;
    @SerializedName("z")
    private double zVelocity;

    /**Constructs a new Velocity object with the specified velocities.
     *
     * @param xVelocity the x-component of the velocity
     * @param yVelocity the y-component of the velocity
     * @param zVelocity the z-component of the velocity
     * @pre xVelocity, yVelocity, and zVelocity must be valid double values.
     * @post The Velocity object is initialized with the provided velocities.
     */
    public Velocity(double xVelocity, double yVelocity, double zVelocity)
    {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    public double getxVelocity()
    {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity)
    {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity()
    {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity)
    {
        this.yVelocity = yVelocity;
    }

    public double getzVelocity()
    {
        return zVelocity;
    }

    public void setzVelocity(double zVelocity)
    {
        this.zVelocity = zVelocity;
    }

    @Override
    public String toString()
    {
        return "Velocity{" +
                "xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                ", zVelocity=" + zVelocity +
                '}';
    }
}
