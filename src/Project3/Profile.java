package Project3;

/**
 * This class represents an employee profile.
 * It includes fields for the employee's name, the department they work in, and the date they were hired.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Profile {
    private String name; //employee’s name in the form "lastname,firstname"
    private String department; //department code: CS, ECE, IT
    private Date dateHired;

    /**
     * Initializes a new Profile object with a given employee name, their department, and the date they were hired.
     * @param name the employee's name
     * @param department the employee's department
     * @param dateHired the date the employee was hired
     */
    public Profile(String name, String department, Date dateHired) {
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * Returns the employee's name.
     * @return the employee's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the employee's department.
     * @return the employee's department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Returns the date the employee was hired.
     * @return the date the employee was hired
     */
    public Date getDateHired() {
        return dateHired;
    }

    /**
     * Returns a string representation of the employee's profile.
     * @return string representation of the employee's profile
     */
    @Override
    public String toString() {
        return (name + "::" + department + "::" + dateHired);
    }

    /**
     * Compares current Profile object to another Profile object.
     * Determines equality by checking if the names, departments, and hire dates of both Profiles are identical.
     * @param obj the profile to compare to
     * @return true if the profiles' names, departments, and hire dates are identical, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Profile profileObj = (Profile) obj;
        if (name.equals(profileObj.getName()) && department.equals(profileObj.getDepartment()) && dateHired.compareTo(profileObj.getDateHired()) == 0) {
            return true;
        }
        return false;
    } //compare name, department and dateHired
}