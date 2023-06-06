package org.example.model;

public class DroneController
{

    public static void calculateNextCoodinate(Drone drone, double duration)
    {
        Thrust thrust = drone.getThrust();
        Position position = drone.getPosition();
        Velocity velocity = drone.getVelocity();

        drone.setPosition(new Position(position.getPositionVector().add(velocity.getVelocityVector().scalarMultiply(duration)
                .add(thrust.getThrust().scalarMultiply(duration * duration).scalarMultiply(0.5)))));
        drone.setVelocity(new Velocity(velocity.getVelocityVector().add(thrust.getThrust().scalarMultiply(duration))));
    }
}
