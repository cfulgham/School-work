package assign2;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * SmallHardwareItems extends Item
 * @author 
 */
public class SmallHardwareItems extends assign2.Item {
    protected final String category;

    /**
     * Constructor initializes a customer object with the provided values.
     * @param idNumber
     * @param name
     * @param quantity
     * @param price
     * @param category a <b><CODE>String</CODE></b> that represents the category.
     *                "Door&Window", "Cabinet", "Furniture", "Fasteners", "Structural", "Other".
     */
    public SmallHardwareItems(String idNumber, String name, int quantity, float price, String category) {
        super(idNumber, name, quantity, price);
        this.category = category;
    }

    /**
     * Get the category.
     * @return category
     */
    public String getCategory() {
        return category;
    }
	
	    /**
     * Returns the attributes of the employee, in a formatted text fashion.
     * @return Formatted Text.
     */
    public String getFormattedText() {
		String formatLine = null;
        formatLine = String.format("| %-7s| %-25s| %-10s| %-10s| %-20s| %-30s|%n", this.getIdNumber(), this.getName(), this.getQuantity(), this.getPrice(), "N/A ", category);
        //#FIXME
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
        formatArray[4] = "N/A";
        formatArray[5] = category;


        return formatArray;
    }

    /**
     * Override toString for file writing
     * @return String
     */
    @Override
    public String toString() {

        return this.getIdNumber() + "~" + this.getName() + "~" + this.getCategory() + "~" + this.getQuantity() + "~" + this.getPrice();

    }

}
