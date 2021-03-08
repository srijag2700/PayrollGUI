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
    void add(ActionEvent event) {
        try {
            String newEmpName = empName.getText();
            String newDeptName; // fix
            String newDateHired = dateHired.getValue().toString();

            Profile newEmpProf = new Profile(newEmpName, newDeptName, newDateHired);


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
