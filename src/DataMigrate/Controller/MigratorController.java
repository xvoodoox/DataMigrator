package DataMigrate.Controller;

import DataMigrate.Model.MigratorModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by Anthony Orio on 4/25/2017.
 */
public class MigratorController
{
    private int paneCount = 0;
    @FXML private StackPane stackPane_parent;

    /** Buttons on the bottom of window **/
    @FXML private Button button_back;
    @FXML private Button button_next;
    @FXML private Button button_cancel;

    /** Estimation base section **/
        /* Estimation indicies */
    private int estimationBaseline, estimationCPRS, estimationDay, estimationDocument, estimationUpgrade, estimationDDR, estimationUnitTest;
    private int estimationIntegration, estimationMonth, estimationDefault, estimationDate, estimationDesign, estimationCode, estimationMaint;

        /* GUI Components */
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
    /** End estimation base section **/


    /** SC/ICR section **/
        /* SC/ICR indicies */
    private int scicrType, scicrNumber, scicrTitle, scicrBuild, scicrBaseline;

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
    /** End SC/ICR Section **/


    /** ValCode section **/
    @FXML private StackPane stackPane_valCode;
    @FXML private TextArea textArea_valCode;
    @FXML private ComboBox<String> combo_valCode;
    @FXML private TextField field_valCode;
    @FXML private Button button_valCode;
    /** End ValCode section **/


    /** Requirements section **/
        /* Requirements indicies */
    private int reqCSC, reqCSU, reqDoors, reqPara, reqBaseline, reqBuild, reqScIcr, reqCap, reqAdd, reqChg, reqDel, reqTest;
    private int reqDesign, reqCode, reqInteg, reqRI, reqRom, reqProgram;

        /* GUI Components */
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
    /** End requirements section **/


    /** Complete section **/
    @FXML private StackPane stackPane_complete;
    @FXML private TextArea textArea_complete;
    /** End complete section **/

    ObservableList<String> valCodes;


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
        stackPane_valCode.setVisible(false);
        stackPane_requirementsBegin.setVisible(false);
        stackPane_requirements.setVisible(false);
        stackPane_complete.setVisible(false);

