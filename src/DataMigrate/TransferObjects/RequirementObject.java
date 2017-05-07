package DataMigrate.TransferObjects;

/**
 * This class will contain all of the data regarding one requirement
 * entry(row) to be inserted into the database.
 *
 * Created by Anthony Orio on 5/2/2017.
 */
public class RequirementObject {


    private String origLine;
    private String csc;
    private String csu;
    private String doors;
    private String paragraph;
    private String baseline;
    private String build;
    private String scicr;
    private String capability;
    private String add;
    private String change;
    private String delete;
    private String unitTest;
    private String design;
    private String code;
    private String integration;
    private String ri;
    private String rommer;
    private String program;

    /** Default constructor
     */
    public RequirementObject() {}

    /**
     * Constructor with all the parameters to pass.
     * @param csc The CSC.
     * @param csu The CSU.
     * @param doors The doors id.
     * @param paragraph The paragraph.
     * @param baseline The baseline.
     * @param build The build.
     * @param scicr The SC/ICR.
     * @param capability The capability.
     * @param add The add.
     * @param change The change.
     * @param delete The delete.
     * @param unitTest The unit test.
     * @param design The design.
     * @param code The code.
     * @param integration The integration.
     * @param ri The responsible individual.
     * @param rommer The rommer.
     * @param program The program.
     */
    public RequirementObject(String csc,
                             String csu,
                             String doors,
                             String paragraph,
                             String baseline,
                             String build,
                             String scicr,
                             String capability,
                             String add,
                             String change,
                             String delete,
                             String unitTest,
                             String design,
                             String code,
                             String integration,
                             String ri,
                             String rommer,
                             String program)
    {
        this.csc = csc;
        this.csu = csu;
        this.doors = doors;
        this.paragraph = paragraph;
        this.baseline = baseline;
        this.build = build;
        this.scicr = scicr;
        this.capability = capability;
        this.add = add;
        this.change = change;
        this.delete = delete;
        this.unitTest = unitTest;
        this.design = design;
        this.code = code;
        this.integration = integration;
        this.ri = ri;
        this.rommer = rommer;
        this.program = program;
    }

    /**
     * Gets the CSC.
     * @return The CSC.
     */
    public String getCsc() {
        return csc;
    }

    /**
     * Gets the CSU.
     * @return The CSU.
     */
    public String getCsu() {
        return csu;
    }

    /**
     * Gets the doors id.
     * @return The doors ID.
     */
    public String getDoors() {
        return doors;
    }

    /**
     * Gets the paragraph.
     * @return The paragraph.
     */
    public String getParagraph() {
        return paragraph;
    }

    /**
     * Gets the baseline.
     * @return The baseline.
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * Gets the build.
     * @return The build
     */
    public String getBuild() {
        return build;
    }

    /**
     * Gets the SC or ICR.
     * @return The SC or ICR.
     */
    public String getScicr() {
        return scicr;
    }

    /**
     * Gets the capability.
     * @return The capability.
     */
    public String getCapability() {
        return capability;
    }

    /**
     * Gets the add.
     * @return The add.
     */
    public String getAdd() {
        return add;
    }

    /**
     * Gets the change.
     * @return The change.
     */
    public String getChange() {
        return change;
    }

    /**
     * Gets the delete.
     * @return The delete.
     */
    public String getDelete() {
        return delete;
    }

    /**
     * Gets the unit test completion.
     * @return The unit test completion.
     */
    public String getUnitTest() {
        return unitTest;
    }

    /**
     * Gets the design completion.
     * @return The design completion.
     */
    public String getDesign() {
        return design;
    }

    /**
     * Gets the code completion.
     * @return The code completion.
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the integration completion.
     * @return The integration completion.
     */
    public String getIntegration() {
        return integration;
    }

    /**
     * Gets the responsible individual.
     * @return The responsible individual.
     */
    public String getRi() {
        return ri;
    }

    /**
     * Gets the rommer.
     * @return The rommer.
     */
    public String getRommer() {
        return rommer;
    }

    /**
     * Gets the program.
     * @return The program.
     */
    public String getProgram() {
        return program;
    }

    /**
     * Gets the CSV line.
     * @return The CSV line.
     */
    public String getOrigLine() {
        return origLine;
    }

    /**
     * Sets the current CSV line.
     * @param origLine The CSV line.
     */
    public void setOrigLine(String origLine) {
        this.origLine = origLine;
    }

}
