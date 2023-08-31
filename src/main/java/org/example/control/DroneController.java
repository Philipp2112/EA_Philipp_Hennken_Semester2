package org.example.control;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.example.model.Battery;
import org.example.model.Drone;
import org.example.model.Position;

/**This class controls the drone and its attributes. The drone instance is created and managed here.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class DroneController
{
    private static DroneController classInstance;


    /** Returns the singleton instance of the DroneController class.
     *
     * @return DroneController singleton instance that is not null.
     * @pre none.
     * @post The singleton instance of the DroneController class is returned.
     */
    public static DroneController getClassInstance()
    {
        if (classInstance == null)
        {
            classInstance = new DroneController();
        }
        return classInstance;
    }


    private final Drone drone;
    private final DoubleProperty xVelocityProperty;
    private DoubleProperty yVelocityProperty;
    private DoubleProperty zVelocityProperty;
    private final DoubleProperty xKoordinateProperty;
    private final DoubleProperty yKoordinateProperty;
    private final DoubleProperty zKoordinateProperty;
    private final DoubleProperty chargeLevelProperty;
    private final DoubleProperty distanceToGroundProperty;

    /** This method sets every property to a new SimpleDoubleProperty.
     * It calls the propertyListeners method.
     *
     * @pre none.
     * @post Every Property is set to a new SimpleDoubleProperty.
     */
    private DroneController()
    {
        this.drone = new Drone(new Position(0.0,0.0,0.0));
        this.xVelocityProperty = new SimpleDoubleProperty();
        this.yVelocityProperty = new SimpleDoubleProperty();
        this.zVelocityProperty = new SimpleDoubleProperty();
        this.xKoordinateProperty = new SimpleDoubleProperty();
        this.yKoordinateProperty = new SimpleDoubleProperty();
        this.zKoordinateProperty = new SimpleDoubleProperty();
        this.chargeLevelProperty = new SimpleDoubleProperty();
        this.distanceToGroundProperty = new SimpleDoubleProperty();
        propertyListeners();
    }

    /** For every property a listener monitors if the property changed. Then the drone singleton wil be updated.
     *
     * @pre The necessary properties and drone instance must be properly initialized.
     * @post Property listeners are set up.
     */
    private void propertyListeners()
    {
        this.xVelocityProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteGeschwindigkeit, Number updatedXVelocity)
            {
                drone.getVelocity().setxVelocity((Double) updatedXVelocity);
            }
        });

        this.yVelocityProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteGeschwindigkeit, Number updatedYVelocity)
            {
                drone.getVelocity().setyVelocity((Double) updatedYVelocity);
            }
        });

        this.zVelocityProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteGeschwindigkeit, Number updatedZVelocity)
            {
                drone.getVelocity().setzVelocity((Double) updatedZVelocity);
            }
        });

        this.distanceToGroundProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldDistanceToGround, Number updatedDistanceToGround)
            {
                drone.getLidarSensor().setDistanceToGround((Double) updatedDistanceToGround);
            }
        });

        this.chargeLevelProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldChargeLevel, Number updatedChargeLevel)
            {
                drone.setBattery(new Battery((Double) updatedChargeLevel));
            }
        });


        this.xKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteXKoordinate, Number neueXKoordinate)
            {
                drone.getPosition().setX((Double) neueXKoordinate);
            }
        });

        this.yKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteYKoordinate, Number neueYKoordinate)
            {
                drone.getPosition().setY((Double) neueYKoordinate);
            }
        });

        this.zKoordinateProperty.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number alteZKoordinate, Number neueZKoordinate)
            {
                drone.getPosition().setZ((Double) neueZKoordinate);
            }
        });
    }

    public synchronized DoubleProperty getxVelocityProperty()
    {
        return xVelocityProperty;
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

    public DoubleProperty getChargeLevelProperty()
    {
        return chargeLevelProperty;
    }

    public DoubleProperty getDistanceToGroundProperty()
    {
        return distanceToGroundProperty;
    }

    public DoubleProperty getyVelocityProperty()
    {
        return yVelocityProperty;
    }

    public DoubleProperty yVelocityPropertyProperty()
    {
        return yVelocityProperty;
    }

    public DoubleProperty getzVelocityProperty()
    {
        return zVelocityProperty;
    }

    public DoubleProperty zVelocityPropertyProperty()
    {
        return zVelocityProperty;
    }

    public Drone getDrone()
    {
        return drone;
    }
}
