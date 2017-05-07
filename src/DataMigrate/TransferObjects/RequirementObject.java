package DataMigrate.TransferObjects;

/**
 * Created by Anthony Orio on 5/3/2017.
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

    public RequirementObject() {}
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

    public String getCsc() {
        return csc;
    }

    public String getCsu() {
        return csu;
    }

    public String getDoors() {
        return doors;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getBaseline() {
        return baseline;
    }

    public String getBuild() {
        return build;
    }

    public String getScicr() {
        return scicr;
    }

    public String getCapability() {
        return capability;
    }

    public String getAdd() {
        return add;
    }

    public String getChange() {
        return change;
    }

    public String getDelete() {
        return delete;
    }

    public String getUnitTest() {
        return unitTest;
    }

    public String getDesign() {
        return design;
    }

    public String getCode() {
        return code;
    }

    public String getIntegration() {
        return integration;
    }

    public String getRi() {
        return ri;
    }

    public String getRommer() {
        return rommer;
    }

    public String getProgram() {
        return program;
    }

    public String getOrigLine() {
        return origLine;
    }

    public void setOrigLine(String origLine) {
        this.origLine = origLine;
    }

}
