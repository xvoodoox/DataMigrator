package DataMigrate.FileHandler;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;

/**
 * This class will perform all operations associated with the file
 * system on the users operating system.
 *
 * Created by Anthony Orio on 4/24/2017.
 */
public class FileHandler
{
    public FileHandler() { }

    /**
     * Uses a file chooser so that user can find the file manually.
     * @return the file that was chosen by the user.
     */
    public File useFileChooser()
    {
        // Create a file chooser object to select database.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Database Resource File");

        // Filters out all other file types.
        /*fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Database Files", "*.mdb"));*/

        // Return the file and then extract its path.
        File selectedFile = fileChooser.showOpenDialog(null);

        return (selectedFile == null) ? null : selectedFile;
    }

    /**
     * Uses a file chooser so that user can find the file manually.
     * @param extensionFilter The extension to filter by.
     * @return the file that was chosen by the user.
     *
     */
    public File useFileChooser(String extensionFilter)
    {
        // Create a file chooser object to select database.
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Database Resource File");


        // Return the file and then extract its path.
        File selectedFile = fileChooser.showOpenDialog(null);

        return (selectedFile == null) ? null : selectedFile;
    }

    /**
     * Use a file chooser to retrieve a path so that a file can be saved
     * to that specific location.
     * @return The directory path.
     */
    public String getPathWithFileChooser()
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Choose location to save to. ");
        File selectedDirectory = chooser.showDialog(null);
        String dir = selectedDirectory.getAbsolutePath();

        return dir;
    }

}
