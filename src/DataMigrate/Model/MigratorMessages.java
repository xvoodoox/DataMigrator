package DataMigrate.Model;

/**
 * This class will contain all of the messages relating to the
 * data migrator's text areas. Most of the messages here will
 * contains steps on how to complete each page of the program.
 *
 * Created by Anthony Orio on 5/2/2017.
 */
public class MigratorMessages
{

    /**
     * Gets the welcoming message for the user.
     * @return The welcome message.
     */
    public static String getWelcomeMessage()
    {
        String message = "Welcome to the Data Migration tool.";
        return message;
    }

    /**
     * Gets the purpose of this program.
     * @return The purpose of this program.
     */
    public static String getBeginMessage()
    {
        String message =
                "This tool is used to transfer data from CSV files to a database created by the developers.\n" +
                "It is vital that these instructions are followed thoroughly to ensure migration is achieved.\n\n";

        return message;
    }

    /**
     * Gets the steps needed before continuing with the data migrator.
     * @return The steps needed before proceeding to migration.
     */
    public static String getStepsMessage()
    {
        String message =
                "Perform steps 1 - 3 for the following database tables: \n" +
                        "\t\t1) basicrom\n" +
                        "\t\t2) scdata\n" +
                        "\t\t3) REQTRACE\n\n" +
                "STEP 1 - Begin by creating CSV files that contain the .txt extension. When the Export Text Wizard displays \n" +
                "\t\tensure that the Delimited radio button is selected and then hit next. \n\n" +
                "STEP 2 - Choose the delimiter that separates the fields to be a comma and choose {none} in the Text Qualifier\n\t\t drop down menu then hit next.\n\n" +
                "STEP 3 - Save the file to some known location so that you can locate later.\n\n" +
                "STEP 4 - Use the file chooser below to locate the database the developers have provide and then hit next.";

        return message;
    }

    /**
     * Gets the word BASICROM as a title.
     * @return The word BASICROM.
     */
    public static String getBasicromTitle()
    {
        String message = "BASICROM";
        return message;
    }

    /**
     * Gets the steps needed to complete basicrom transfer.
     * @return The steps to transfer basicrom.
     */
    public static String getBasicromStep()
    {
        String message = "Use the file chooser to locate the basicrom CSV file, " +
                "then use the drop down menus to select the column names that match the labels to the left.";
        return message;
    }

    /**
     * Gets the word SCDATA to use as a title.
     * @return The word SCDATA.
     */
    public static String getScdataTitle()
    {
        String message = "SCDATA";
        return message;
    }

    /**
     * Gets the steps to complete the scdata transfer.
     * @return The steps to complete scdata transfer.
     */
    public static String getScdataStep()
    {
        String message = "Use the file chooser to locate the scdata CSV file, " +
                "then use the drop down menus to select the column names that match the labels to the left.";
        return message;
    }

    /**
     * Gets the word REQTRACE to use as a title.
     * @return The word REQTRACE.
     */
    public static String getREQTRACETitle()
    {
        String message = "REQTRACE";
        return message;
    }

    /**
     * Gets the steps needed to migrate REQTRACE.
     * @return The steps to migrate REQTRACE.
     */
    public static String getREQTRACEStep()
    {
        String message = "Use the file chooser to locate the REQTRACE CSV file, " +
                "then use the drop down menus to select the column names that match the labels to the left.";
        return message;
    }

    /**
     * Gets the message saying that the user is finished.
     * @return The message sayin that the user is finished.
     */
    public static String getFinalMessage()
    {
        String message = "Data migration has been completed. Click cancel to finish.";
        return message;
    }

}
