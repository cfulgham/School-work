package assign2;

import java.io.Serializable;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * Customer is a subclass of User
 * @author 
 */
public class Transaction {
    private final String itemID;
    private final Date saleDate;
    private final int saleQuantity;
    private final int customerId;
    private final int employeeId;

    /**
     * Constructor initializes a SaleTransaction object with the provided values.
     * @param itemID
     * @param saleDate
     * @param saleQuantity
     * @param customerId
     * @param employeeId
     */
    public Transaction(String itemID, Date saleDate, int saleQuantity, int customerId, int employeeId) {
        this.itemID = itemID;
        this.saleDate = saleDate;
        this.saleQuantity = saleQuantity;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }

    /**
     * Get the item ID of this transaction.
     * @return itemID
     */
    public String getItemID() {
        return itemID;
    }

    /**
     * Get the sale date of this transaction.
     * @return saleDate
     */
    public Date getSaleDate() {
        return saleDate;
    }

    /**
     * Get the sale quantity of this transaction.
     * @return saleQuantity
     */
    public int getSaleQuantity() {
        return saleQuantity;
    }

    /**
     * Get the customer ID of this transaction.
     * @return customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Get the employee ID of this transaction.
     * @return
     */
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFormattedText() {
       //implement getFormattedText for Transactions class
        String formattedText = null;

        formattedText = String.format("| %-10s| %-30s| %-10s| %-12s| %-12s|%n", this.getItemID(), this.getSaleDate(), this.getSaleQuantity(), this.getCustomerId(), this.getEmployeeId());

        return formattedText;
    }

    /**
     * Get information to be shown in JTable
     * @return String array of information
     */
    public String[] getJTableEntry() {

        String[] formatArray = new String[5]; //add array of information to be displayed in JTable
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        formatArray[0] = this.getItemID();
        formatArray[1] = dateFormat.format(this.getSaleDate());
        formatArray[2] = Integer.toString(this.getSaleQuantity());
        formatArray[3] = Integer.toString(this.getCustomerId());
        formatArray[4] = Integer.toString(this.getEmployeeId());

        return formatArray;

    }


    /**
     * Override toString for file writing
     * @return String
     */
    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        return this.getItemID() + "~" + dateFormat.format(this.getSaleDate()) + "~" + Integer.toString(this.getSaleQuantity()) + "~" + Integer.toString(this.getCustomerId()) + "~" + Integer.toString(this.getEmployeeId());

    }
}
