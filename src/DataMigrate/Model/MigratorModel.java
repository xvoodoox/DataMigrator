package DataMigrate.Model;

import DataMigrate.FileHandler.FileHandler;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class perform all of the operations associated with transferring
 * data from one database file to another database file via using .csv files.
 *
 * Created by Anthony Orio on 4/24/2017.
 */
public class MigratorModel
{
    public static Connection conn = null;
    public static String dbPath = "jdbc:ucanaccess://";

    private static FileHandler fileHandler = new FileHandler();

    public static File databaseFile;
    public static File estimationCSV;
    public static File scicrCSV;
    public static File requirementsCSV;

    public static String[] estimationHeaders = {};
    public static String[] scicrHeaders = {};
    public static String[] requirementsHeaders = {};

    public static boolean findDatabaseFile()
    {
        databaseFile = fileHandler.useFileChooser("*.mdb");
        dbPath = dbPath + databaseFile.getPath();

        if (attemptConnection()) {
            return true;
        } else {
            return false;
        }
    }

    public static void findEstimationCSV()
    {
        estimationCSV = fileHandler.useFileChooser();
        estimationHeaders = readColumnNames(estimationCSV);

    }

    public static void findSCICRCSV()
    {
        scicrCSV = fileHandler.useFileChooser();
        scicrHeaders = readColumnNames(scicrCSV);
    }

    public static void findRequirementsCSV()
    {
        requirementsCSV = fileHandler.useFileChooser();
        requirementsHeaders = readColumnNames(requirementsCSV);
    }

    private static boolean attemptConnection()
    {
        try {
            conn = DriverManager.getConnection(dbPath);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }



    private static String[] readColumnNames(File file)
    {
        String csvFile = file.getPath();
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String[] headers = {};

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            headers = line.split(cvsSplitBy);
            System.out.println(headers[0]);
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] country = line.split(cvsSplitBy);
//
//                //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return headers;
    }
}
