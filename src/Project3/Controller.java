package Project3;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.beans.binding.Bindings;

public class Controller {

    @FXML
    private Button clearButton, addButton, removeButton, setHoursButton;

    @FXML
    private RadioButton choiceCS, choiceECE, choiceIT, choiceManager, choiceDeptHead, choiceDirector, choiceFullTime, choicePartTime, choiceManagement;

    @FXML
    private ToggleGroup dept, employeeType, mgmtType;

    @FXML
    private TextField empName, annualSalary, hoursWorked, rate;

    @FXML
    private DatePicker dateHired;

    @FXML
    private TextArea messageArea;

    @FXML
    private void initialize() {
        hoursWorked.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choiceManagement.selectedProperty()));
        rate.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choiceManagement.selectedProperty()));

        choiceManager.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));
        choiceDeptHead.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));
        choiceDirector.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));

        annualSalary.disableProperty().bind(choicePartTime.selectedProperty());

        Company comp = new Company();
    }

    @FXML
    void add(ActionEvent event) {
        try {
            String newEmpName = empName.getText();
            RadioButton selectedDept = (RadioButton) dept.getSelectedToggle();
            String newDeptName = selectedDept.getText();
            String newDateHired = dateHired.getValue().toString();
            double payInfo = Double.parseDouble(rate);
            boolean added = false;

            // catching errors
            if (!dateHired.isValid()) {
                messageArea.appendText(dateHired + " is not a valid date!" + "\n");
                continue;
            }
            else if (!deptName.equals("CS") && !deptName.equals("ECE") && !deptName.equals("IT")) {
                messageArea.appendText("'" + deptName + "' is not a valid department code.\n");
                continue;
            }

            Profile newEmpProf = new Profile(newEmpName, newDeptName, newDateHired);

            RadioButton selectedEmpType = (RadioButton) employeeType.getSelectedToggle();
            String newEmpType = selectedEmpType.getText();
            if (newEmpType.equals("choicePartTime")) {
                if (payInfo < 0) {
                    messageArea.appendText("Pay rate cannot be negative.\n");
                    continue;
                }
                Parttime newPartTime = new Parttime(newEmpProf, payInfo);
                added = comp.add(newPartTime);
            }
            else if (newEmpType.equals("choiceFullTime")) {
                if (payInfo < 0) {
                    messageArea.appendText("Pay rate cannot be negative.\n");
                    continue;
                }
                Fulltime newFullTime = new Fulltime(newEmpProf, payInfo);
                added = com.add(newFullTime);
            }
            else if (newEmpType.equals("choiceManagement")) {
                RadioButton selectedMgmt = (RadioButton) mgmtType.getSelectedToggle();
                String newMgmtStatus = selectedMgmt.getText();
                int mgmtNumber;
                if (selectedMgmt.equals("choiceManager")) {
                    mgmtNumber = 1;
                }
                else if (selectedMgmt.equals("choiceDeptHead")) {
                    mgmtNumber = 2;
                }
                else if (selectedMgmt.equals("choiceDirector")) {
                    mgmtNumber = 3;
                }
                if (payInfo < 0) {
                    messageArea.appendText("Salary cannot be negative.\n");
                    continue;
                }

                Management newMgmt = new Management(newEmpProf, payInfo, mgmtNumber);
                added = com.add(newMgmt);
            }

            if (added) {
                messageArea.appendText("Employee added.\n");
            }
            else {
                messageArea.appendText("Employee is already in the list.\n");
            }
        }
        catch (Exception e){

        }
    }

    @FXML
    void clear(ActionEvent event) {
        empName.clear();
        annualSalary.clear();
        hoursWorked.clear();
        rate.clear();
        dateHired.getEditor().clear();
        choiceCS.setSelected(false);
        choiceECE.setSelected(false);
        choiceIT.setSelected(false);
        choiceDeptHead.setSelected(false);
        choiceManager.setSelected(false);
        choiceDirector.setSelected(false);
        choiceFullTime.setSelected(false);
        choicePartTime.setSelected(false);
        choiceManagement.setSelected(false);
    }
}