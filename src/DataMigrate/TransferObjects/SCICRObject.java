package DataMigrate.TransferObjects;

/**
 * This class will contain all of the data regarding one SC/ICR
 * entry(row) to be inserted into the database.
 *
 * Created by Anthony Orio on 5/3/2017.
 */
public class SCICRObject {

    private String origLine;
    private String type;
    private String number;
    private String title;
    private String build;
    private String baseline;

    /**
     * Default constructor.
     */
    public SCICRObject() {}

    /**
     * Constructor to set all the parameters.
     * @param type The type.
     * @param number The number.
     * @param title The title.
     * @param build The build.
     * @param baseline The baseline.
     */
    public SCICRObject(String type, String number, String title, String build, String baseline)
    {
        this.type = type;
        this.number = number;
        this.title = title;
        this.build = build;
        this.baseline = baseline;
    }

    /**
     * Get the type.
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the number.
     * @return The number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gets the title.
     * @return The title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the build.
     * @return The build.
     */
    public String getBuild() {
        return build;
    }

    /**
     * Gets the baseline.
     * @return The baseline.
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * Gets the CSV line.
     * @return The CSV line.
     */
    public String getOrigLine() {
        return origLine;
    }

    /**
     * Sets the CSV line.
     * @param origLine The CSV line.
     */
    public void setOrigLine(String origLine) {
        this.origLine = origLine;
    }
}
