package assign2;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;


import assign2.Appliances;
import assign2.Item;
import assign2.SmallHardwareItems;
import assign2.Customer;
import assign2.Employee;
import assign2.User;
import assign2.Transaction;


/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * HardwareStore item management class 
 * @author 
 */
public class HardwareStore {

    /**
     * This constructor creates an empty ArrayList and then calls the 
     * <CODE>readDatabase()</CODE> method to populate items previously stored. 
     *
     * @throws IOException
     */
    public HardwareStore() throws IOException, ParseException {
        readDatabase();
        sortItemList();
        sortUserList();
    }

    /**
     * Method getAllItemsFormatted returns the current list of items in the Arraylist in
     * no particular order.
     * 
     * @return a formatted String representation of all the items in itemList.
     */
    public String getAllItemsFormatted() {
        return getFormattedItemList(itemList);
    }

    /**
     * Private method getFormattedItemList used as an auxiliary method to return a given ArrayList
     * of items in a formatted manner.
     *
     * @param items the item list to be displayed.
     * @return a formatted String representation of all the items in the list give as a parameter.
     */
    private String getFormattedItemList(ArrayList<Item> items) {

        String text = " ------------------------------------------------------------------------------------------------------------------\n" +
                String.format("| %-8s| %-25s| %-10s| %-10s| %-20s| %-30s|%n", "Item ID", "Name", "Quantity", "Price", "Item Type", "Category / Brand and type") +
                      " ------------------------------------------------------------------------------------------------------------------\n";

        for (Item item : items) {
           text += item.getFormattedText();
           text += " ------------------------------------------------------------------------------------------------------------------\n";
        }
        //text += " ------------------------------------------------------------------------------------------------------------------\n";

        return text;
    }

    /**
     * Method getAllItemsFormatted returns the current list of users in the Arraylist in
     * no particular order.
     *
     * @return a formatted String representation of all the users in userList.
     */
    public String getAllUsersFormatted() {
        return getFormattedUserList(userList);
    }

    /**
     * Private method getFormattedUserList used as an auxiliary method to return a given ArrayList
     * of users in a formatted manner.
     *
     * @param users the user list to be displayed.
     * @return a formatted String representation of all the users in the list give as a parameter.
     */
    private String getFormattedUserList(ArrayList<User> users) {

        String text = " -------------------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-9s| %-12s| %-12s| %-45s|%n", "User Type", "User ID", "First Name", "Last Name", "Special") +
                " -------------------------------------------------------------------------------------------------\n";

        for (User user : users) {
            text += user.getFormattedText();
            text += " -------------------------------------------------------------------------------------------------\n";
        }
        //text += " -------------------------------------------------------------------------------------------------\n";

