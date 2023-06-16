package org.example.view;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;
import org.example.client.Strings;
import org.example.model.DroneController;
import org.example.model.Position;

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
    public TableColumn<Position, Double> dtgCoordinateTableColumn;
    public ProgressBar chargeLevelProgressBar;
    public Label chargeLevelLabel;
    ObservableList<Position> positions = FXCollections.observableArrayList();

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
       Bindings.bindBidirectional(chargeLevelProgressBar.progressProperty(), DroneController.getClassInstance().getChargeLevelProperty());
       Bindings.bindBidirectional(chargeLevelLabel.textProperty(), DroneController.getClassInstance().getChargeLevelProperty(), new NumberStringConverter());
    }

    public void flyRoute() throws InterruptedException
    {
        for (Position position : positions)
        {
            double destinyX = position.getX();
            double destinyY = position.getY();
            double destinyZ = position.getZ();

            flyToDestiny(destinyX, destinyY, destinyZ);
        }
    }

    private void flyToDestiny(double destinyX, double destinyY, double destinyZ)
    {
            Platform.runLater(() ->
            {

            });

    }

    private void sendCommandForDuration(String command, int durationInMilliseconds) throws InterruptedException
    {
        Platform.runLater(() ->
        {
            for (int i = 0; i < durationInMilliseconds; i++)
            {
                FreeFlightDelegate.setCommandToController(command);
                try
                {
                    wait(1);
                } catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });


    }
    public void onAddCoordinatesClick()
    {
        try
        {
            positions.add(new Position(Double.parseDouble(xCoordinateEntry.getText()),Double.parseDouble(yCoordinateEntry.getText()),Double.parseDouble(zCoordinateEntry.getText())));
            tableWithCoordinates.setItems(positions);
            infoLabel.setText(Strings.POSITION_ADDED + coordinateToString(positions.get(positions.size()-1)));;
        }catch (NumberFormatException numberFormatException)
        {
            infoLabel.setText(Strings.WRONG_INPUT_COORDINATES);
        }
    }

    public void onFlyToCoordinatesClick() throws InterruptedException
    {
        flyRoute();
        System.out.println(positions);
    }

    private String coordinateToString(Position position)
    {
        return Strings.X_COLON + position.getX() + Strings.Y_COLON + position.getY() + Strings.Z_COLON + position.getZ();
    }

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
}
