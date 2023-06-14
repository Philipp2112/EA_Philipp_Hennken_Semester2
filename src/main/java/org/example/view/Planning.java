package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    //ArrayList<Position> positions = new ArrayList<>();
    ObservableList<Position> positions = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
       xCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>("x"));
       yCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>("y"));
       zCoordinateTableColumn.setCellValueFactory(new PropertyValueFactory<Position,Double>("z"));


    }




    public void onAddCoordinatesClick()
    {
        try
        {
            positions.add(new Position(Double.parseDouble(xCoordinateEntry.getText()),Double.parseDouble(yCoordinateEntry.getText()),Double.parseDouble(zCoordinateEntry.getText())));
            addPositionToList();
            infoLabel.setText("Position hinzugefügt: \n" + coordinateToString(positions.get(positions.size()-1)));;
        }catch (NumberFormatException numberFormatException)
        {
            infoLabel.setText("Fehlerhafte Eingabe!\nHinweis: Trennzeichen Dezimalpunkt");
        }
    }

    private void addPositionToList()
    {
        tableWithCoordinates.setItems(positions);
    }

    public void onFlyToCoordinatesClick()
    {
        System.out.println(positions);
    }

    private String coordinateToString(Position position)
    {
        return "X: " + position.getX() + " Y: " + position.getY() + " Z: " + position.getZ();
    }

    public void deleteLastCoordinate()
    {
        try
        {
            infoLabel.setText("Position entfernt: \n" + coordinateToString(positions.get(positions.size()-1)));
            positions.remove(positions.get(positions.size()-1));
        }catch (IndexOutOfBoundsException indexOutOfBoundsException)
        {
            infoLabel.setText("Keine löschbare Koordinate \n verfügbar.");
        }
    }
}
