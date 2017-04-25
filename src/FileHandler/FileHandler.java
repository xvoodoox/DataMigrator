package FileHandler;

import javafx.stage.FileChooser;
import java.io.File;

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
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Database Files", "*.mdb"));

        // Return the file and then extract its path.
        File selectedFile = fileChooser.showOpenDialog(null);

        return (selectedFile == null) ? null : selectedFile;
    }

}
