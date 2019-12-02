package assign2;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * Customer is a subclass of User
 * @author 
 */
public class Customer extends assign2.User {

    /**
     * Constructor initializes a customer object with the provided values.
     * @param id
     * @param phoneNumber
     * @param address
     * @param firstName
     * @param lastName
     */
    public Customer(int id, String firstName, String lastName, String phoneNumber, String address) {
       super(id,firstName,lastName);
       this.phoneNumber = phoneNumber;
       this.address = address;

    }

    /**
     * Get the phone number.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Set the phone number.
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Get the address.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the address.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Returns the attributes of the customer, in a formatted text fashion.
     * @return Formatted Text.
     */
    public String getFormattedText() {
		String formatLine = null;

		formatLine = String.format("| %-10s| %-9s| %-12s| %-12s| %-45s|%n", "Customer", this.getId(), this.getFirstName(), this.getLastName(), this.getPhoneNumber() + " " + this.getAddress());

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
        //formatArray[3] = "Customer";
        formatArray[3] = phoneNumber;
        formatArray[4] = address;

        return formatArray;

    }

    /**
     * Override toString for file writing
     * @return String
     */
    @Override
    public String toString(){

        return Integer.toString(this.getId()) + "~" + this.getFirstName() + "~" + this.getLastName() + "~" + this.getPhoneNumber() + "~" + this.getAddress() + "~" + "Customer";

    }
	
	private String phoneNumber;
    private String address;

}
