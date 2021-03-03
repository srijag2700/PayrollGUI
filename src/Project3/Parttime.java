package Project3;
import java.text.DecimalFormat;

/**
 * This class represents a part-time employee at a company.
 * It extends the Employee class, and also includes its own field for the number of hours a part-time employee has worked.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Parttime extends Employee{

    private int hoursWorked;
    DecimalFormat df = new DecimalFormat("$#,###,###,##0.00");

    /**
     * Initializes a new Parttime object with a given employee profile and hourly salary information, and a default of 0 hours worked.
     * @param employeeProfile the employee's profile
     * @param salary the employee's hourly salary
     */
    public Parttime(Profile employeeProfile, double salary) {
        super(employeeProfile, salary);
        hoursWorked = 0;
    }

    /**
     * Initializes a new Parttime object with a given employee profile and the number of hours they worked in the current pay period.
     * @param employeeProfile the employee's profile
     * @param hoursWorked the number of hours they worked
     */
    public Parttime(Profile employeeProfile, int hoursWorked) {
        super(employeeProfile);
        this.hoursWorked = hoursWorked;
    }

    /**
     * Sets the number of hours worked by a part-time employee in a given pay period.
     * @param hoursWorked number of hours worked
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the number of hours worked by a part-time employee in a given pay period.
     * @return number of hours worked
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * Returns a string representation of the part-time employee.
     * @return a string representation of the part-time employee
     */
    @Override
    public String toString() {
        return super.toString() + "::Payment " + df.format(super.getPayment()) + "::PART TIME::Hourly Rate "
                + df.format(super.getSalary()) + "::Hours worked this period: " + hoursWorked;
    }

    /**
     * Compares current Parttime object to another Parttime object.
     * Determines equality by checking if the profiles, salaries, hours worked, and payments of both Parttime objects are identical.
     * @param obj the Parttime employee to compare to
     * @return true if the profiles, salaries, hours worked, and payments are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            return super.equals(obj);
        }
        Parttime emp = (Parttime) obj;
        if (!super.equals(obj) && super.getSalary() != emp.getSalary() && super.getPayment() != emp.getPayment() && hoursWorked != emp.getHoursWorked()) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the part time employee's payment.
     */
    @Override
    public void calculatePayment() {
        int overtimeHours = 0;
        int maxHours = 80;
        if (hoursWorked > maxHours) {
            overtimeHours = hoursWorked - maxHours;
            double overtimeRate = 1.5;
            super.setPayment((maxHours * super.getSalary()) + (overtimeHours * super.getSalary() * overtimeRate));
        }
        else {
            super.setPayment(hoursWorked * super.getSalary());
        }
    }
}
