package org.example.view;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import org.example.res.Constants;
import org.example.res.Strings;
import org.example.control.PlannedFlight;
import org.example.control.DroneController;
import org.example.model.Position;

/**This class sets everything up that is necessary for displaying data for the planned flight.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class Planning
{

    public TextField xCoordinateEntry;
    public TextField yCoordinateEntry;
    public TextField zCoordinateEntry;
    public TableView<Position> tableWithCoordinates;
    public Label infoLabel;
    public TableColumn<Position, Double> xCoordinateTableColumn;
    public TableColumn<Position, Double> yCoordinateTableColumn;
    public TableColumn<Position, Double> zCoordinateTableColumn;
    public ProgressBar chargeLevelProgressBar;
    public Label chargeLevelLabel;
    private static ObservableList<Position> positions = FXCollections.observableArrayList();
    public Slider chargingSlider;
    public Label anzeigeXVelocity;
    public Label anzeigeX;
    public Label anzeigeY;
    public Label anzeigeZ;
    public Label abstandZumBoden;
    public Label anzeigeYVelocity;
    public Label anzeigeZVelocity;

    /**This Method is called when the FXML-File "planning.fxml" is loaded. It sets the ValueFactories for the table columns
     * and binds the Charge-Level-ProgressBar to it´s Property as well as the ChargeLevel-Label.
     * @pre
     * @post
     */
    @FXML
    public void initialize()
    {
       xCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>(Strings.LITTLE_X));
       yCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>(Strings.LITTLE_Y));
       zCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>(Strings.LITTLE_Z));
       setBindings(anzeigeX, anzeigeY, anzeigeZ, anzeigeXVelocity, abstandZumBoden, chargeLevelProgressBar, chargeLevelLabel, anzeigeYVelocity, anzeigeZVelocity);
    }

    /**
     * Sets up bidirectional bindings between the provided labels, progress bar, and the properties in the DroneController.
     * @param anzeigeX The label displaying the X coordinate.
     * @param anzeigeY The label displaying the Y coordinate.
     * @param anzeigeZ The label displaying the Z coordinate.
     * @param anzeigeXVelocity The label displaying the velocity.
     * @param abstandZumBoden The label displaying the distance to the ground.
     * @param chargeLevelProgressBar The progress bar displaying the battery charge level.
     * @param chargeLevelLabel The label displaying the battery charge level.
     * @pre The setBindings method is called with the required labels and progress bar.
     * @post Bidirectional bindings are made between the labels, progress bar, and the properties in DroneController.
     */
    public static void setBindings(Label anzeigeX, Label anzeigeY, Label anzeigeZ, Label anzeigeXVelocity, Label abstandZumBoden, ProgressBar chargeLevelProgressBar, Label chargeLevelLabel, Label anzeigeYVelocity, Label anzeigeZVelocity)
    {
        Bindings.bindBidirectional(anzeigeX.textProperty(), DroneController.getClassInstance().getXKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeY.textProperty(), DroneController.getClassInstance().getYKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeZ.textProperty(), DroneController.getClassInstance().getZKoordinateProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeXVelocity.textProperty(), DroneController.getClassInstance().getxVelocityProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(abstandZumBoden.textProperty(), DroneController.getClassInstance().getDistanceToGroundProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(chargeLevelProgressBar.progressProperty(), DroneController.getClassInstance().getChargeLevelProperty());
        Bindings.bindBidirectional(chargeLevelLabel.textProperty(), DroneController.getClassInstance().getChargeLevelProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeYVelocity.textProperty(), DroneController.getClassInstance().getyVelocityProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(anzeigeZVelocity.textProperty(), DroneController.getClassInstance().getzVelocityProperty(), new NumberStringConverter());
    }

    /**Handles the event when the "Add Coordinates" button is clicked.
     * Updates the table view and info label.
     * @pre The "Add Coordinates" button is clicked.
     * @post The entered coordinates are added to the list of positions if they are within the valid range.
     */
    public void onAddCoordinatesClick()
    {
        try
        {
            if (Double.parseDouble(zCoordinateEntry.getText()) >= Constants.MIN_Z_COODINATE &&
                    Double.parseDouble(zCoordinateEntry.getText()) <= Constants.MAX_Z_COODINATE &&
                    Double.parseDouble(xCoordinateEntry.getText()) >= Constants.MIN_X_COODINATE &&
                    Double.parseDouble(xCoordinateEntry.getText()) <= Constants.MAX_X_COODINATE &&
                    Double.parseDouble(yCoordinateEntry.getText()) >= Constants.MIN_Y_COODINATE)
            {
                positions.add(new Position(
                        Double.parseDouble(xCoordinateEntry.getText()),Double.parseDouble(yCoordinateEntry.getText()),Double.parseDouble(zCoordinateEntry.getText())));
                tableWithCoordinates.setItems(positions);
                infoLabel.setText(Strings.POSITION_ADDED + coordinateToString(positions.get(positions.size()-1)));;
            }
            else
            {
                infoLabel.setText(Strings.OUT_OF_BORDER);
            }
        }catch (NumberFormatException numberFormatException)
        {
            infoLabel.setText(Strings.WRONG_INPUT_COORDINATES);
        }
    }

    /**Handles the event when the "Fly to Coordinates" button is clicked.
     * Creates a new instance of PlannedFlight and starts it in a separate thread.
     * @pre The "Fly to Coordinates" button is clicked.
     * @post A new instance of PlannedFlight is created and started in a separate thread.
     */
    public void onFlyToCoordinatesClick()
    {
        PlannedFlight plannedFlight = new PlannedFlight();
        Thread plannedFlightThread = new Thread(plannedFlight);
        plannedFlightThread.start();
    }

    /**Converts a Position object to a string representation.
     * @param position The Position object to convert.
     * @return The string representation of the Position object.
     */
    private String coordinateToString(Position position)
    {
        return Strings.X_COLON + position.getX() + Strings.Y_COLON + position.getY() + Strings.Z_COLON + position.getZ();
    }

    /**Deletes the last coordinate from the list of positions.
     * @pre The list of positions is not empty.
     * @post The last coordinate is removed from the list of positions.
     */
    public void deleteLastCoordinate()
    {
        try
        {
            infoLabel.setText(Strings.POSITION_REMOVED + coordinateToString(positions.get(positions.size()-1)));
            positions.remove(positions.get(positions.size()-1));
        }catch (IndexOutOfBoundsException indexOutOfBoundsException)
        {
            infoLabel.setText(Strings.NO_COORDINATES_TO_REMOVE);
        }
    }

    /** Charges the drone's battery based on the value of the charging slider.
     * @pre The chargingSlider value is within the valid range.
     * @post The drone's battery charge level is set based on the value of the chargingSlider.
     */
    public void chargeDrone()
    {
        DroneController.getClassInstance().getDrone().getBattery().setChargeLevel(chargingSlider.getValue() / Constants.NUMBER_TO_PERCENT);
    }

    public static ObservableList<Position> getPositions()
    {
        return positions;
    }
}
