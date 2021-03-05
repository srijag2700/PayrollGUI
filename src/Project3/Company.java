package Project3;

/**
 * The Company class is used to store all of the current employees in array format.
 * It includes fields such as the list of employees, number of employees and data about the capacity, and
 * methods such as growing the array, adding an employee, removing an employee, setting a part time employee's hours,
 * processing payments for all employees, and printing the list of employees in its current order, by date of hire, or by department.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Company {
    private Employee[] emplist;
    private int numEmployee;
    private final static int CAPACITY = 4;
    private final static int NOT_FOUND = -1;

    /**
     * Initializes a new Company object with array size 4 and no employees.
     */
    public Company() {
        numEmployee = 0;
        emplist = new Employee[CAPACITY];
    }

    /**
     * Searches the array iteratively for a given employee.
     * @param employee the employee to find
     * @return index of employee in array, -1 if not found
     */
    private int find(Employee employee) {
        if (numEmployee == 0) {
            return NOT_FOUND;
        }

        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                continue;
            }
            else if (emplist[i].getEmployeeProfile().equals(employee.getEmployeeProfile())) {
                return i; // returns index of employee
            }
        }
        return NOT_FOUND;
    }

    /**
     * Increases the capacity of the company by 4 when the array is full.
     */
    private void grow() {
        int currentLength = emplist.length;
        int newLength = currentLength + CAPACITY;

        Employee[] tempEmp = new Employee[newLength];

        // copy over all current employees
        for (int i = 0; i < currentLength; i++) {
            tempEmp[i] = emplist[i];
        }

        emplist = tempEmp;
    }

    /**
     * Adds an employee to the company.
     * @param employee the employee to add
     * @return true if employee is successfully added, false otherwise
     */
    public boolean add(Employee employee) {
        if (numEmployee == emplist.length) {
            grow();
        }

        if (find(employee) == NOT_FOUND) {
            emplist[numEmployee] = employee;
            numEmployee++;
            return true;
        }

        return false;
    } //check the profile before adding

    /**
     * Removes an employee from the company.
     * @param employee the employee to remove
     * @return true if employee is successfully removed, false otherwise
     */
    public boolean remove(Employee employee) {

        int empIndex = find(employee);
        if (empIndex == NOT_FOUND) {
            return false;
        }

        Employee[] tempEmp = new Employee[emplist.length-1];
        for (int i = 0, j = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                continue;
            }
            else if (!emplist[i].equals(employee)) {
                tempEmp[j++] = emplist[i];
            }
        }
        emplist = tempEmp;
        numEmployee--;
        return true;
    } //maintain the original sequence

    /**
     * Sets the hours worked in the current pay period for the given (part-time) employee.
     * @param employee the part-time employee
     * @return true if hours successfully set, false otherwise
     */
    public boolean setHours(Employee employee) {
        // make temporary employee in payrollprocessing with those hours & pass it into this method
        if (isEmpty()) {
            return false;
        }

        int empIndex = find(employee);
        if (empIndex == NOT_FOUND) {
            return false;
        }
        Parttime partTimeEmployee = (Parttime) employee;
        Parttime tempPartTime = (Parttime) emplist[empIndex];
        tempPartTime.setHoursWorked(partTimeEmployee.getHoursWorked());
        emplist[empIndex] = tempPartTime;
        return true;

    } //set working hours for a part time

    /**
     * Calculates payments for all employees in the company.
     */
    public void processPayments() {
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                continue;
            }
            else {
                emplist[i].calculatePayment();
            }
        }
    } //process payments for all employees

    /**
     * Prints the list of employees in its current order.
     * @return the list of employees in its current order
     */
    public String print() {
        String output = "";
        if (numEmployee == 0) {
            output += ("Employee database is empty.");
            return output;
        }
        System.out.println("--Printing earning statements for all employees--");
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] != null) {
                output += (emplist[i]);
                output += "\n";
            }
        }
        return output;
    } //print earning statements for all employees

    /**
     * Prints the list of employees sorted by their department, in alphabetical order.
     * @return the list of employees sorted by their department, in alphabetical order
     */
    public String printByDepartment() {
        String output = "";
        if (numEmployee == 0) {
            output += ("Employee database is empty.");
            return output;
        }
        insertionSortByDepartment();
        System.out.println("--Printing earning statements by department--");
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] != null) {
                output += (emplist[i]);
                output += "\n";
            }
        }
        return output;
    } //print earning statements by department

    /**
     * Prints the list of employees sorted by their date hired, in ascending order.
     * @return the list of employees sorted by their date hired, in ascending order
     */
    public String printByDate() {
        String output = "";
        if (numEmployee == 0) {
            output += ("Employee database is empty.");
            return output;
        }
        insertionSortByDate();
        System.out.println("--Printing earning statements by date hired--");
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] != null) {
                output += (emplist[i]);
                output += "\n";
            }
        }
        return output;
    } //print earning statements by date hired

    /**
     * Checks if there are employees in the company.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if (numEmployee == 0) {
            return true;
        }
        return false;
    }

    /**
     * Performs an insertion sort on the company array to sort by department name in alphabetical order.
     */
    private void insertionSortByDepartment() {
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                continue;
            }
            Employee currEmp = emplist[i];
            String currDept = currEmp.getEmployeeProfile().getDepartment();
            int j = i - 1;
            while (j >= 0 && currDept.compareTo(emplist[j].getEmployeeProfile().getDepartment()) < 0) {
                emplist[j+1] = emplist[j];
                j--;
            }
            emplist[j+1] = currEmp;
        }
    }

    /**
     * Performs an insertion sort on the company array to sort by date hired, from earliest to latest.
     */
    private void insertionSortByDate() {
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                continue;
            }
            Employee currEmp = emplist[i];
            Date currDate = currEmp.getEmployeeProfile().getDateHired();
            int j = i - 1;
            while (j >= 0 && currDate.compareTo(emplist[j].getEmployeeProfile().getDateHired()) <= 0) {
                emplist[j+1] = emplist[j];
                j--;
            }
            emplist[j+1] = currEmp;

        }
    }
}