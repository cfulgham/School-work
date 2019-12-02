package assign2;

import java.io.Serializable;

/**
 * CS3354 Fall 2019 Programming Assignment 2		
 * @author
 */
public abstract class User /*implements Serializable*/{

    /**
     * Constructor initializes a user object with the provided values.
     * @param id User ID
     * @param firstName First Name
     * @param lastName Last Name
     */
    public User(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Get the last name.
     * @return lastName Last Name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name.
     * @param lastName Last Name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the user ID.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Get the first name.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name.
     * @param firstName First Name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
	
	private final int id;
    private String firstName;
    private String lastName;

}
