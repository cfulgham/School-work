package assign2;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * Appliances is a subclass of User
 * @author
 */
public class Appliances extends assign2.Item {

    /**
     * Constructor initializes a customer object with the provided values.
     * @param idNumber
     * @param name
     * @param quantity
     * @param price
     * @param brand
     * @param type a <b><CODE>String</CODE></b> that represents the type.
     *                "Refrigerators", "Washers&Dryers", "Ranges&Ovens", "Small Appliance".
     */
    public Appliances(String idNumber, String name, int quantity, float price, String brand, String type) {
        //add constructor here
        super(idNumber,name, quantity, price);
        this.brand = brand;
        this.type = type;

    }

    /**
     * Get the brand.
     * @return brand
     */
    public String getBrand() {
        return brand;
    }


    /**
     * Get the type.
     * @return type
     */
    public String getType() {
        return type;
    }
	
	    /**
     * Returns the attributes of the employee, in a formatted text fashion.
     * @return Formatted Text.
     */
    public String getFormattedText() {
		String formatLine = null;
		// edit formatLine so it returns correct formatted text here
        formatLine = String.format("| %-7s| %-25s| %-10s| %-10s| %-20s| %-30s|%n", this.getIdNumber(), this.getName(), this.getQuantity(), this.getPrice(), brand, type);
        return formatLine;
    }

    /**
     * Get information to be shown in JTable
     * @return String array of information
     */
    public String[] getJTableEntry() {
		String[] formatArray = new String[6]; //add array of information to be displayed in JTable

        formatArray[0] = this.getIdNumber();
        formatArray[1] = this.getName();
        formatArray[2] = Integer.toString(this.getQuantity());
        formatArray[3] = Float.toString(this.getPrice());
        formatArray[4] = brand;
        formatArray[5] = type;

        return formatArray;
    }

    /**
     * Override toString for file writing
     * @return String
     */
    @Override
    public String toString(){

        return this.getIdNumber() + "~" + this.getName() + "~" + this.getType() + "~" + Integer.toString(this.getQuantity()) + "~" + Float.toString(this.getPrice()) + "~" + this.getBrand();

    }
	
	protected final String brand;
    protected final String type;

}
