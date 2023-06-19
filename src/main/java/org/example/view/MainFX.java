package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.client.Constants;
import org.example.client.Strings;
import org.example.communication.DroneControllerMain;

import java.io.File;
import java.io.IOException;

public class MainFX extends Application
{



    public static void main(String[] args)
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
        String absolutePath = Strings.PATH_TO_MAIN_SCREEN_FXML;
        fxmlLoaderMainScreen.setLocation(new File(absolutePath).toURI().toURL());
        Scene scene = null;

        try
        {
            scene = new Scene(fxmlLoaderMainScreen.load(), Constants.WINDOW_WIDTH, Constants.WINDOW_HIGHT);
        } catch (IOException ioException)
        {
            throw new RuntimeException(ioException);
        }

        stage.setTitle(Strings.TITLE_DRONE_SOFTWARE);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
