package DataMigrate.TransferObjects;

/**
 * This class will contain all of the data regarding one ROM
 * entry(row) to be inserted into the database.
 *
 * Created by Anthony Orio on 5/2/2017.
 */
public class EstimationObject
{
    private String baseline;
    private String slocsDay;
    private String slocsMonth;
    private String cprs;
    private String defaultSlocs;
    private String cpddDoc;
    private String cpddDate;
    private String budUpgrade;
    private String budMaint;
    private String ddrcwtSlocs;

    private double design;
    private double code;
    private double integ;
    private double unitTest;




    public EstimationObject(String baseline,
                            String slocsDay,
                            String slocsMonth,
                            String cprs,
                            String defaultSlocs,
                            String cpddDoc,
                            String cpddDate,
                            String budUpgrade,
                            String budMaint,
                            String ddrcwtSlocs,
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

    public String getSlocsDay() {
        return slocsDay;
    }

    public String getSlocsMonth() {
        return slocsMonth;
    }

    public String getCprs() {
        return cprs;
    }

    public String getDefaultSlocs() {
        return defaultSlocs;
    }

    public String getCpddDate() {
        return cpddDate;
    }

    public String getCpddDoc() {
        return cpddDoc;
    }

    public String getBudUpgrade() {
        return budUpgrade;
    }

    public String getBudMaint() {
        return budMaint;
    }

    public String getDdrcwtSlocs() {
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
}


