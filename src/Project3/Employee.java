package Project3;

/**
 * This class represents an employee at a company.
 * It includes fields for the employee's profile, their salary information, and their current payment.
 * This is the parent class for Parttime, Fulltime, and Management classes.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Employee {

    private Profile employeeProfile;
    private double salary; // comment
    private double payment;

    /**
     * Initializes a new Employee object with a given profile and a default salary and payment of 0.
     * @param employeeProfile the profile of the employee
     */
    public Employee(Profile employeeProfile) {
        this.employeeProfile = employeeProfile;
        this.salary = 0;
        this.payment = 0;
    }

    /**
     * Initializes a new Employee object with a given profile, given salary, and default payment of 0.
     * @param employeeProfile the profile of the employee
     * @param salary the salary of the employee
     */
    public Employee(Profile employeeProfile, double salary) {
        this.employeeProfile = employeeProfile;
        this.salary = salary;
        this.payment = 0;
    }

    /**
     * Sets the employee's payment.
     * @param payment the employee's payment
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }

    /**
     * Returns the employee's profile.
     * @return the employee's profile
     */
    public Profile getEmployeeProfile() {
        return employeeProfile;
    }

    /**
     * Returns the employee's salary.
     * @return the employee's salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the employee's payment.
     * @return the employee's payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * Calculate's the employee's payment.
     * This method is overridden for each type of employee.
     */
    public void calculatePayment() {
        return;
    }

    /**
     * Compares current Employee object to another Employee object.
     * Determines equality by checking if the two employees have identical profiles.
     * @param obj the Employee to compare to
     * @return true if the two Employees' profiles are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Employee emp = (Employee) obj;
        if (!employeeProfile.equals(emp.getEmployeeProfile())) {
            return false;
        }

        return true;
    }

    /**
     * Returns a string representation of the employee.
     * @return string representation of the employee
     */
    @Override
    public String toString() {
        return (employeeProfile.toString());
    }
}
