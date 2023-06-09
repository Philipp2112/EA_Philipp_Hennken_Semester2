/*
package org.example;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.example.model.*;

public class Main
{
    public static void main(String[] args)
    {
        testen1();
    }




    private static void testen1()
    {
        Drone drone = new Drone(new Battery(), new Thrust(new Vector3D(12,0,12)),new Orientation(30,30,30),
                new Velocity(new Vector3D(3,3,3)),new Position(new Vector3D(-122,500,50)));
        for (int i = 0; i < 30; i++)
        {
            DroneController.calculateNextCoodinate(drone,1/30d);
            System.out.println(drone.getPosition());
        }
        drone.setVelocity(new Velocity(new Vector3D(200,200,200)));
        System.out.println("\n\n\n\n");
        for (int i = 0; i < 30; i++)
        {
            DroneController.calculateNextCoodinate(drone,1/30d);
            System.out.println(drone.getPosition());
        }
    }

}*/
