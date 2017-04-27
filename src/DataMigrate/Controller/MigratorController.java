package DataMigrate.Controller;

import DataMigrate.Model.MigratorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Anthony Orio on 4/25/2017.
 */
public class MigratorController
{
    private int paneCount = 0;
    @FXML private StackPane stackPane_parent;

    /** Always visible **/
    @FXML private Button button_back;
    @FXML private Button button_next;
    @FXML private Button button_cancel;

    /** Estimation indicies **/
    private int estimationBaseline, estimationCPRS, estimationDay, estimationDocument, estimationUpgrade, estimationDDR, estimationUnitTest;
    private int estimationIntegration, estimationMonth, estimationDefault, estimationDate, estimationDesign, estimationCode;

    /** SC/ICR indicies **/
    private int scicrType, scicrNumber, scicrTitle, scicrBuild, scicrBaseline;

    /** Requirements indicies **/
    private int reqCSC, reqCSU, reqDoors, reqPara, reqBaseline, reqBuild, reqScIcr, reqCap, reqAdd, reqChg, reqDel, reqTest;
    private int reqDesign, reqCode, reqInteg, reqRI, reqRom, reqProgram;

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

    @FXML private ComboBox<String> combo_scicrType;
    @FXML private ComboBox<String> combo_scicrNumber;
    @FXML private ComboBox<String> combo_scicrTitle;
    @FXML private ComboBox<String> combo_scicrBuild;
    @FXML private ComboBox<String> combo_scicrBaseline;



    /** Requirements section **/
    @FXML private StackPane stackPane_requirementsBegin;
    @FXML private StackPane stackPane_requirements;

    @FXML private Button button_requirementsFind;

    @FXML private TextArea textArea_requirements;
    @FXML private TextField field_requirementsPath;

    @FXML private ComboBox<String> combo_reqCSC;
    @FXML private ComboBox<String> combo_reqCSU;
    @FXML private ComboBox<String> combo_reqDoors;
    @FXML private ComboBox<String> combo_reqPara;
    @FXML private ComboBox<String> combo_reqBaseline;
    @FXML private ComboBox<String> combo_reqBuild;
    @FXML private ComboBox<String> combo_reqSCICR;
    @FXML private ComboBox<String> combo_reqCapability;
    @FXML private ComboBox<String> combo_reqAdd;
    @FXML private ComboBox<String> combo_reqChg;
    @FXML private ComboBox<String> combo_reqDel;
    @FXML private ComboBox<String> combo_reqUnit;
    @FXML private ComboBox<String> combo_reqDesign;
    @FXML private ComboBox<String> combo_reqCode;
    @FXML private ComboBox<String> combo_reqIntegration;
    @FXML private ComboBox<String> combo_reqRI;
    @FXML private ComboBox<String> combo_reqRommer;
    @FXML private ComboBox<String> combo_reqProgram;



    /** Complete section **/
    @FXML private StackPane stackPane_complete;
    @FXML private TextArea textArea_complete;



    @FXML
    public void initialize()
    {
        this.reset();
    }

    private void reset()
    {
        stackPane_beginEstimation.setVisible(true);
        stackPane_estimation.setVisible(false);
        stackPane_beginSCICR.setVisible(false);
        stackPane_scicr.setVisible(false);
        stackPane_requirementsBegin.setVisible(false);
        stackPane_requirements.setVisible(false);
        stackPane_complete.setVisible(false);
    }


