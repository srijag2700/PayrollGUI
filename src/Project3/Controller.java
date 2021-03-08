package Project3;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

public class Controller {

    @FXML
    private Button clearButton, addButton, removeButton, setHoursButton;

    @FXML
    private RadioButton choiceCS, choiceECE, choiceIT, choiceManager, choiceDeptHead, choiceDirector, choiceFullTime, choicePartTime, choiceManagement;

    @FXML
    private TextField empName, annualSalary, hoursWorked, rate;

    @FXML
    private DatePicker dateHired;

    @FXML
    private TextArea messageArea;

    @FXML
    private void initialize() {
        ToggleGroup dept = new ToggleGroup();
        choiceCS.setToggleGroup(dept);
        choiceECE.setToggleGroup(dept);
        choiceIT.setToggleGroup(dept);

        ToggleGroup employeeType = new ToggleGroup();
        choiceFullTime.setToggleGroup(employeeType);
        choicePartTime.setToggleGroup(employeeType);
        choiceManagement.setToggleGroup(employeeType);

        ToggleGroup mgmtType = new ToggleGroup();
        choiceManager.setToggleGroup(mgmtType);
        choiceDeptHead.setToggleGroup(mgmtType);
        choiceDirector.setToggleGroup(mgmtType);
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
