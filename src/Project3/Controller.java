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
import java.time.format.DateTimeFormatter;

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

    Company comp = new Company();

    @FXML
    private void initialize() {
        hoursWorked.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choiceManagement.selectedProperty()));
        rate.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choiceManagement.selectedProperty()));

        choiceManager.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));
        choiceDeptHead.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));
        choiceDirector.disableProperty().bind(Bindings.or(choiceFullTime.selectedProperty(), choicePartTime.selectedProperty()));

        annualSalary.disableProperty().bind(choicePartTime.selectedProperty());

        //Company comp = new Company();
    }

    @FXML
    void add(ActionEvent event) {
        String newEmpName = empName.getText();
        String newDeptName = "";
        try {
            RadioButton selectedDept = (RadioButton) dept.getSelectedToggle();
            newDeptName = selectedDept.getText();
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please select a department.\n");
            return;
        }

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Date newDateHired = null;
        try {
            newDateHired = new Date(dateHired.getValue().format(formatDate));
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please enter a date." + "\n");
            return;
        }
        boolean added = false;

        if (!newDateHired.isValid()) {
            messageArea.appendText(dateHired + " is not a valid date!" + "\n");
            return;
        }

        Profile newEmpProf = new Profile(newEmpName, newDeptName, newDateHired);

        String newEmpType = "";
        try {
            RadioButton selectedEmpType = (RadioButton) employeeType.getSelectedToggle();
            newEmpType = selectedEmpType.getText();
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please select an employment type.\n");
            return;
        }

        if (newEmpType.equals("Part Time")) {
            double hourlyPay = 0;
            try {
                hourlyPay = Double.parseDouble(rate.getText());
            }
            catch (NumberFormatException e) {
                messageArea.appendText("Please enter an hourly pay.\n");
                return;
            }

            if (hourlyPay < 0) {
                messageArea.appendText("Pay rate cannot be negative.\n");
                return;
            }
            Parttime newPartTime = new Parttime(newEmpProf, hourlyPay);
            added = comp.add(newPartTime);
        }

        else if (newEmpType.equals("Full Time")) {
            double salary = 0;
            try {
                salary = Double.parseDouble(annualSalary.getText());
            }
            catch (NumberFormatException e) {
                messageArea.appendText("Please enter an annual salary.\n");
                return;
            }

            if (salary < 0) {
                messageArea.appendText("Pay rate cannot be negative.\n");
                return;
            }
            Fulltime newFullTime = new Fulltime(newEmpProf, salary);
            added = comp.add(newFullTime);
        }

        else if (newEmpType.equals("Management")) {
            double salary = 0;
            try {
                salary = Double.parseDouble(annualSalary.getText());
            }
            catch (NumberFormatException e) {
                messageArea.appendText("Please enter an annual salary.\n");
                return;
            }

            String newMgmtStatus = "";
            try {
                RadioButton selectedMgmt = (RadioButton) mgmtType.getSelectedToggle();
                newMgmtStatus = selectedMgmt.getText();
            }
            catch (NullPointerException e) {
                messageArea.appendText("Please select a management type.\n");
                return;
            }

            int mgmtNumber = 0;
            if (newMgmtStatus.equals("Manager")) {
                mgmtNumber = 1;
            }
            else if (newMgmtStatus.equals("Department Head")) {
                mgmtNumber = 2;
            }
            else if (newMgmtStatus.equals("Director")) {
                mgmtNumber = 3;
            }
            if (salary < 0) {
                messageArea.appendText("Salary cannot be negative.\n");
                return;
            }

            Management newMgmt = new Management(newEmpProf, salary, mgmtNumber);
            added = comp.add(newMgmt);
        }

        if (added) {
            messageArea.appendText("Employee added.\n");
            return;
        }
        else {
            messageArea.appendText("Employee is already in the list.\n");
            return;
        }
    }

    @FXML
    void print(ActionEvent event) {
        messageArea.appendText(comp.print() + "\n");
        return;
    }

    @FXML
    void clear(ActionEvent event) {
        empName.clear();
        annualSalary.clear();
        hoursWorked.clear();
        rate.clear();
        dateHired.getEditor().clear();
        dateHired.setValue(null);
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