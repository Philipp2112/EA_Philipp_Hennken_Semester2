package org.example.view;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import java.io.File;
import java.io.IOException;

/**This class starts every party of the program.
 * @author philipp.hennken
 * @version 18.0.2
 */
public class MainScreenDelegate
{

    public void onAboutClick() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        String absolutePath = "C:\\Users\\philipp.hennken\\eclipse-workspace\\EA_Philipp_Hennken_Semester2\\src\\main\\resources\\org.example\\about.fxml";
        loader.setLocation(new File(absolutePath).toURI().toURL());
        Dialog<Void> dialog = loader.load();
        dialog.show();
    }

    /**Stops the execution of the program.
     * @post The program execution is terminated with an exit status of 0.
     */
    public void stopProgramm()
    {
        System.exit(0);
    }
}
