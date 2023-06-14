package org.example.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.communication.DroneControllerMain;

import java.io.File;
import java.io.IOException;

public class MainFX extends Application
{



    public static void main(String[] args) throws IOException
    {
        DroneControllerMain.main(null);
        PlanungsSoftwareClient planungsSoftwareClient = new PlanungsSoftwareClient();
        Thread planungsThread = new Thread(planungsSoftwareClient);
        planungsThread.start();
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoaderMainScreen = new FXMLLoader();
        String absolutePath = "C:\\Users\\philipp.hennken\\eclipse-workspace\\EA_Philipp_Hennken_Semester2\\src\\main\\resources\\org.example\\mainScreen.fxml";
        fxmlLoaderMainScreen.setLocation(new File(absolutePath).toURI().toURL());
        Scene scene = null;


        try
        {
            scene = new Scene(fxmlLoaderMainScreen.load(), 800, 800);
        } catch (IOException ioException)
        {
            throw new RuntimeException(ioException);
        }

        stage.setTitle("hallo");
        stage.setScene(scene);
        stage.show();

    }

    public void onSetButtonClick(ActionEvent actionEvent)
    {

    }

}
