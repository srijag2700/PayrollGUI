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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private static final int MAX_HOURS = 100;

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
    void remove (ActionEvent event) {
        if (comp.isEmpty()) {
            messageArea.appendText("Employee database is empty.\n");
            return;
        }

        String remEmpName = empName.getText();
        String remDeptName = "";
        try {
            RadioButton selectedDept = (RadioButton) dept.getSelectedToggle();
            remDeptName = selectedDept.getText();
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please select a department.\n");
            return;
        }

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Date remDateHired = null;
        try {
            remDateHired = new Date(dateHired.getValue().format(formatDate));
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please enter a date." + "\n");
            return;
        }

        Profile tempProfile = new Profile(remEmpName, remDeptName, remDateHired);
        Employee tempEmployee = new Employee(tempProfile);
        if (comp.remove(tempEmployee)) {
            messageArea.appendText("Employee removed.\n");
            return;
        }
        else {
            messageArea.appendText("Employee doesn't exist.\n");
            return;
        }
    }

    @FXML
    void setHours(ActionEvent event) {
        if (comp.isEmpty()) {
            messageArea.appendText("Employee database is empty.\n");
            return;
        }

        String tempEmpName = empName.getText();
        String tempDeptName = "";
        try {
            RadioButton selectedDept = (RadioButton) dept.getSelectedToggle();
            tempDeptName = selectedDept.getText();
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please select a department.\n");
            return;
        }

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        Date tempDateHired = null;
        try {
            tempDateHired = new Date(dateHired.getValue().format(formatDate));
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please enter a date." + "\n");
            return;
        }

        String newEmpType = "";
        try {
            RadioButton selectedEmpType = (RadioButton) employeeType.getSelectedToggle();
            newEmpType = selectedEmpType.getText();
        }
        catch (NullPointerException e) {
            messageArea.appendText("Please select an employment type.\n");
            return;
        }

        if (!newEmpType.equals("Part Time")) {
            messageArea.appendText("Hours can only be set for a part-time employee.\n");
            return;
        }

        int hours = 0;
        if (!hoursWorked.getText().equals("")) {
            try {
                hours = Integer.parseInt(hoursWorked.getText());
            }
            catch (NumberFormatException e) {
                messageArea.appendText("Number of hours worked must be an integer.\n");
                return;
            }
        }
        else {
            messageArea.appendText("Please enter the number of hours worked.\n");
            return;
        }

        if (hours > MAX_HOURS) {
            messageArea.appendText("Invalid hours: over 100\n");
            return;
        }
        else if (hours < 0) {
            messageArea.appendText("Working hours cannot be negative.\n");
            return;
        }
        else {
            Profile tempProfile = new Profile(tempEmpName, tempDeptName, tempDateHired);
            Parttime tempPartTime = new Parttime(tempProfile, hours);
            if (comp.setHours(tempPartTime)) {
                messageArea.appendText("Working hours set.\n");
                return;
            }
            else {
                messageArea.appendText("Employee does not exist.\n");
                return;
            }
        }

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

    @FXML
    void calcPayments (ActionEvent event) {
        if (comp.isEmpty()) {
            messageArea.appendText("Employee database is empty.\n");
            return;
        }
        comp.processPayments();
        messageArea.appendText("Calculation of employee payments is done.\n");
        return;
    }

    @FXML
    void importDatabase (ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose File to Import");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file

        try {
            FileReader fr = new FileReader(sourceFile);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String empType = tokens[0];
                String empName = tokens[1];
                String empDept = tokens[2];
                String tempDateHired = tokens[3];
                Date empDateHired = new Date(tempDateHired);

                Profile empProfile = new Profile(empName, empDept, empDateHired);

                if (empType.equals("P")) {
                    double empHourlyRate = Double.parseDouble(tokens[4]);
                    Parttime empPartTime = new Parttime(empProfile, empHourlyRate);
                    comp.add(empPartTime);
                }
                else if (empType.equals("F")) {
                    double empSalary = Double.parseDouble(tokens[4]);
                    Fulltime empFullTime = new Fulltime(empProfile, empSalary);
                    comp.add(empFullTime);
                }
                else if (empType.equals("M")) {
                    double empMgmtSalary = Double.parseDouble(tokens[4]);
                    int empMgmtCode = Integer.parseInt(tokens[5]);
                    Management empMgmt = new Management(empProfile, empMgmtSalary, empMgmtCode);
                    comp.add(empMgmt);
                }
            }

            messageArea.appendText("Database imported.\n");
            return;

        } catch (Exception e) {
            messageArea.appendText("Error while importing database.\n");
            return;
        }
    }

    @FXML
    void export(ActionEvent event) throws IOException {
        if (comp.isEmpty()) {
            messageArea.appendText("Employee database is empty.\n");
            return;
        }
        try {
            comp.exportDatabase();
        }
        catch (Exception e) {
            messageArea.appendText("Error exporting database.\n");
            return;
        }
        messageArea.appendText("Database exported.\n");
        return;
    }

    @FXML
    void print(ActionEvent event) {
        messageArea.appendText(comp.print() + "\n");
        return;
    }

    @FXML
    void printByDate(ActionEvent event) {
        messageArea.appendText(comp.printByDate() + "\n");
        return;
    }

    @FXML
    void printByDept(ActionEvent event) {
        messageArea.appendText(comp.printByDepartment() + "\n");
        return;
    }
}