    @FXML
    public void hitNext()
    {
        switch (paneCount++)
        {
            case 0:
                this.fillEstimationCombos();
                stackPane_beginEstimation.setVisible(false);
                stackPane_estimation.setVisible(true);
                break;
            case 1:
                estimationBaseline = combo_baseline.getSelectionModel().getSelectedIndex();
                System.out.println(estimationBaseline);
                estimationCPRS = combo_cprs.getSelectionModel().getSelectedIndex();
                System.out.println(estimationCPRS);
                estimationDay = combo_day.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDay);
                estimationDocument = combo_document.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDocument);
                estimationUpgrade = combo_upgrade.getSelectionModel().getSelectedIndex();
                System.out.println(estimationUpgrade);
                estimationDDR = combo_ddr.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDDR);
                estimationUnitTest = combo_unitTestWeight.getSelectionModel().getSelectedIndex();
                System.out.println(estimationUnitTest);
                estimationIntegration = combo_integrationWeight.getSelectionModel().getSelectedIndex();
                System.out.println(estimationIntegration);
                estimationMonth = combo_month.getSelectionModel().getSelectedIndex();
                System.out.println(estimationMonth);
                estimationDefault = combo_default.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDefault);
                estimationDate = combo_date.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDate);
                estimationDesign = combo_designWeight.getSelectionModel().getSelectedIndex();
                System.out.println(estimationDesign);
                estimationCode = combo_codeWeight.getSelectionModel().getSelectedIndex();
                System.out.println(estimationCode);

                stackPane_estimation.setVisible(false);
                stackPane_beginSCICR.setVisible(true);
                break;
            case 2:
                this.fillSCICRCombos();
                stackPane_beginSCICR.setVisible(false);
                stackPane_scicr.setVisible(true);
                break;
            case 3:
                scicrBaseline = combo_scicrBaseline.getSelectionModel().getSelectedIndex();
                System.out.println(scicrBaseline);
                scicrType = combo_scicrType.getSelectionModel().getSelectedIndex();
                System.out.println(scicrType);
                scicrBuild = combo_scicrBuild.getSelectionModel().getSelectedIndex();
                System.out.println(scicrBuild);
                scicrNumber = combo_scicrNumber.getSelectionModel().getSelectedIndex();
                System.out.println(scicrNumber);
                scicrTitle = combo_scicrTitle.getSelectionModel().getSelectedIndex();
                System.out.println(scicrTitle);


                stackPane_scicr.setVisible(false);
                stackPane_requirementsBegin.setVisible(true);
                break;
            case 4:
                this.fillRequirementCombos();
                stackPane_requirementsBegin.setVisible(false);
                stackPane_requirements.setVisible(true);
                break;
            case 5:
                reqCSC = combo_reqCSC.getSelectionModel().getSelectedIndex();
                System.out.println(reqCSC);
                reqCSU = combo_reqCSU.getSelectionModel().getSelectedIndex();
                System.out.println(reqCSU);
                reqDoors = combo_reqDoors.getSelectionModel().getSelectedIndex();
                System.out.println(reqDoors);
                reqPara = combo_reqPara.getSelectionModel().getSelectedIndex();
                System.out.println(reqPara);
                reqBaseline = combo_reqBaseline.getSelectionModel().getSelectedIndex();
                System.out.println(reqBaseline);
                reqBuild = combo_reqBuild.getSelectionModel().getSelectedIndex();
                System.out.println(reqBuild);
                reqScIcr = combo_reqSCICR.getSelectionModel().getSelectedIndex();
                System.out.println(reqScIcr);
                reqCap = combo_reqCapability.getSelectionModel().getSelectedIndex();
                System.out.println(reqCap);
                reqAdd = combo_reqAdd.getSelectionModel().getSelectedIndex();
                System.out.println(reqAdd);
                reqChg = combo_reqChg.getSelectionModel().getSelectedIndex();
                System.out.println(reqChg);
                reqDel = combo_reqDel.getSelectionModel().getSelectedIndex();
                System.out.println(reqDel);
                reqTest = combo_reqUnit.getSelectionModel().getSelectedIndex();
                System.out.println(reqTest);
                reqDesign = combo_reqDesign.getSelectionModel().getSelectedIndex();
                System.out.println(reqDesign);
                reqCode = combo_reqCode.getSelectionModel().getSelectedIndex();
                System.out.println(reqCode);
                reqInteg = combo_reqIntegration.getSelectionModel().getSelectedIndex();
                System.out.println(reqInteg);
                reqRI = combo_reqRI.getSelectionModel().getSelectedIndex();
                System.out.println(reqRI);
                reqRom = combo_reqRommer.getSelectionModel().getSelectedIndex();
                System.out.println(reqRom);
                reqProgram = combo_reqProgram.getSelectionModel().getSelectedIndex();
                System.out.println(reqProgram);

                stackPane_requirements.setVisible(false);
                stackPane_complete.setVisible(true);
                break;
            default:
                paneCount = 6;
                break;
        }
    }

    @FXML
    public void hitBack()
    {
        switch (--paneCount)
        {
            case 0:
                stackPane_beginEstimation.setVisible(true);
                stackPane_estimation.setVisible(false);
                break;
            case 1:
                stackPane_estimation.setVisible(true);
                stackPane_beginSCICR.setVisible(false);
                break;
            case 2:
                stackPane_beginSCICR.setVisible(true);
                stackPane_scicr.setVisible(false);
                break;
            case 3:
                stackPane_scicr.setVisible(true);
                stackPane_requirementsBegin.setVisible(false);
                break;
            case 4:
                stackPane_requirementsBegin.setVisible(true);
                stackPane_requirements.setVisible(false);
                break;
            case 5:
                stackPane_requirements.setVisible(true);
                stackPane_complete.setVisible(false);
                break;
            default:
                paneCount = 0;
                break;
        }
    }

    @FXML
    public void hitFinish()
    {

    }

    /**
     * Closes the data migrate view.
     */
    @FXML
    private void closeScene() {
        Stage stage = (Stage) button_next.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void findDatabase()
    {
        boolean connComplete = MigratorModel.findDatabaseFile();
        field_dbFile.setText(MigratorModel.databaseFile.getAbsolutePath());

        if (!connComplete) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not connect to database.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void findEstimationCSV()
    {
        MigratorModel.findEstimationCSV();
        field_estimationCSV.setText(MigratorModel.estimationCSV.getAbsolutePath());
    }

    @FXML
    public void findSCICRCSV()
    {
        MigratorModel.findSCICRCSV();
        field_scicrCSV.setText(MigratorModel.scicrCSV.getAbsolutePath());
    }

    @FXML
    public void findRequirementsCSV()
    {
        MigratorModel.findRequirementsCSV();
        field_requirementsPath.setText(MigratorModel.requirementsCSV.getAbsolutePath());
    }

    private void fillEstimationCombos()
    {
        ObservableList list = FXCollections.observableList(Arrays.asList(MigratorModel.estimationHeaders));

        combo_baseline.setItems(list);
        combo_cprs.setItems(list);
        combo_day.setItems(list);
        combo_document.setItems(list);
        combo_upgrade.setItems(list);
        combo_ddr.setItems(list);
        combo_unitTestWeight.setItems(list);
        combo_integrationWeight.setItems(list);
        combo_month.setItems(list);
        combo_default.setItems(list);
        combo_date.setItems(list);
        combo_maint.setItems(list);
        combo_designWeight.setItems(list);
        combo_codeWeight.setItems(list);
    }

    private void fillSCICRCombos()
    {
        ObservableList list = FXCollections.observableList(Arrays.asList(MigratorModel.scicrHeaders));

        combo_scicrNumber.setItems(list);
        combo_scicrBaseline.setItems(list);
        combo_scicrBuild.setItems(list);
        combo_scicrTitle.setItems(list);
        combo_scicrType.setItems(list);
    }

    private void fillRequirementCombos()
    {
        ObservableList list = FXCollections.observableList(Arrays.asList(MigratorModel.requirementsHeaders));

        combo_reqCSC.setItems(list);
        combo_reqCSU.setItems(list);
        combo_reqDoors.setItems(list);
        combo_reqPara.setItems(list);
        combo_reqBaseline.setItems(list);
        combo_reqBuild.setItems(list);
        combo_reqSCICR.setItems(list);
        combo_reqCapability.setItems(list);
        combo_reqAdd.setItems(list);
        combo_reqChg.setItems(list);
        combo_reqDel.setItems(list);
        combo_reqUnit.setItems(list);
        combo_reqDesign.setItems(list);
        combo_reqCode.setItems(list);
        combo_reqIntegration.setItems(list);
        combo_reqRI.setItems(list);
        combo_reqRommer.setItems(list);
        combo_reqProgram.setItems(list);

    }

}