        return text;
    }

    /**
     * Method getAllTransactionsFormatted returns the current list of transactions in the Arraylist in
     * no particular order.
     *
     * @return a formatted String representation of all the transactions in transactionList.
     */
    public String getAllTransactionsFormatted() {
        return getFormattedTransactionList(transactionList);
    }

    /**
     * Private method getFormattedTransactionList used as an auxiliary method to return a given ArrayList
     * of items in a formatted manner.
     *
     * @param transactions the transaction list to be displayed.
     * @return a formatted String representation of all the items in the list give as a parameter.
     */
    private String getFormattedTransactionList(ArrayList<Transaction> transactions) {

        String text = " -----------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-30s| %-10s| %-12s| %-12s|%n", "Item ID", "Date", "Quantity", "Customer ID", "Employee ID") +
                " -----------------------------------------------------------------------------------\n";

        for (Transaction transaction : transactions) {
            text += transaction.getFormattedText();
            text += " -----------------------------------------------------------------------------------\n";
        }
        //text += " -----------------------------------------------------------------------------------\n";

        return text;
    }



    /**
     * This method is used to add a small hardware item to the itemList ArrayList.
     *
     * @param idNumber a <CODE>String</CODE> representing the ID number of item
     * @param name a <CODE>String</CODE> representing the name of item
     * @param quantity an <CODE>int</CODE> representing the quantity of item
     * @param price a <CODE>float</CODE> representing the price of item
     * @param category a <CODE>String</CODE> representing the category of item
     */
    public String addNewSmallHardwareItem(String idNumber, String name, int quantity, float price, String category) {
        //If passed all the checks, add the item to the list

        String result;

        if ((!idNumber.contains("~")) && (!name.contains("~")) && (!category.contains("~"))) {

            // if quantity is negative set to 0
            if (quantity < 0){

                quantity = 0;

            }
            itemList.add(new SmallHardwareItems(idNumber, name, quantity, price, category));
            sortItemList();
            result = "New small hardware added to database.";
            System.out.println("New small hardware item has been added.");
        }
        else{

            result = "Invalid character '~' used.";

        }
        return result;
    }

    /**
     * This method is used to add an appliance to the itemList ArrayList.
     *
     * @param idNumber a <CODE>String</CODE> representing the ID number of item
     * @param name a <CODE>String</CODE> representing the name of item
     * @param quantity an <CODE>int</CODE> representing the quantity of item
     * @param price a <CODE>float</CODE> representing the price of item
     * @param brand a <CODE>String</CODE> representing the brand of item
     * @param type a <CODE>String</CODE> representing the type of item
     */
    public String addNewAppliance(String idNumber, String name, int quantity, float price, String brand, String type) {
        //If passed all the checks, add the item to the list

        String result;

        if ((!idNumber.contains("~")) && (!name.contains("~")) && (!brand.contains("~")) && (!type.contains("~"))) {

            // if quantity is negative set to 0
            if (quantity < 0){

                quantity = 0;

            }
            itemList.add(new Appliances(idNumber, name, quantity, price, brand, type));
            sortItemList();
            result = "New appliance added to database.";
            System.out.println("New appliance has been added.");
        }
        else{

            result = "Invalid character '~' used.";

        }

        return result;

    }


    /**
     * @param firstName a <CODE>String</CODE> representing the first name of user
     * @param lastName a <CODE>String</CODE> representing the last name of user
     * @param phoneNumber a <CODE>String</CODE> representing the telephone number of user
     * @param address a <CODE>String</CODE> representing the address of user
     */
    public String addCustomer(String firstName, String lastName, String phoneNumber, String address) {

        String result;

        if ((!firstName.contains("~")) && (!lastName.contains("~")) && (!phoneNumber.contains("~")) && (!address.contains("~"))){

            userList.add(new Customer(++userIdCounter, firstName, lastName, phoneNumber, address));
            sortUserList();
            result = "New customer added to database.";
            System.out.println("New customer has been added.");

        }
        else{

            result = "Invalid character '~' used.";
        }

        return result;
    }

    /**
     * @param firstName a <CODE>String</CODE> representing the first name of user
     * @param lastName a <CODE>String</CODE> representing the last name of user
     * @param ssn an <CODE>int</CODE> representing the ssn of user
     * @param monthlySalary a <CODE>float</CODE> representing the monthly salary of user
     */
    public String addEmployee(String firstName, String lastName, int ssn, float monthlySalary) {

        String result;

        if ((!firstName.contains("~")) && (!lastName.contains("~"))) {

            userList.add(new Employee(++userIdCounter, firstName, lastName, ssn, monthlySalary));
            sortUserList();
            result = "New employee added to database.";
            System.out.println("New employee has been added.");
        }
        else{

            result = "Invalid character '~' used.";

        }

        return result;

    }


    /**
     * Add a certain quantity of the given item index.
     * Preconditions: 1. Item exists.
     * @param itemIndex the index of the item in the itemList
     * @param quantity  the quantity to add
     */
    public void addQuantity(int itemIndex, int quantity) {
        //implement addQuantity here

        int currQuantity = itemList.get(itemIndex).getQuantity();

        currQuantity += quantity;

        itemList.get(itemIndex).setQuantity(currQuantity);

        System.out.println("Quantity updated.");
    }

    /**
     * Removes a certain quantity of the given item index. 
     * Preconditions: 1. Item exists. 2. Quantity to remove smaller than current quantity.
     * @param itemIndex the index of the item in the itemList
     * @param quantity  the quantity to remove
     */
    public void removeQuantity(int itemIndex, int quantity) {
        //implement remove quantity here

        int currQuantity = itemList.get(itemIndex).getQuantity();

        currQuantity += quantity;

        //Set to 0 if more than available are removed.
        if (currQuantity < 0){

            currQuantity = 0;
        }

        itemList.get(itemIndex).setQuantity(currQuantity);

        System.out.println("Quantity updated.\n");
    }

    /**
     * Returns all the items that (partially) match the given name.
     * @param name the name to match.
     * @return a string containing a table of the matching items.
     */
    public String[][] getMatchingItemsByName(String name) {
		int match = 0;

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {

                ++match;

            }

        }
        String[][] items = new String[match][6];
        int index = 0;

        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {

                items[index] = itemList.get(i).getJTableEntry();
                ++index;

            }

        }

		return items;
    }

    /**
     * Returns all the items with current quantity lower than (or equal) the
     * given threshold.
     * @param quantity the quantity threshold.
     * @return a string containing a table of the matching items.
     */
    public String getMatchingItemsByQuantity(int quantity) {
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item tempItem : itemList) {
            if (tempItem.getQuantity() <= quantity) {
                temp.add(tempItem);
            }
        }
        
        if (temp.isEmpty()) {
            return null;
        } else {
            return getFormattedItemList(temp);
        }
    }

    /**
     * This method can be used to find a item in the Arraylist of items.
     *
     * @param id a <CODE>String</CODE> that represents the ID of
     * the item that to be searched for.
     * @return the <CODE>Item</CODE> in the Arraylist of
     * items, or null if the search failed.
     */
    public Item findItem(String id) {
        Item item = null;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getIdNumber().equals(id)) {
                item = itemList.get(i);
                break;
            }

        }
        return item;
    }


    /**
     * This method can be used to find a item in the Arraylist of items.
     *
     * @param idNumber a <CODE>String</CODE> that represents the ID number of
     * the item that to be searched for.
     * @return the <CODE>int</CODE> index of the items in the Arraylist of
     * items, or -1 if the search failed.
     */
    public int findItemIndex(String idNumber) {
        int index = -1;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getIdNumber().equals(idNumber)) {
                index = i;
                break;
            }

        }
        return index;
    }

    /**
     * This method can be used to find a user in the Arraylist of users.
     *
     * @param id an <CODE>int</CODE> that represents the ID of
     * the user that to be searched for.
     * @return the <CODE>User</CODE> in the Arraylist of
     * users, or null if the search failed.
     */
    public User findUser(int id) {
        User user = null;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                user = userList.get(i);
                break;
            }

        }
        return user;
    }


    /**
     * This method can be used to find the index of user in the Arraylist of users.
     *
     * @param id a <CODE>String</CODE> that represents the ID of
     * the user that to be searched for.
     * @return the <CODE>int</CODE> index of the user in the Arraylist of
     * users, or -1 if the search failed.
     */
    public int findUserIndex(int id) {
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId() == id) {
                index = i;
                break;
            }

        }
        return index;
    }
    /**
     * This method will edit information of an customer.
     * It will first remove the old entry and add a new one with same user ID
     * @param idInput the <CODE>int</CODE> index of the user's ID
     * @param firstName a <CODE>String</CODE> representing the first name of user
     * @param lastName a <CODE>String</CODE> representing the last name of user
     * @param phoneNumber a <CODE>String</CODE> representing the telephone number of user
     * @param address a <CODE>String</CODE> representing the address of user
     */
    public String editCustomerInformation(int idInput, String firstName, String lastName, String phoneNumber, String address) {

        String result;

        if ((!firstName.contains("~")) && (!lastName.contains("~")) && (!phoneNumber.contains("~")) && (!address.contains("~"))){

            userList.remove(findUserIndex(idInput));
            userList.add(new Customer(idInput, firstName, lastName, phoneNumber, address));
            sortUserList();
            result = "User information changed.";
            System.out.println("Customer information updated.");

        }
        else{

            result = "Invalid character '~' used.";

        }

        return result;

    }


    /**
     * This method will edit information of an employee.
     * It will first remove the old entry and add a new one with same user ID
     * @param idInput the <CODE>int</CODE> index of the user's ID
     * @param firstName a <CODE>String</CODE> representing the first name of user
     * @param lastName a <CODE>String</CODE> representing the last name of user
     * @param socialSecurityNumber an <CODE>int</CODE> representing the ssn of user
     * @param monthlySalary a <CODE>float</CODE> representing the monthly salary of user
     */
    public String editEmployeeInformation(int idInput, String firstName, String lastName, int socialSecurityNumber, float monthlySalary) {

        String result;

        if ((!firstName.contains("~")) && (!lastName.contains("~"))) {

            userList.remove(findUserIndex(idInput));
            userList.add(new Employee(idInput, firstName, lastName, socialSecurityNumber, monthlySalary));
            sortUserList();
            result = "User information changed.";
            System.out.println("Employee information updated.");

        }
        else{

            result = "Invalid character '~' used.";

        }

        return result;

    }

    /**
     * This method can be used to remove a item in the Arraylist of items.
     *
     * @param itemIndex an <CODE>int</CODE> that represents the Index of
     * the item in the list that to be removed.
     */
    public void removeItem(int itemIndex) {
        itemList.remove(itemIndex);
    }

    /**
     * This method is used to retrieve the Item object from the
     * <CODE>itemList</CODE> at a given index.
     *
     * @param i the index of the desired <CODE>Item</CODE> object.
     * @return the <CODE>Item</CODE> object at the index or null if the index is
     * invalid.
     */
    public Item getItem(int i) {
        if (i < itemList.size() && i >= 0) {
            return itemList.get(i);
        } else {
            System.out.println("Invalid Index.");
            return null;
        }
    }

    /**
     * This method will add a transaction to the list, and remove the quantity for the target item.
     * @param itemId a <CODE>String</CODE> representing the ID of item
     * @param saleQuantity an <CODE>int</CODE> of the quantity
     * @param customerId an <CODE>int</CODE> representing the ID of customer
     * @param employeeId an <CODE>int</CODE> representing the ID of employee
     * @param itemIndex an <CODE>int</CODE> representing the index of item in the list, used to reduce the quantity.
     * @return String with result of transaction attempt.
     */
    public String processTransaction(String itemId, int saleQuantity, int customerId, int employeeId, int itemIndex) {

        String resultOfTransaction = null;

        if ( (this.findItem(itemId) != null) && (this.findUser(customerId) != null)  && (this.findUser(employeeId) != null)){

            Item item = this.findItem(itemId);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            if ( (item.getQuantity() >= saleQuantity) && (saleQuantity > 0) ){

                this.removeQuantity(itemIndex, (saleQuantity * -1));
                transactionList.add(new Transaction( itemId, date, saleQuantity, customerId, employeeId));
                resultOfTransaction = "Sale complete.";

            }
            else if (saleQuantity < 0){

                resultOfTransaction = "Quantity entered is negative.";
                System.out.println("Quantity entered is negative.");

            }
            else{

                resultOfTransaction = "Inventory quantity of specified Item too low. No sale completed.";
                System.out.println("Inventory quantity of specified Item too low. No sale completed.");

            }
        }
        else{

            if(this.findItem(itemId) != null){

                resultOfTransaction = "Item not found. Please enter item before attempting transaction.";
                System.out.println("Item not found. Please enter item before attempting transaction.");

            }else if(this.findUser(customerId) != null){

                resultOfTransaction = "Customer not found. Please enter Customer before attempting transaction.";
                System.out.println("Customer not found. Please enter Customer before attempting transaction.");

            }else if(this.findUser(employeeId) != null){

                resultOfTransaction ="Employee not found. Please enter Employee before attempting transaction.";
                System.out.println("Employee not found. Please enter Employee before attempting transaction.");

            }


        }

        return resultOfTransaction;

    }

    /**
     * This method opens the database file and overwrites it with a
     * serialized representation of all the items in the <CODE>itemList</CODE>,
     * all users in the <CODE>userList</CODE>, all transactions in the
     * <CODE>transactionList</CODE>, and the <CODE>userIdCounter</CODE>.
     * This should be the last method to be called before exiting the program.
     *
     * @throws IOException
     */
    public void writeDatabase() throws IOException {
        //write all data to database.txt

        File dataFile = new File(DATA_FILE_NAME);
        FileWriter fileStream = new FileWriter(DATA_FILE_NAME);
        BufferedWriter outFS = new BufferedWriter(fileStream);

        for(int i = 0; i < itemList.size(); i++) {

            outFS.write(itemList.get(i).toString() + "\n");

        }

        outFS.write("~~~~~~~~~~~~~~\n");

        for(int i = 0; i < userList.size(); i++) {

            outFS.write(userList.get(i).toString() + "\n");

        }

        outFS.write("~~~~~~~~~~~~~~\n");

        for(int i = 0; i < transactionList.size(); i++) {

            outFS.write(transactionList.get(i).toString() + "\n");

        }

        outFS.write("~~~~~~~~~~~~~~\n");

        outFS.write(Integer.toString(this.userIdCounter));

        outFS.flush();
        fileStream.close();
        System.out.println("Write complete.");
    }

    /**
     * The method opens the database file and initializes the <CODE>itemList</CODE>,
     * <CODE>userList</CODE>, <CODE>transactionList</CODE> and the <CODE>userIdCounter</CODE>
     * with their contents. If no such file exists, then one is created.
     * The contents of the file are "loaded" into the itemList ArrayList in no 
     * particular order. The file is then closed during the duration of the 
     * program until <CODE>writeDatabase()</CODE> is called.
     *
     * @throws IOException
     */
    public void readDatabase() throws IOException, ParseException {

        int deliniation = 0;

        System.out.println("Reading database...");

        File dataFile = new File(DATA_FILE_NAME);

        // Try to read existing hardware store database from a file
        FileInputStream fileByteStream = new FileInputStream( DATA_FILE_NAME);
        Scanner inFS = new Scanner(fileByteStream);
        
            if (!dataFile.exists()) {
                System.out.println("Data file does not exist. Creating a new database.");
                itemList = new ArrayList<Item>();
                userList = new ArrayList<User>();
                transactionList = new ArrayList<Transaction>();
                userIdCounter = 1;
                return;
            }
            else{
                itemList = new ArrayList<Item>();
                userList = new ArrayList<User>();
                transactionList = new ArrayList<Transaction>();
                userIdCounter = 1;

                while( inFS.hasNext()){

                    String tempLineHolder = inFS.nextLine();
                    if (tempLineHolder.equals("~~~~~~~~~~~~~~")){ //Check for delination between data types.
                        ++deliniation;
                        continue;
                    }
                    String[] tempLineArray = tempLineHolder.split("~");

                    switch (deliniation) {

                        case 0: // Read Items
                            String id = tempLineArray[0];
                            String name = tempLineArray[1];
                            String category = tempLineArray[2];
                            int quantity = Integer.parseInt(tempLineArray[3]);
                            float price = Float.parseFloat(tempLineArray[4]);


                            if (category.contains("Door") || category.contains("Window") || category.contains("Cabinet")
                                || category.contains("Furniture") || category.contains("Fasteners")
                                || category.contains("Structural") || category.contains("Other")) {

                                itemList.add(new SmallHardwareItems(id, name, quantity, price, category));

                            }
                            if (category.contains("Refrigerator") || category.contains("Washer") || category.contains("Dryer")
                                || category.contains("Ranges") || category.contains("Ovens")
                                || category.contains("Small Appliance")) {

                                String brand = tempLineArray[5];
                                itemList.add(new Appliances(id, name, quantity, price, brand, category));

                            }
                            break;

                        case 1: // Read Users

                            id = tempLineArray[0];
                            String firstName = tempLineArray[1];
                            String lastName = tempLineArray[2];

                            if (tempLineArray[5].equals("Customer")){

                                String phone = tempLineArray[3];
                                String address = tempLineArray[4];
                                userList.add(new Customer(Integer.parseInt(id), firstName, lastName, phone, address));

                            }
                            else if(tempLineArray[5].equals("Employee")){

                                int ssn = Integer.parseInt(tempLineArray[3]);
                                float salary = Float.parseFloat(tempLineArray[4]);
                                userList.add(new Employee(Integer.parseInt(id), firstName, lastName, ssn, salary));
                            }

                            break;

                        case 2: // Read Transactions

                            id = tempLineArray[0];
                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(tempLineArray[1]);
                            quantity = Integer.parseInt(tempLineArray[2]);
                            int cusID = Integer.parseInt(tempLineArray[3]);
                            int empID = Integer.parseInt(tempLineArray[4]);

                            transactionList.add(new Transaction(id,date,quantity,cusID,empID));

                            break;

                        case 3: // Set user counter

                            userIdCounter = Integer.parseInt(tempLineArray[0]);

                    }

                }

                System.out.println("Read complete.");


            }
			

        fileByteStream.close();
        System.out.println("File closed");
    }

    /**
     * Auxiliary convenience method used to close a file and handle possible
     * exceptions that may occur.
     *
     * @param c
     */
    public static void close(Closeable c) {
        if (c == null) {
            return;
        }
        try {
            c.close();
        } catch (IOException ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     * This method will sort the item list, based on the item ID.
     */
    public static void sortItemList() {
        Collections.sort(itemList, new Comparator<Item>(){

                public int compare(Item o1, Item o2){

                    return o1.getIdNumber().compareTo(o2.getIdNumber());

                  }
                }
		 );

    }
    /**
     * This method will sort the user list, based on the user ID.
     */
    public static void sortUserList() {

        Collections.sort(userList, new Comparator<User>(){

                    public int compare(User o1, User o2){

                        return o1.getId() - o2.getId();

                    }
                }
        );

    }


    /**
     * Format entries for JTable as String[][]
     * @param name
     * @return
     */
    public String[][] formatJTableEntries(String name) {
        //format itemList into an array of arrays, formatted for JTable entry
        String[][] arr = new String[itemList.size()][6];

        return arr;
    }

    /**
     * Get all item information in format of String[][]
     * @return information of packages
     */
    public String[][] getJTableEntries() {
        String[][] arr = new String[itemList.size()][6];
        for(int i = 0; i < itemList.size(); i++) {
            arr[i] = itemList.get(i).getJTableEntry();
        }
        return arr;
    }

    public String getItemList(){

        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item tempItem : itemList) {
                temp.add(tempItem);
        }

        if (temp.isEmpty()) {
            return null;
        } else {
            return getFormattedItemList(temp);
        }

    }

    /**
     * Get all transaction information in format of String[][]
     * @return information of packages
     */
    public String[][] transactionListInfoTable() {
        String[][] arr = new String[transactionList.size()][6];
        for(int i = 0; i < transactionList.size(); i++) {
            arr[i] = transactionList.get(i).getJTableEntry();
        }
        return arr;
    }


    /**
     * Get all user information in format of String[][]
     * @return information of users
     */
    public String[][] userListInfoTable() {
        String[][] arr = new String[userList.size()][5];
        for(int i = 0; i < userList.size(); i++) {
            arr[i] = userList.get(i).getJTableEntry();
        }
        return arr;
    }
	
	private static final String DATA_FILE_NAME = "database.txt";
	private static ArrayList<Item> itemList;
    private static ArrayList<User> userList;
    private static ArrayList<Transaction> transactionList;
    private static int userIdCounter = 0;
	
}
