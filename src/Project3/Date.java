package Project3;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * The Date class is used to represent dates in the format mm/dd/yyyy.
 *
 * It also contains methods to validate a given date, and compare dates to each other.
 * @author Srija Gottiparthi, Catherine Nguyen
 */

public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    /**
     * Initializes a new default Date object with the current (today's) date.
     */
    public Date() {
        Calendar today = Calendar.getInstance();
        this.year = today.get(Calendar.YEAR);
        this.month = today.get(Calendar.MONTH);
        this.day = today.get(Calendar.DAY_OF_MONTH);
    } //create an object with today’s date (see Calendar class)

    /**
     * Initializes a new Date object with the given date.
     * @param date the date given as a string in "mm/dd/yyyy" format
     */
    public Date(String date) {
        StringTokenizer split = new StringTokenizer(date, "/");
        int [] dateNumbers = new int[3];
        int index = 0;
        while (split.hasMoreTokens()) {
            dateNumbers[index] = Integer.parseInt(split.nextToken());
            index++;
        }
        this.year = dateNumbers[2];
        this.month = dateNumbers[0];
        this.day = dateNumbers[1];
    } //taking mm/dd/yyyy and create a Date object

    /**
     * Returns the Date's year.
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the Date's month.
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Returns the Date's day.
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * Checks if a date is valid.
     * A date is valid if it is after 1900, on or before the current date, and meets all other common requirements of a date.
     * (e.g. the month existing, leap years being handled correctly, etc.)
     * @return true if the date is valid, false otherwise
     */
    public boolean isValid() {
        Calendar currentDate = Calendar.getInstance();
        int currentYear = currentDate.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH);
        int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);
        int earliestValidYear = 1900;
        if (year < earliestValidYear) {
            return false;
        }
        if (year > currentYear) {
            return false;
        }
        else if (year == currentYear) {
            if (month-1 > currentMonth) {
                return false;
            }
            else if (month-1 == currentMonth) {
                if (day > currentDay) {
                    return false;
                }
            }
        }
        int numMonths = 12;
        if (month > numMonths) {
            return false;
        }
        Calendar bookDate = Calendar.getInstance();
        bookDate.set(year, month - 1, 1); // ?
        int numberOfDays = bookDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (day > numberOfDays || day < 1) {
            return false;
        }
        return true;
    }

    /**
     * Compares current Date object to another Date object.
     * If the current Date object occurs later in the future than the given Date object, 1 is returned.
     * If the current Date object occurs earlier in the future than the given Date object, -1 is returned.
     * If both Dates are the same, 0 is returned.
     * @param date the date to compare to
     * @return -1 if the current Date object is earlier, 1 if the current Date is later, 0 if equal
     */
    @Override
    public int compareTo(Date date) {
        if (year > date.getYear()) {
            return 1;
        }
        else if (year < date.getYear()) {
            return -1;
        }
        else {
            if (month > date.getMonth()) {
                return 1;
            }
            else if (month < date.getMonth()) {
                return -1;
            }
            else {
                if (day > date.getDay()) {
                    return 1;
                }
                else if (day < date.getDay()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    /**
     * Returns a string representation of the date.
     * @return a string representation of the date
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Testbed main to verify that the isValid() method is functioning correctly.
     * @param args the given arguments
     */
    public static void main(String[] args) {
        Date date1 = new Date("01/00/2021");
        if (date1.isValid()) {
            System.out.println(date1.month + "/" + date1.day + "/" + date1.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date1.month + "/" + date1.day + "/" + date1.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date2 = new Date("01/01/1899");
        if (date2.isValid()) {
            System.out.println(date2.month + "/" + date2.day + "/" + date2.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date2.month + "/" + date2.day + "/" + date2.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date3 = new Date("01/01/1900");
        if (date3.isValid()) {
            System.out.println(date3.month + "/" + date3.day + "/" + date3.year +
                    " is valid (expected result: is valid)");
        }
        else {
            System.out.println(date3.month + "/" + date3.day + "/" + date3.year +
                    " is not valid (expected result: is valid)");
        }
        Date date4 = new Date("01/15/2021");
        if (date4.isValid()) {
            System.out.println(date4.month + "/" + date4.day + "/" + date4.year +
                    " is valid (expected result: is valid)");
        }
        else {
            System.out.println(date4.month + "/" + date4.day + "/" + date4.year +
                    " is not valid (expected result: is valid)");
        }
        Date date5 = new Date("01/32/2020");
        if (date5.isValid()) {
            System.out.println(date5.month + "/" + date5.day + "/" + date5.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date5.month + "/" + date5.day + "/" + date5.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date6 = new Date("02/29/2020");
        if (date6.isValid()) {
            System.out.println(date6.month + "/" + date6.day + "/" + date6.year +
                    " is valid (expected result: is valid)");
        }
        else {
            System.out.println(date6.month + "/" + date6.day + "/" + date6.year +
                    " is not valid (expected result: is valid)");
        }
        Date date7 = new Date("02/29/2019");
        if (date7.isValid()) {
            System.out.println(date7.month + "/" + date7.day + "/" + date7.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date7.month + "/" + date7.day + "/" + date7.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date8 = new Date("02/29/1900");
        if (date8.isValid()) {
            System.out.println(date8.month + "/" + date8.day + "/" + date8.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date8.month + "/" + date8.day + "/" + date8.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date9 = new Date("01/01/2022");
        if (date9.isValid()) {
            System.out.println(date9.month + "/" + date9.day + "/" + date9.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date9.month + "/" + date9.day + "/" + date9.year +
                    " is not valid (expected result: is not valid)");
        }
        Date date10 = new Date("31/01/2000");
        if (date10.isValid()) {
            System.out.println(date10.month + "/" + date10.day + "/" + date10.year +
                    " is valid (expected result: is not valid)");
        }
        else {
            System.out.println(date10.month + "/" + date10.day + "/" + date10.year +
                    " is not valid (expected result: is not valid)");
        }
    }
}
