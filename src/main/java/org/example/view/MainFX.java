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

/**This class starts every party of the program.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class MainFX extends Application
{


    /** This method is called when the programm is started.
     * calls the runProgram-method.
     * @param args Program arguments.
     * @pre Unity has to be started to be able to connect properly.
     * @post the GUI is started.
     * @post The DroneControllerMain is started.
     * @post The PlanningSoftware thread is started.
     */
    public static void main(String[] args)
    {
        runProgram();

    }

    /**It starts the DroneController and the Thread for the planning software.
     * Additionally, it starts the GUI that is displayed to the user.
     * @pre Unity has to be started to be able to connect properly.
     * @post the GUI is started.
     * @post The DroneControllerMain is started.
     * @post The PlanningSoftware thread is started.
     */
    private static void runProgram()
    {
        DroneControllerMain.main(null);
        PlanningSoftwareClient planningSoftwareClient = new PlanningSoftwareClient();
        Thread planningThread = new Thread(planningSoftwareClient);
        planningThread.start();
        launch();
    }

    /**
     * Starts the application by loading the main screen FXML file, creating a scene, and displaying it on the stage.
     * @param stage The primary stage of the JavaFX application.
     * @throws Exception If an exception occurs during the start of the application.
     * @pre The application is launched.
     * @post The main screen of the application is displayed on the stage.
     */
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
