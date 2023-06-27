package org.example.model;

import com.google.gson.annotations.SerializedName;

/**This class keeps a velocity with its attributes.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Velocity
{
    //private final double velocity;
    @SerializedName("x")
    private double xVelocity;
    @SerializedName("y")
    private double yVelocity;
    @SerializedName("z")
    private double zVelocity;

    ///TODO kommentar
    public Velocity(double xVelocity, double yVelocity, double zVelocity)
    {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    /*public double getVelocity()
    {
        return velocity;
    }*/

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
