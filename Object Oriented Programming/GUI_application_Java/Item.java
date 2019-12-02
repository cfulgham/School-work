package assign2;

import java.io.Serializable;


/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * Abstract Class Item
 * @author 
 */
public abstract class Item /*implements Serializable*/{

    /**
	 * default constructor provides no user input validation. 
	 * That should be handled by the class that creates an item object.
     *
     * @param idNumber a <b><CODE>String</CODE></b> ID is an alphanumeric string of length 5
     * @param name a <b><CODE>String</CODE></b> Name
     * @param price an <b><CODE>float</CODE></b> Price
     */
    public Item(String idNumber, String name, int quantity, float price) {
        this.idNumber = idNumber;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * @return a <b><CODE>String</CODE></b> that is the ID number of the item.
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @return a <b><CODE>String</CODE></b> that is the item's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return an <b><CODE>int</CODE></b> that is the item's weight
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *  @param quantity a <b><CODE>int</CODE></b> that represents the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

    /**
     * @return a <b><CODE>float</CODE></b> that is the item's price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param c a <b><CODE>Item</CODE></b> object that is used to compare to
     * <b><CODE>this</CODE></b> item. Two orders are equal if their ID is the
     * same.
     * @return the <CODE>boolean</CODE> value of the comparison.
     */
    public boolean equals(Item c) {
        return c.getIdNumber().equals(this.idNumber);
    }

    /**
     * Abstract print method, to be implemented by subclasses of class User.
     * @return
     */
    public abstract String getFormattedText();

    /**
     * Abstract method, to transfer package information to table format
     * @return
     */
    public abstract String[] getJTableEntry();
	
	protected final String idNumber;
    protected final String name;
    protected int quantity;
    protected final float price;

}
