package Project3;
import java.text.DecimalFormat;

/**
 * This class represents a management employee at a company.
 * It extends the Fulltime class, and also includes fields for management role, additional compensation, and information for each type of management profile.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Management extends Fulltime{
    private String managementRole;
    private double addtlCompensation;
    private final static int MANAGER = 1;
    private final static int DEPT_HEAD = 2;
    private final static int DIRECTOR = 3;
    private final static int MANAGER_COMP = 5000;
    private final static int DEPT_HEAD_COMP = 9500;
    private final static int DIRECTOR_COMP = 12000;
    DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    /**
     * Initializes a new Management object with a given employee profile, yearly salary information, and management role.
     * @param employeeProfile the employee's profile
     * @param salary the employee's yearly salary
     * @param managementStatus the employee's management type
     */
    public Management(Profile employeeProfile, double salary, int managementStatus) {
        super(employeeProfile, salary);
        if (managementStatus == MANAGER) {
            managementRole = "Manager";
            addtlCompensation = MANAGER_COMP;
        }
        else if (managementStatus == DEPT_HEAD) {
            managementRole = "DepartmentHead";
            addtlCompensation = DEPT_HEAD_COMP;
        }
        else if (managementStatus == DIRECTOR) {
            managementRole = "Director";
            addtlCompensation = DIRECTOR_COMP;
        }
    }

    /**
     * Returns a string representation of the management employee.
     * @return a string representation of the management employee
     */
    @Override
    public String toString() {
        return super.toString() + "::" + managementRole + " Compensation " + df.format(addtlCompensation/PAY_PERIODS); // fix addtlCompensation in kiosk?
    }

    /**
     * Compares current Management object to another Management object.
     * Determines equality by checking if the profiles, salaries, management roles, and payments of both Management objects are identical.
     * @param obj the Management employee to compare to
     * @return true if the profiles, salaries, management roles, and payments are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            return super.equals(obj);
        }
        Management emp = (Management) obj;
        if (!super.equals(obj) && super.getSalary() != emp.getSalary() && super.getPayment() != emp.getPayment() && !managementRole.equals(emp.managementRole)
            && addtlCompensation != emp.addtlCompensation) {
                return false;
        }
        return true;
    }

    /**
     * Calculates the management employee's payment.
     */
    @Override
    public void calculatePayment() {
        super.setPayment((super.getSalary() + addtlCompensation)/PAY_PERIODS);
    }
}
