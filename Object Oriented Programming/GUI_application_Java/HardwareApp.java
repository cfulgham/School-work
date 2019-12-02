package assign2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.*;
import assign2.HardwareStore;
import assign2.Item;


/**
 * Hardware Store database manager. 
 * @author 
 */
public class HardwareApp {


	/** Run the app on UI thread 
	  * @param args array of string arguments 
	*/
	public static void main(String[] args) throws IOException {

        try {
            hardwareStore = new HardwareStore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    /**
     * This method initialize the top panel, which is the commands using a ComboBox
     */
    private static void createTopPanel() {

        comboBox.setBounds(250,20,500,20);
        comboBox.addItem("Please select...");
        comboBox.addItem(" 1. Show all existing items records in the database (sorted by ID number).");
        comboBox.addItem(" 2. Add new item (or quantity) to the database.");
        comboBox.addItem(" 3. Delete an item from a database.");
        comboBox.addItem(" 4. Search for an item given its name.");
        comboBox.addItem(" 5. Show a list of users in the database.");
        comboBox.addItem(" 6. Add new user to the database.");
        comboBox.addItem(" 7. Update user info (given their id).");
        comboBox.addItem(" 8. Complete a sale transaction.");
        comboBox.addItem(" 9. Show completed sale transactions.");
        comboBox.addItem("10. Exit program.");
        comboBox.setSelectedIndex(0);

        topPanel.setBounds(10, 60, 960, 435);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel message = new JLabel("WELCOME TO THE HARDWARE STORE.");
        //message.setBounds(20, 80, 40, 20);
        topPanel.add(message);
        topPanel.setVisible(true);

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int s = comboBox.getSelectedIndex();

                switch (s) {

                    case 0:
                        topPanel.removeAll();
                        topPanel.revalidate();
                        topPanel.repaint();
                        message.setText("Please make a selection above.");
                        topPanel.add(message);
                        topPanel.revalidate();
                        topPanel.repaint();
                        break;
                    case 1:
                        showAllItems();
                        break;
                    case 2:
                        addItemQuantity();
                        break;
                    case 3:
                        removeItem();
                        break;
                    case 4:
                        searchItemByName();
                        break;
                    case 5:
                        showAllUsers();
                        break;
                    case 6:
                        addUser();
                        break;
                    case 7:
                        editUser();
                        break;
                    case 8:
                        finishTransaction();
                        break;
                    case 9:
                        showAllTransactions();
                        break;
                    case 10:
                        try {
                            saveDatabase();
                            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                        }
                        catch(Exception e) {
                            System.out.print(e);
                        }
                    }
                }
        });
    }

    /**
     * This method initialize the bottom panel, which is the output area.
     * Just a TextArea that not editable.
     */
    private static void createBottomPanel() {

        bottomPanel.setBounds(10, 505, 960, 435);
        bottomText.setBounds(20, 565, 700, 350);
        bottomText.setEditable(false);
        bottomPanel.add(bottomText);

        bottomPanel.setVisible(true);


    }

    /**
     * Initialize the JFrame and JPanels, and show them.
     * Also set the location to the middle of the monitor.
     */
    private static void createAndShowGUI() {
        //implement



        createTopPanel();
        createBottomPanel();

        f.add(comboBox);
        f.add(topPanel);
        f.add(bottomPanel);

        f.setLayout(null);
        f.setVisible(true);
        f.setSize(1000,1000);
        f.setLocation(width/2-f.getSize().width/2, height/2-f.getSize().height/2);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);

        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e){

                try {
                    hardwareStore.writeDatabase();
                }
                catch (IOException err){

                    System.err.println(err.toString());

                }

            }
        });


    }

    //Function 1
    /**
     * This method shows all items in the inventory.
     */
    public static void showAllItems() {

        clearPanels();

        String[] columnNames = {"Item ID", "Name", "Quantity", "Price", "Item Type", "Category / Brand"};

        JTable itemTable = new JTable(hardwareStore.getJTableEntries(), columnNames);
        itemTable.setBounds(20, 600, 10000, 435);
        itemTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(600);
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        itemTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        itemTable.getColumnModel().getColumn(4).setPreferredWidth(300);
        itemTable.getColumnModel().getColumn(5).setPreferredWidth(600);


        JScrollPane scrollPane = new JScrollPane(itemTable);

        itemTable.setFillsViewportHeight(true);

        bottomPanel.add(scrollPane);
        bottomPanel.revalidate();
        bottomPanel.repaint();
        
    }

    //Function 2
    /**
     * This method will add a new item
     *
     */
    public static void addNewItem(int selection, GridBagConstraints gbc){


        clearPanels();
        categoryField.removeAllItems();

        //Name
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(nameLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(nameField, gbc);

        //Price
        JLabel priceLabel = new JLabel("Price:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        topPanel.add(priceLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 4;
        topPanel.add(priceField, gbc);

        //Add Appliances
        if ( selection == 0) {

            //Brand
            JLabel brandLabel = new JLabel("Brand:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 5;
            topPanel.add(brandLabel, gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 5;
            topPanel.add(brandField, gbc);

            // Type
            JLabel typeLabel = new JLabel("Type:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 6;
            topPanel.add(typeLabel, gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 6;
            categoryField.addItem(" 1. Refrigerator");
            categoryField.addItem(" 2. Washer");
            categoryField.addItem(" 3. Dryer");
            categoryField.addItem(" 4. Ranges");
            categoryField.addItem(" 5. Ovens");
            categoryField.addItem(" 6. Small Appliance");
            categoryField.setSelectedIndex(0);
            topPanel.add(categoryField, gbc);

            //Submit
            JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    String name = nameField.getText();
                    String brand = brandField.getText();
                    String type = null;
                    float price = Float.parseFloat(priceField.getText());
                    int selection = categoryField.getSelectedIndex();

                    switch (selection) {

                        case 0:
                            type = "Refrigerator";
                            break;
                        case 1:
                            type = "Washer";
                            break;
                        case 2:
                            type = "Dryer";
                            break;
                        case 3:
                            type = "Ranges";
                            break;
                        case 4:
                            type = "Ovens";
                            break;
                        case 5:
                            type = "Small Appliance";
                            break;

                    }

                    String result = hardwareStore.addNewAppliance(idField.getText(), name, Integer.parseInt(quantityField.getText()), price, brand, type);
                    topPanel.removeAll();
                    topPanel.revalidate();
                    topPanel.repaint();
                    addItemQuantity();

                }
            });
            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 7;
            topPanel.add(submit, gbc);

        }

        //Adding Small Hardware
        if (selection == 1) {

            //Category
            JLabel categoryLabel = new JLabel("Category:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 5;
            topPanel.add(categoryLabel, gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 5;
            categoryField.addItem(" 1. Door");
            categoryField.addItem(" 2. Window");
            categoryField.addItem(" 3. Cabinet");
            categoryField.addItem(" 4. Furniture");
            categoryField.addItem(" 5. Fasteners");
            categoryField.addItem(" 6. Structural");
            categoryField.addItem(" 7. Other");
            categoryField.setSelectedIndex(0);
            topPanel.add(categoryField, gbc);

            //Submit
            JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    String name = nameField.getText();
                    String category = null;
                    int selection = categoryField.getSelectedIndex();

                    switch (selection) {

                        case 0:
                            category = "Door";
                            break;
                        case 1:
                            category = "Window";
                            break;
                        case 2:
                            category = "Cabinet";
                            break;
                        case 3:
                            category = "Furniture";
                            break;
                        case 4:
                            category = "Fasteners";
                            break;
                        case 5:
                            category = "Structural";
                            break;
                        case 6:
                            category = "Other";
                            break;

                    }

                    float price = Float.parseFloat(priceField.getText());
                    String result = hardwareStore.addNewSmallHardwareItem(idField.getText(),name,Integer.parseInt(quantityField.getText()), price, category);
                    topPanel.removeAll();
                    topPanel.revalidate();
                    topPanel.repaint();
                    addItemQuantity();


                }
            });
            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 7;
            topPanel.add(submit, gbc);
        }

        bottomText.setText("Item add to Database.");
        bottomPanel.add(bottomText);
        bottomPanel.revalidate();
        bottomPanel.repaint();

        topPanel.revalidate();
        topPanel.repaint();

    }

    /**
     * This method will add items quantity with given number. If the item does
     * not exist, it will call another method to add it.
     *
     */
    public static void addItemQuantity() {

        //Reset all Fields and panels
        clearAllFields();
        clearPanels();

        //Layout
        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        //Instruction
        JLabel instruction = new JLabel("Please enter ID and quantity to add or remove. Negative quantities will be removed.");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(instruction, gbc);

        // ID
        JLabel idLabel = new JLabel("Item ID: ");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(idLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        // limit idField to 5 characters
        idField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (idField.getText().length() >= 5 )
                    e.consume();
            }
        });
        topPanel.add(idField, gbc);

        // Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(quantityLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(quantityField, gbc);

        // Submit
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String id = idField.getText();
                int quantity = Integer.parseInt(quantityField.getText());


                if ( hardwareStore.findItem(id) != null){ //Check if Item ID is in Database

                    if ( quantity >= 0 ){ //Add Quantity

                        int index = hardwareStore.findItemIndex(id);
                        hardwareStore.addQuantity( index, quantity );
                        bottomText.setText( "Quantity updated." );
                        bottomPanel.add(bottomText);
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                    } else if ( quantity < 0 ) { //Subtract Quantity

                        int index = hardwareStore.findItemIndex(id);
                        hardwareStore.removeQuantity( index, quantity );
                        bottomText.setText( "Quantity updated." );
                        bottomPanel.add(bottomText);
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                    }


                }
                else{  //Item Id not in Database converting to add new Item

                    bottomText.setText("Item not found please enter information about new item.");
                    bottomPanel.add(bottomText);
                    bottomPanel.revalidate();
                    bottomPanel.repaint();

                    topPanel.removeAll();
                    topPanel.revalidate();
                    topPanel.repaint();


                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    JLabel radioLabel = new JLabel("Select an Item Type: ");
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    topPanel.add(radioLabel,gbc);

                    JRadioButton radioButton1 = new JRadioButton();
                    radioButton1.setText("Appliance");
                    radioButton1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            addNewItem(0,gbc);
                        }
                    });
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    topPanel.add(radioButton1, gbc);

                    JRadioButton radioButton2 = new JRadioButton();
                    radioButton2.setText("Small Hardware");
                    radioButton2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            addNewItem(1,gbc);
                        }
                    });
                    gbc.gridx = 1;
                    gbc.gridy = 2;
                    topPanel.add(radioButton2, gbc);

                    ButtonGroup group = new ButtonGroup();
                    group.add(radioButton1);
                    group.add(radioButton2);

                    topPanel.revalidate();
                    topPanel.repaint();

                }

            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        topPanel.add(submit, gbc);

       
    }

    //Function 3
    /**
     * This method will remove the item with given ID.
     * If the item does not exist, it will show an appropriate message.
     */
    public static void removeItem() {

        clearAllFields();
        clearPanels();

        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        JLabel instruction = new JLabel("Please enter ID of item to remove.");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(instruction, gbc);

        JLabel idLabel = new JLabel("Item ID: ");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(idLabel, gbc);


        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        idField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (idField.getText().length() >= 5 ) // limit textfield to 3 characters
                    e.consume();
            }
        });
        topPanel.add(idField, gbc);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String id = idField.getText();
                int index = hardwareStore.findItemIndex(id);

                if ( index != -1 ){

                    hardwareStore.removeItem(index);
                    bottomText.setText("Item removed.");
                    bottomPanel.add(bottomText);
                    bottomPanel.revalidate();
                    bottomPanel.repaint();

                }
                else{

                    bottomText.setText("Item not found, no action taken.");
                    bottomPanel.add(bottomText);
                    bottomPanel.revalidate();
                    bottomPanel.repaint();

                }

            }

        });

        gbc.gridwidth = 2;
        gbc.gridy = 1;
        gbc.gridx = 2;
        topPanel.add(submit,gbc);
 
    }

    //Function 4
    /**
     * This method can search item by a given name (part of name.
     * Case-insensitive.) Will display all items with the given name.
     *
     */
    public static void searchItemByName() {
		//implement

        clearAllFields();
        topPanel.removeAll();
        topPanel.revalidate();
        topPanel.repaint();

        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        JLabel nameLabel = new JLabel("Name:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(nameLabel, gbc);


        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(nameField, gbc);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name = nameField.getText();

                bottomPanel.removeAll();
                bottomPanel.revalidate();
                bottomPanel.repaint();

                if (hardwareStore.getMatchingItemsByName(name).length != 0){

                    String[] columnNames = {"Item ID", "Name", "Quantity", "Price", "Item Type", "Category / Brand"};

                    JTable itemTable = new JTable(hardwareStore.getMatchingItemsByName(name), columnNames);
                    itemTable.setBounds(20, 600, 10000, 435);
                    itemTable.getColumnModel().getColumn(0).setPreferredWidth(200);
                    itemTable.getColumnModel().getColumn(1).setPreferredWidth(600);
                    itemTable.getColumnModel().getColumn(2).setPreferredWidth(200);
                    itemTable.getColumnModel().getColumn(3).setPreferredWidth(200);
                    itemTable.getColumnModel().getColumn(4).setPreferredWidth(300);
                    itemTable.getColumnModel().getColumn(5).setPreferredWidth(600);


                    JScrollPane scrollPane = new JScrollPane(itemTable);

                    itemTable.setFillsViewportHeight(true);

                    bottomPanel.add(scrollPane);


                }
                else{

                    bottomText.setText("Item not found.");
                    bottomPanel.add(bottomText);

                }

                bottomPanel.revalidate();
                bottomPanel.repaint();

            }

        });

        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        topPanel.add(search, gbc);

        topPanel.revalidate();
        topPanel.repaint();


    }

    //Function 5
    /**
     * This method shows all users in the system.
     *
     *     public String[][] userListInfoTable() {
     */
    public static void showAllUsers() {

        clearPanels();

        topPanel.setLayout(new GridBagLayout());
        String[] columnNames = {"User ID", "First Name", "Last Name", "SSN/Phone", "Salary/Address"};

        JTable itemTable = new JTable(hardwareStore.userListInfoTable(), columnNames);
        itemTable.setBounds(20, 600, 10000, 435);
        itemTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        itemTable.getColumnModel().getColumn(1).setPreferredWidth(200);
        itemTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        itemTable.getColumnModel().getColumn(3).setPreferredWidth(400);
        itemTable.getColumnModel().getColumn(4).setPreferredWidth(600);


        JScrollPane scrollPane = new JScrollPane(itemTable);
        itemTable.setFillsViewportHeight(true);

        bottomPanel.add(scrollPane);
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    //Function 6
    /**
     * This method adds fields to addUser
     */
    public static void addFieldsAddUser(int selection, GridBagConstraints gbc){


        clearPanels();

        // First Name
        JLabel fNameLabel = new JLabel("First Name:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 4;
        topPanel.add(fNameLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 4;
        topPanel.add(fNameField, gbc);

        // Last Name
        JLabel lNameLabel = new JLabel("Last Name");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 5;
        topPanel.add(lNameLabel, gbc);


        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 5;
        topPanel.add(lNameField, gbc);

        if (selection == 0) {

            // Phone Number
            JLabel phoneLabel = new JLabel("Phone Number:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 6;
            topPanel.add(phoneLabel, gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 6;
            // Limit to 10 digits
            phoneField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (phoneField.getText().length() >= 10 )
                        e.consume();
                }
            });
            topPanel.add(phoneField, gbc);

            //Address
            JLabel addressLabel = new JLabel("Address:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 7;
            topPanel.add(addressLabel, gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 7;
            topPanel.add(addressField, gbc);

            //Submit
            JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try {
                        String result;
                        String firstName = fNameField.getText();
                        String lastName = lNameField.getText();
                        String number = phoneField.getText();
                        String address = addressField.getText();

                        result = hardwareStore.addCustomer(firstName, lastName, number, address);
                        bottomText.setText(result);

                        topPanel.removeAll();
                        topPanel.revalidate();
                        topPanel.repaint();
                        addUser();

                    }
                    catch(NumberFormatException e) {

                        bottomText.setText("Invalid input.");
                        bottomPanel.add(bottomText);
                        clearAllFields();

                        topPanel.removeAll();
                        topPanel.revalidate();
                        topPanel.repaint();
                        addUser();

                    }

                }
            });
            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 8;
            topPanel.add(submit, gbc);

        }
        if (selection == 1) {

            // Social Security
            JLabel ssnLabel = new JLabel("SSN:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 6;
            topPanel.add(ssnLabel,gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 6;
            // Limit to 9 digits
            ssnField.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (ssnField.getText().length() >= 9 )
                        e.consume();
                }
            });
            topPanel.add(ssnField,gbc);

            // Salary
            JLabel salaryLabel = new JLabel("Salary:");
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 7;
            topPanel.add(salaryLabel,gbc);


            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 7;
            topPanel.add(salaryField,gbc);

            // Submit
            JButton submit = new JButton("Submit");
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {

                    try {
                        String result;
                        String firstName = fNameField.getText();
                        String lastName = lNameField.getText();
                        int ssn = Integer.parseInt(ssnField.getText());
                        float salary = Float.parseFloat(salaryField.getText());

                        result = hardwareStore.addEmployee(firstName, lastName, ssn, salary);
                        bottomText.setText(result);
                        bottomPanel.add(bottomText);
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                        topPanel.removeAll();
                        topPanel.revalidate();
                        topPanel.repaint();

                        addUser();
                    }
                    catch(NumberFormatException e) {

                        bottomText.setText("Invalid SSN or Salary entered. Please ensure the field only contains numbers.");
                        bottomPanel.add(bottomText);
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                        topPanel.removeAll();
                        topPanel.revalidate();
                        topPanel.repaint();

                        addUser();

                    }

                }
            });
            gbc.gridwidth = 2;
            gbc.gridx = 1;
            gbc.gridy = 8;
            topPanel.add(submit, gbc);

        }


        topPanel.revalidate();
        topPanel.repaint();

    }

    /**
     * This method will add a user.
     */
    public static void addUser(){
        //implement

        clearPanels();
        clearAllFields();
        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        // User Type
        JLabel radioLabel = new JLabel("Select a User Type:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(radioLabel,gbc);

        JRadioButton radioButton1 = new JRadioButton();
        radioButton1.setText("Customer");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                addFieldsAddUser(0,gbc);

            }
        });
        topPanel.add(radioButton1, gbc);

        JRadioButton radioButton2 = new JRadioButton();
        radioButton2.setText("Employee");
        gbc.gridx = 0;
        gbc.gridy = 2;
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

               addFieldsAddUser(1,gbc);
            }
        });
        topPanel.add(radioButton2, gbc);

        ButtonGroup group = new ButtonGroup();
        group.add(radioButton1);
        group.add(radioButton2);

        topPanel.revalidate();
        topPanel.repaint();

    }

    //Function 7
    /**
     * This method will edit a user (customer or employee).
     *
     */
    public static void editUser() {
		//implement

        clearAllFields();
        clearPanels();

        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        // ID
        JLabel userIDLabel = new JLabel("User ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(userIDLabel,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(idField, gbc);

        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {

                    String id = idField.getText();
                    int idInt = Integer.parseInt(id);

                    if (hardwareStore.findUser(idInt) != null){

                        clearPanels();

                        if (hardwareStore.findUser(idInt) instanceof assign2.Employee){

                            // First Name
                            JLabel fNameLabel = new JLabel("First Name:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            topPanel.add(fNameLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 0;
                            topPanel.add(fNameField, gbc);

                            // Last Name
                            JLabel lNameLabel = new JLabel("Last Name");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 1;
                            topPanel.add(lNameLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 1;
                            topPanel.add(lNameField, gbc);

                            // Social Security
                            JLabel ssnLabel = new JLabel("SSN:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 2;
                            topPanel.add(ssnLabel,gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 2;
                            // Limit to 9 digits
                            ssnField.addKeyListener(new KeyAdapter() {
                                public void keyTyped(KeyEvent e) {
                                    if (ssnField.getText().length() >= 9 )
                                        e.consume();
                                }
                            });
                            topPanel.add(ssnField,gbc);

                            // Salary
                            JLabel salaryLabel = new JLabel("Salary:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 3;
                            topPanel.add(salaryLabel,gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 3;
                            topPanel.add(salaryField,gbc);

                            // Submit
                            JButton submit = new JButton("Submit");
                            submit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {

                                    try {

                                        String firstName = fNameField.getText();
                                        String lastName = lNameField.getText();
                                        int ssn = Integer.parseInt(ssnField.getText());
                                        int id = Integer.parseInt(idField.getText());
                                        float salary = Float.parseFloat(salaryField.getText());

                                        String result = hardwareStore.editEmployeeInformation(id, firstName, lastName, ssn, salary);
                                        bottomText.setText(result);
                                        bottomPanel.add(bottomText);
                                        bottomPanel.revalidate();
                                        bottomPanel.repaint();

                                        topPanel.removeAll();
                                        topPanel.revalidate();
                                        topPanel.repaint();

                                        editUser();
                                    }
                                    catch(NumberFormatException e) {

                                        bottomText.setText("Invalid SSN or Salary entered. Please ensure the field only contains numbers.");
                                        bottomPanel.add(bottomText);
                                        bottomPanel.revalidate();
                                        bottomPanel.repaint();

                                        topPanel.removeAll();
                                        topPanel.revalidate();
                                        topPanel.repaint();

                                        editUser();

                                    }

                                }
                            });

                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 8;
                            topPanel.add(submit, gbc);

                            topPanel.revalidate();
                            topPanel.repaint();

                        }
                        if(hardwareStore.findUser(idInt) instanceof assign2.Customer){

                            // First Name
                            JLabel fNameLabel = new JLabel("First Name:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 4;
                            topPanel.add(fNameLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 4;
                            topPanel.add(fNameField, gbc);

                            // Last Name
                            JLabel lNameLabel = new JLabel("Last Name");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 5;
                            topPanel.add(lNameLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 5;
                            topPanel.add(lNameField, gbc);

                            // Phone Number
                            JLabel phoneLabel = new JLabel("Phone Number:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 6;
                            topPanel.add(phoneLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 6;
                            phoneField.addKeyListener(new KeyAdapter() {
                                public void keyTyped(KeyEvent e) {
                                    if (phoneField.getText().length() >= 10 )
                                        e.consume();
                                }
                            });
                            topPanel.add(phoneField, gbc);

                            //Address
                            JLabel addressLabel = new JLabel("Address:");
                            gbc.gridwidth = 1;
                            gbc.gridx = 0;
                            gbc.gridy = 7;
                            topPanel.add(addressLabel, gbc);


                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 7;
                            topPanel.add(addressField, gbc);

                            //Submit
                            JButton submit = new JButton("Submit");
                            submit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {

                                    String result;

                                    try {
                                        int id = Integer.parseInt(idField.getText());
                                        String firstName = fNameField.getText();
                                        String lastName = lNameField.getText();
                                        String number = phoneField.getText();
                                        String address = addressField.getText();

                                        result = hardwareStore.editCustomerInformation(id, firstName, lastName, number, address);
                                        bottomText.setText(result);
                                        bottomPanel.add(bottomText);
                                        bottomPanel.revalidate();
                                        bottomPanel.repaint();

                                        topPanel.removeAll();
                                        topPanel.revalidate();
                                        topPanel.repaint();
                                        editUser();

                                    }
                                    catch(NumberFormatException e) {

                                        bottomText.setText("Invalid input.");
                                        bottomPanel.add(bottomText);
                                        clearAllFields();

                                        topPanel.removeAll();
                                        topPanel.revalidate();
                                        topPanel.repaint();
                                        editUser();

                                    }

                                }
                            });

                            gbc.gridwidth = 2;
                            gbc.gridx = 1;
                            gbc.gridy = 8;
                            topPanel.add(submit, gbc);

                            topPanel.revalidate();
                            topPanel.repaint();

                        }

                    }
                    else{

                        bottomText.setText("User Id not found.");
                        bottomPanel.add(bottomText);
                        bottomPanel.revalidate();
                        bottomPanel.repaint();

                    }

                }
                catch(NumberFormatException e) {


                }

            }
        });

        gbc.gridwidth = 2;
        gbc.gridx = 2;
        gbc.gridy = 0;
        topPanel.add(submit, gbc);

        topPanel.revalidate();
        topPanel.repaint();


    }

    //Function 8
    /**
     * This method will lead user to complete a transaction.
     */
    public static void finishTransaction(){

        clearAllFields();
        clearPanels();
        topPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipadx = 5;
        gbc.ipady = 10;

        // ItemId
        JLabel itemid = new JLabel("Item ID:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(itemid, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        topPanel.add(itemField, gbc);

        // Quantity
        JLabel quantity = new JLabel("Quantity:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(quantity, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 1;
        topPanel.add(quantityField, gbc);

        // Customer ID
        JLabel cusID = new JLabel("Customer ID:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        topPanel.add(cusID, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 2;
        topPanel.add(cusField, gbc);

        // Employee ID
        JLabel empID = new JLabel("Employee ID:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        topPanel.add(empID, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 3;
        topPanel.add(empField, gbc);

        // Submit
        JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {

                    String itemID = itemField.getText();
                    int cusID = Integer.parseInt(cusField.getText());
                    int empID = Integer.parseInt(empField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    String result;


                    if (!itemID.contains("~")) {

                        int index = hardwareStore.findItemIndex(itemID);

                        result = hardwareStore.processTransaction(itemID, quantity, cusID, empID, index);
                    }else{

                        result = "Texts Contains invalid character '~'.";

                    }

                    bottomText.setText(result);
                    bottomPanel.add(bottomText);
                    bottomPanel.revalidate();
                    bottomPanel.repaint();
                }
                catch(NumberFormatException e) {

                    bottomText.setText("Information entered invalid, please try again.");
                    bottomPanel.add(bottomText);
                    bottomPanel.revalidate();
                    bottomPanel.repaint();

                }

            }
        });
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 4;
        topPanel.add(submit, gbc);

    }

    //Function 9
    /**
     * This method shows all transactions in the system.
     */
    public static void showAllTransactions(){

        clearPanels();

        topPanel.setLayout(new GridBagLayout());
        String[] columnNames = {"Item ID", "Date", "Quantity", "Customer Id", "Employee Id"};

        JTable transactionTable = new JTable(hardwareStore.transactionListInfoTable(), columnNames);
        transactionTable.setBounds(20, 600, 10000, 435);
        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        transactionTable.getColumnModel().getColumn(1).setPreferredWidth(350);
        transactionTable.getColumnModel().getColumn(2).setPreferredWidth(200);
        transactionTable.getColumnModel().getColumn(3).setPreferredWidth(200);
        transactionTable.getColumnModel().getColumn(4).setPreferredWidth(300);


        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBounds(20, 600, 10000, 435);
        transactionTable.setFillsViewportHeight(true);

        bottomPanel.add(scrollPane);
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    //Function 10
    /**
     * These method is called to save the database before exit the system.
     * @throws IOException
     */
    public static void saveDatabase() throws IOException {
		//implement
        hardwareStore.writeDatabase();
    }

    /**
     * This method clears all fields in UI
     */
    private static void clearAllFields(){

        fNameField.setText(null);
        lNameField.setText(null);
        phoneField.setText(null);
        addressField.setText(null);
        ssnField.setText(null);
        salaryField.setText(null);
        idField.setText(null);
        nameField.setText(null);
        quantityField.setText(null);
        priceField.setText(null);
        brandField.setText(null);
        itemField.setText(null);
        cusField.setText(null);
        empField.setText(null);

    }

    /**
     * This method clears the top and bottom panel and refreshes them.
     */
    private static void clearPanels(){

        topPanel.removeAll();
        topPanel.revalidate();
        topPanel.repaint();
        bottomPanel.removeAll();
        bottomPanel.revalidate();
        bottomPanel.repaint();

    }


    //Database
    private static HardwareStore hardwareStore;

    //width and height of the monitor
    private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    //Add components for the layout
	static private final JComboBox comboBox = new JComboBox();
	static private final JComboBox smallHardwareComboBox = new JComboBox();
	static private final JComboBox applianceComboBox = new JComboBox();
	static private final JFrame f = new JFrame("Hardware Store");
	static private final JPanel topPanel = new JPanel();

	static private final JPanel bottomPanel = new JPanel();
    static private final TextArea bottomText = new TextArea(27,132);

    static private final JTextField fNameField = new JTextField(32);
    static private final JTextField lNameField = new JTextField(32);
    static private final JTextField phoneField = new JTextField(32);
    static private final JTextField addressField = new JTextField(32);
    static private final JTextField ssnField = new JTextField(32);
    static private final JTextField salaryField = new JTextField(32);

    static private final JTextField idField = new JTextField(32);
    static private final JTextField nameField = new JTextField(32);
    static private final JTextField quantityField = new JTextField(32);
    static private final JTextField priceField = new JTextField(32);
    static private final JTextField brandField = new JTextField(32);
    static private final JComboBox typeField = new JComboBox();
    static private final JComboBox categoryField = new JComboBox();

    static private final JTextField itemField = new JTextField(32);
    static private final JTextField cusField = new JTextField(32);
    static private final JTextField empField = new JTextField(32);
}

