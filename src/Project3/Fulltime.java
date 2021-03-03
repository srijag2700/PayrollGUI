package Project3;
import java.text.DecimalFormat;

/**
 * This class represents a full-time employee at a company.
 * It extends the Employee class, and also includes a field for the number of pay periods in a year.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Fulltime extends Employee{
    public static final int PAY_PERIODS = 26; // final?
    DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    /**
     * Initializes a new Fulltime object with a given employee profile and yearly salary information.
     * @param employeeProfile the employee's profile
     * @param salary the employee's yearly salary
     */
    public Fulltime(Profile employeeProfile, double salary) {
        super(employeeProfile, salary);
    }

    /**
     * Returns a string representation of the full-time employee.
     * @return a string representation of the full-time employee
     */
    @Override
    public String toString() {
        return super.toString() + "::Payment " + df.format(super.getPayment())
                + "::FULL TIME::Annual Salary " + df.format(super.getSalary());
    }

    /**
     * Compares current Fulltime object to another Fulltime object.
     * Determines equality by checking if the profiles, salaries, and payments of both Fulltime objects are identical.
     * @param obj the Fulltime employee to compare to
     * @return true if the profiles, salaries, and payments are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;
        return (super.equals(obj) && super.getSalary() != emp.getSalary() && super.getPayment() != emp.getPayment());
    }

    /**
     * Calculates the full time employee's payment.
     */
    @Override
    public void calculatePayment() {
        super.setPayment(super.getSalary()/PAY_PERIODS);
        // maybe do more?
    }
}
