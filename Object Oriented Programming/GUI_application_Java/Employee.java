package assign2;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * Employee is a subclass of User
 * @author
 */
public class Employee extends assign2.User {

    /**
     * Constructor initializes an employee object with the provided values.
     * @param id
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param monthlySalary
     */
    public Employee(int id, String firstName, String lastName, int socialSecurityNumber, float monthlySalary) {
        super(id,firstName,lastName);
        this.socialSecurityNumber = socialSecurityNumber;
        this.monthlySalary = monthlySalary;

    }

    /**
     * Get the SSN.
     * @return socialSecurityNumber
     */
    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Set the SSN.
     * @param socialSecurityNumber
     */
    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    /**
     * Get the monthly salary.
     * @return monthlySalary
     */
    public float getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * Set the monthly salary.
     * @param monthlySalary
     */
    public void setMonthlySalary(float monthlySalary) {
        this.monthlySalary = monthlySalary;
    }


    /**
     * Returns the attributes of the employee, in a formatted text fashion.
     * @return Formatted Text.
     */
    public String getFormattedText() {
		String formatLine = null;
		// edit formatLine so it returns correct formatted text here
        formatLine = String.format("| %-10s| %-9s| %-12s| %-12s| %-45s|%n", "Employee", this.getId(), this.getFirstName(), this.getLastName(), this.getSocialSecurityNumber() + " " + this.getMonthlySalary());

        return formatLine;
    }

    /**
     * Get information to be shown in JTable
     * @return String array of information
     */
    public String[] getJTableEntry() {

		String[] formatArray = new String[5]; //add array of information to be displayed in JTable

        formatArray[0] = Integer.toString(this.getId());
        formatArray[1] = this.getFirstName();
        formatArray[2] = this.getLastName();
        //formatArray[3] = "Employee";
        formatArray[3] = Integer.toString(socialSecurityNumber);
        formatArray[4] = Float.toString(monthlySalary);

        return formatArray;

    }

    /**
     * Override toString for file writing
     * @return String
     */
    @Override
    public String toString(){

        return Integer.toString(this.getId()) + "~" + this.getFirstName() + "~" + this.getLastName() + "~"
                + Integer.toString(this.getSocialSecurityNumber()) + "~" + Float.toString(this.getMonthlySalary()) + "~" + "Employee";

    }
		
    private int socialSecurityNumber;
    private float monthlySalary;
	
}
