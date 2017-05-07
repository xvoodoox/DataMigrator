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



    public EstimationObject() {}
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

    public String getBaseline() {
        return baseline;
    }

    public double getSlocsDay() {
        return slocsDay;
    }

    public double getSlocsMonth() {
        return slocsMonth;
    }

    public String getCprs() {
        return cprs;
    }

    public double getDefaultSlocs() {
        return defaultSlocs;
    }

    public String getCpddDate() {
        return cpddDate;
    }

    public String getCpddDoc() {
        return cpddDoc;
    }

    public double getBudUpgrade() {
        return budUpgrade;
    }

    public double getBudMaint() {
        return budMaint;
    }

    public double getDdrcwtSlocs() {
        return ddrcwtSlocs;
    }

    public double getDesign() {
        return design;
    }

    public double getCode() {
        return code;
    }

    public double getInteg() {
        return integ;
    }

    public double getUnitTest() {
        return unitTest;
    }

    public String getOrigLine() {
        return origLine;
    }

    public void setOrigLine(String origLine) {
        this.origLine = origLine;
    }
}


