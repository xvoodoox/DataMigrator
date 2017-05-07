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

    public SCICRObject() {}

    public SCICRObject(String type, String number, String title, String build, String baseline)
    {
        this.type = type;
        this.number = number;
        this.title = title;
        this.build = build;
        this.baseline = baseline;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getBuild() {
        return build;
    }

    public String getBaseline() {
        return baseline;
    }

    public String getOrigLine() {
        return origLine;
    }

    public void setOrigLine(String origLine) {
        this.origLine = origLine;
    }
}
