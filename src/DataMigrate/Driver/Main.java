package DataMigrate.Driver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;


/**
 * The main entry way to the program.
 *
 * Created by Anthony Orio on 4/24/2017.
 */
public class Main extends Application
{


    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Starts the FXML GUI created in scene builder.
     * @param primaryStage : the current GUI stage.
     * @throws Exception If scene cannot start.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DataMigrate/View/DataMigrateView.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Data Migrator");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }
}
