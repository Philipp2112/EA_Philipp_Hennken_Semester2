package org.example.model;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.client.MeineKonstanten;

public class DroneController
{
    private static DroneController classInstance;

    public static DroneController getClassInstance()
    {
        if (classInstance == null)
        {
            classInstance = new DroneController();
        }
        return classInstance;
    }


    private Drone drone;
    private DoubleProperty geschwindigkeitsProperty;
    private DoubleProperty xKoordinateProperty;
    private DoubleProperty yKoordinateProperty;
    private DoubleProperty zKoordinateProperty;

    private DroneController()
    {
        this.drone = new Drone(new Position(0,0,0));
        this.geschwindigkeitsProperty = new SimpleDoubleProperty();
        this.xKoordinateProperty = new SimpleDoubleProperty();
        this.yKoordinateProperty = new SimpleDoubleProperty();
        this.zKoordinateProperty = new SimpleDoubleProperty();
        propertyListeners();
    }

    private void propertyListeners()
    {
        this.geschwindigkeitsProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteGeschwindigkeit, Number neueGeschwindigkeit)
            {
                drone.setVelocity(new Velocity((Double) neueGeschwindigkeit));
                System.out.println("Geschwindigkeit hat sich geändert!!!");
            }
        });

        this.xKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteXKoordinate, Number neueXKoordinate)
            {
                drone.getPosition().setX((Double) neueXKoordinate);
                System.out.println("change X Property");
            }
        });

        this.yKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteYKoordinate, Number neueYKoordinate)
            {
                drone.getPosition().setY((Double) neueYKoordinate);
                System.out.println("changed y");
            }
        });

        this.zKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteZKoordinate, Number neueZKoordinate)
            {
                drone.getPosition().setZ((Double) neueZKoordinate);
                System.out.println("change z");
            }
        });
    }

    public synchronized static double calculateSpeed(double currentX, double currentY, double currentZ, double prevX, double prevY, double prevZ) {
        double deltaX = currentX - prevX;
        double deltaY = currentY - prevY;
        double deltaZ = currentZ - prevZ;

        return Math.round(Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ)/ MeineKonstanten.milliSecondToSecond);
    }



    public synchronized DoubleProperty getGeschwindigkeitsProperty()
    {
        return geschwindigkeitsProperty;
    }


    public DoubleProperty getXKoordinateProperty()
    {
        return xKoordinateProperty;
    }


    public DoubleProperty getYKoordinateProperty()
    {
        return yKoordinateProperty;
    }


    public DoubleProperty getZKoordinateProperty()
    {
        return zKoordinateProperty;
    }

    public Drone getDrone()
    {
        return drone;
    }
}
