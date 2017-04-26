package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 * Created by Anthony Orio on 4/25/2017.
 */
public class MigratorController
{
    /** Always visible **/
    @FXML private Button button_back;
    @FXML private Button button_next;
    @FXML private Button button_cancel;

    /** Estimation base section **/
    @FXML private StackPane stackPane_beginEstimation;
    @FXML private StackPane stackPane_estimation;

    @FXML private TextArea textArea_estimation;
    @FXML private TextField field_dbFile;
    @FXML private TextField field_estimationCSV;

    @FXML private Button button_dbFind;
    @FXML private Button button_estimationCSVFind;

    @FXML private ComboBox<?> combo_cprs;
    @FXML private ComboBox<?> combo_day;
    @FXML private ComboBox<?> combo_document;
    @FXML private ComboBox<?> combo_upgrade;
    @FXML private ComboBox<?> combo_ddr;
    @FXML private ComboBox<?> combo_unitTestWeight;
    @FXML private ComboBox<?> combo_integrationWeight;
    @FXML private ComboBox<?> combo_month;
    @FXML private ComboBox<?> combo_default;
    @FXML private ComboBox<?> combo_date;
    @FXML private ComboBox<?> combo_maint;
    @FXML private ComboBox<?> combo_designWeight;
    @FXML private ComboBox<?> combo_codeWeight;
    @FXML private ComboBox<?> combo_baseline;



    /** SC/ICR section **/
    @FXML private StackPane stackPane_beginSCICR;
    @FXML private StackPane stackPane_scicr;

    @FXML private TextArea textArea_scicr;
    @FXML private TextField field_scicrCSV;

    @FXML private Button button_scicrCSVFind;

    @FXML private ComboBox<?> combo_scicrType;
    @FXML private ComboBox<?> combo_scicrNumber;
    @FXML private ComboBox<?> combo_scicrTitle;
    @FXML private ComboBox<?> combo_scicrBuild;
    @FXML private ComboBox<?> combo_scicrBaseline;



    /** Requirements section **/
    @FXML private StackPane stackPane_requirementsBegin;
    @FXML private StackPane stackPane_requirements;

    @FXML private Button button_requirementsFind;

    @FXML private TextArea textArea_requirements;
    @FXML private TextField field_requirementsPath;

    @FXML private ComboBox<?> combo_reqCSC;
    @FXML private ComboBox<?> combo_reqCSU;
    @FXML private ComboBox<?> combo_reqDoors;
    @FXML private ComboBox<?> combo_reqPara;
    @FXML private ComboBox<?> combo_reqBaseline;
    @FXML private ComboBox<?> combo_reqBuild;
    @FXML private ComboBox<?> combo_reqSCICR;
    @FXML private ComboBox<?> combo_reqCapability;
    @FXML private ComboBox<?> combo_reqAdd;
    @FXML private ComboBox<?> combo_reqChg;
    @FXML private ComboBox<?> combo_reqDel;
    @FXML private ComboBox<?> combo_reqUnit;
    @FXML private ComboBox<?> combo_reqDesign;
    @FXML private ComboBox<?> combo_reqCode;
    @FXML private ComboBox<?> combo_reqIntegration;
    @FXML private ComboBox<?> combo_reqRI;
    @FXML private ComboBox<?> combo_reqRommer;
    @FXML private ComboBox<?> combo_reqProgram;



    /** Complete section **/
    @FXML private StackPane stackPane_complete;
    @FXML private TextArea textArea_complete;
}
