package org.example.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Planungssoftware extends Application
{



    public static void main(String[] args) throws IOException
    {
        PlanungsSoftwareClient planungsSoftwareClient = new PlanungsSoftwareClient();
        Thread planungsThread = new Thread(planungsSoftwareClient);
        planungsThread.start();
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        String absolutePath = "C:\\Users\\philipp.hennken\\eclipse-workspace\\EA_Philipp_Hennken_Semester2\\src\\main\\resources\\org.example\\mainScreen.fxml";
        fxmlLoader.setLocation(new File(absolutePath).toURI().toURL());
        Scene scene = null;


        try
        {
            scene = new Scene(fxmlLoader.load(), 800, 800);
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