        this.fillValCodeCombo();
    }

    private void fillValCodeCombo()
    {
        valCodes = FXCollections.observableArrayList(Arrays.asList(MigratorModel.valTypes));
        combo_valCode.setItems(valCodes);
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
//                estimationBaseline = combo_baseline.getSelectionModel().getSelectedIndex();
//                estimationCPRS = combo_cprs.getSelectionModel().getSelectedIndex();
//                estimationDay = combo_day.getSelectionModel().getSelectedIndex();
//                estimationDocument = combo_document.getSelectionModel().getSelectedIndex();
//                estimationUpgrade = combo_upgrade.getSelectionModel().getSelectedIndex();
//                estimationDDR = combo_ddr.getSelectionModel().getSelectedIndex();
//                estimationUnitTest = combo_unitTestWeight.getSelectionModel().getSelectedIndex();
//                estimationIntegration = combo_integrationWeight.getSelectionModel().getSelectedIndex();
//                estimationMonth = combo_month.getSelectionModel().getSelectedIndex();
//                estimationDefault = combo_default.getSelectionModel().getSelectedIndex();
//                estimationDate = combo_date.getSelectionModel().getSelectedIndex();
//                estimationDesign = combo_designWeight.getSelectionModel().getSelectedIndex();
//                estimationCode = combo_codeWeight.getSelectionModel().getSelectedIndex();
//                estimationMaint = combo_maint.getSelectionModel().getSelectedIndex();
//
//
//                try {
//                    MigratorModel.performROMTransfer( estimationBaseline, estimationCPRS, estimationMonth, estimationDay, estimationDocument,
//                                                      estimationUpgrade, estimationMaint, estimationDDR, estimationUnitTest, estimationIntegration,
//                                                      estimationDesign, estimationCode, estimationDefault, estimationDate);
//                } catch (SQLException e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not complete transfer.", ButtonType.OK);
//                    alert.showAndWait();
//                }


                stackPane_estimation.setVisible(false);
                stackPane_beginSCICR.setVisible(true);
                break;
            case 2:
                this.fillSCICRCombos();
                stackPane_beginSCICR.setVisible(false);
                stackPane_scicr.setVisible(true);
                break;
            case 3:
//                scicrBaseline = combo_scicrBaseline.getSelectionModel().getSelectedIndex();
//                scicrType = combo_scicrType.getSelectionModel().getSelectedIndex();
//                scicrBuild = combo_scicrBuild.getSelectionModel().getSelectedIndex();
//                scicrNumber = combo_scicrNumber.getSelectionModel().getSelectedIndex();
//                scicrTitle = combo_scicrTitle.getSelectionModel().getSelectedIndex();
//
//                try {
//                    MigratorModel.performSCICRTransfer(scicrType, scicrNumber, scicrTitle, scicrBuild, scicrBaseline);
//                } catch (SQLException e) {
//                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not complete transfer.", ButtonType.OK);
//                    alert.showAndWait();
//                }

                stackPane_scicr.setVisible(false);
                stackPane_valCode.setVisible(true);
                break;
            case 4:
                stackPane_valCode.setVisible(false);
                stackPane_requirementsBegin.setVisible(true);
                break;
            case 5:
                this.fillRequirementCombos();
                stackPane_requirementsBegin.setVisible(false);
                stackPane_requirements.setVisible(true);
                break;
            case 6:
                reqCSC = combo_reqCSC.getSelectionModel().getSelectedIndex();
                reqCSU = combo_reqCSU.getSelectionModel().getSelectedIndex();
                reqDoors = combo_reqDoors.getSelectionModel().getSelectedIndex();
                reqPara = combo_reqPara.getSelectionModel().getSelectedIndex();
                reqBaseline = combo_reqBaseline.getSelectionModel().getSelectedIndex();
                reqBuild = combo_reqBuild.getSelectionModel().getSelectedIndex();
                reqScIcr = combo_reqSCICR.getSelectionModel().getSelectedIndex();
                reqCap = combo_reqCapability.getSelectionModel().getSelectedIndex();
                reqAdd = combo_reqAdd.getSelectionModel().getSelectedIndex();
                reqChg = combo_reqChg.getSelectionModel().getSelectedIndex();
                reqDel = combo_reqDel.getSelectionModel().getSelectedIndex();
                reqTest = combo_reqUnit.getSelectionModel().getSelectedIndex();
                reqDesign = combo_reqDesign.getSelectionModel().getSelectedIndex();
                reqCode = combo_reqCode.getSelectionModel().getSelectedIndex();
                reqInteg = combo_reqIntegration.getSelectionModel().getSelectedIndex();
                reqRI = combo_reqRI.getSelectionModel().getSelectedIndex();
                reqRom = combo_reqRommer.getSelectionModel().getSelectedIndex();
                reqProgram = combo_reqProgram.getSelectionModel().getSelectedIndex();

                try {
                    MigratorModel.performReqTransfer(   reqCSC, reqCSU, reqDoors, reqPara, reqBaseline,
                                                        reqBuild, reqScIcr, reqCap, reqAdd, reqChg,
                                                        reqDel, reqTest, reqDesign, reqCode, reqInteg,
                                                        reqRI, reqRom, reqProgram );
                } catch (SQLException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not complete transfer.", ButtonType.OK);
                    alert.showAndWait();
                }

                stackPane_requirements.setVisible(false);
                stackPane_complete.setVisible(true);
                break;
            default:
                paneCount = 7;
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
                stackPane_valCode.setVisible(false);
                break;
            case 4:
                stackPane_valCode.setVisible(true);
                stackPane_requirementsBegin.setVisible(false);
                break;
            case 5:
                stackPane_requirementsBegin.setVisible(true);
                stackPane_requirements.setVisible(false);
                break;
            case 6:
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
        field_estimationCSV.setText(MigratorModel.estimationCSVFile.getAbsolutePath());
    }

    @FXML
    public void findSCICRCSV()
    {
        MigratorModel.findSCICRCSV();
        field_scicrCSV.setText(MigratorModel.scicrCSVFile.getAbsolutePath());
    }

    @FXML
    public void findValCodeCSV()
    {
        MigratorModel.findValCodeCSV();
        field_valCode.setText(MigratorModel.valCodeCSVFile.getAbsolutePath());
    }

    @FXML
    public void findRequirementsCSV()
    {
        MigratorModel.findRequirementsCSV();
        field_requirementsPath.setText(MigratorModel.requirementsCSVFile.getAbsolutePath());
    }

    @FXML
    public void selectValItem()
    {
        String selVal = combo_valCode.getValue();
        field_valCode.setPromptText(selVal + " .csv file");

        switch (selVal)
        {
            case "CSC":

                break;
            case "CSU":
                break;
            case "Capability":
                break;
            case "RI":
                break;
            case "Rommer":
                break;
            case "Program":
                break;
            case "Build":
                break;
            default:
                break;

        }
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
