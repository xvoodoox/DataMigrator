package DataMigrate.TransferObjects;

/**
 * This class will contain all of the data regarding one ROM
 * entry(row) to be inserted into the database.
 *
 * Created by Anthony Orio on 5/2/2017.
 */
public class EstimationObject
{
    private String origLine;
    private String baseline;
    private double slocsDay;
    private double slocsMonth;
    private String cprs;
    private double defaultSlocs;
    private String cpddDoc;
    private String cpddDate;
    private double budUpgrade;
    private double budMaint;
    private double ddrcwtSlocs;

    private double design;
    private double code;
    private double integ;
    private double unitTest;

    /**
     * Default constructor.
     */
    public EstimationObject() {}

    /**
     * The constructor with all the parameters.
     * @param baseline The baseline.
     * @param slocsDay The slocs per day.
     * @param slocsMonth The slocs per month.
     * @param cprs The cprs number.
     * @param defaultSlocs The default slocs.
     * @param cpddDoc The cpdd document.
     * @param cpddDate The cpdd date.
     * @param budUpgrade The budget upgrade.
     * @param budMaint The budget maintenance.
     * @param ddrcwtSlocs The DDR/CWT Slocs.
     * @param design The design weight.
     * @param code The code weight.
     * @param integ The integration weight.
     * @param unitTest The unit test weight.
     */
    public EstimationObject(String baseline,
                            double slocsDay,
                            double slocsMonth,
                            String cprs,
                            double defaultSlocs,
                            String cpddDoc,
                            String cpddDate,
                            double budUpgrade,
                            double budMaint,
                            double ddrcwtSlocs,
                            double design,
                            double code,
                            double integ,
                            double unitTest
                            )
    {
        this.baseline       = baseline;
        this.slocsDay       = slocsDay;
        this.slocsMonth     = slocsMonth;
        this.cprs           = cprs;
        this.defaultSlocs   = defaultSlocs;
        this.cpddDoc        = cpddDoc;
        this.cpddDate       = cpddDate;
        this.budUpgrade     = budUpgrade;
        this.budMaint       = budMaint;
        this.ddrcwtSlocs    = ddrcwtSlocs;
        this.design         = design;
        this.code           = code;
        this.integ          = integ;
        this.unitTest       = unitTest;
    }

    /**
     * Gets the baseline.
     * @return The baseline.
     */
    public String getBaseline() {
        return baseline;
    }

    /**
     * Gets slocs per day.
     * @return The per day value.
     */
    public double getSlocsDay() {
        return slocsDay;
    }

    /**
     * Gets slocs per month.
     * @return The per month value.
     */
    public double getSlocsMonth() {
        return slocsMonth;
    }

    /**
     * Gets the cprs number.
     * @return The cprs number.
     */
    public String getCprs() {
        return cprs;
    }

    /**
     * Gets the default slocs.
     * @return The default slocs.
     */
    public double getDefaultSlocs() {
        return defaultSlocs;
    }

    /**
     * Gets the cpdd date.
     * @return The cpdd date.
     */
    public String getCpddDate() {
        return cpddDate;
    }

    /**
     * Gets the cpdd document.
     * @return The cpdd document.
     */
    public String getCpddDoc() {
        return cpddDoc;
    }

    /**
     * Gets the budget upgrade.
     * @return The budget upgrade.
     */
    public double getBudUpgrade() {
        return budUpgrade;
    }

    /**
     * Gets the budget maintenance.
     * @return The budge maintenance.
     */
    public double getBudMaint() {
        return budMaint;
    }

    /**
     * Gets the DDR/CWT SLOCS
     * @return The DDR/CWT Slocs.
     */
    public double getDdrcwtSlocs() {
        return ddrcwtSlocs;
    }

    /**
     * Gets the design weight.
     * @return The design weight.
     */
    public double getDesign() {
        return design;
    }

    /**
     * Gets the code weight.
     * @return The code weight.
     */
    public double getCode() {
        return code;
    }

    /**
     * Gets the integration weight.
     * @return The integration weight.
     */
    public double getInteg() {
        return integ;
    }

    /**
     * Gets the unit test weight.
     * @return The unit test weight.
     */
    public double getUnitTest() {
        return unitTest;
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


