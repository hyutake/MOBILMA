package Boundary;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Constants.UserType;
import Exceptions.UserAlreadyExistsException;
import Model.User;
import Service.AccountCreationService;
import Service.Security;
/** 
 * Displays the user interface to get user input for account creation
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 */
public class AccountCreationUI {
    /**
     * User's input for "username"
     */
    private String username;

    /**
     * Hashed user input for "password"
     */
    private String password;    // hashed

    /**
     * User's input for "mobile number"
     */
    private String mobileNumber;

    /**
     * User's input for "email"
     */
    private String email;

    /**
     * Temporary user account created, to be verified before adding to database
     */
    private User user;

    /**
     * Constructor, ability to create an Admin/Customer via Model.{@link User}
     * @param userType
     */
    public AccountCreationUI(UserType userType) {
        System.out.println("=== Account Creation(" + userType + ") ==="); 
        // Getting user inputs
        this.username = setUserStringInfo("username");
        this.password = setPassword();
        this.mobileNumber = setUserStringInfo("mobile number");
        this.email = setUserStringInfo("email");

        // Create user (Admin/Customer)
        this.user = new User(userType, username, password, mobileNumber, email);
        // Add new user account to database
        try {
            AccountCreationService creationService = new AccountCreationService(user);
            System.out.println("Account created successfully!");
            System.out.println("Please log into your new account\n");
        } catch (UserAlreadyExistsException e) {
            System.out.println("Invalid username and password combination!\n");
        }
    }
    
    /** 
     * Returns User information 
     * @return User
     */
    public User returnUser() {
        return user;
    }

    
    /** 
     * Provides a prompt to get String input from the user (username, email, mobile number)
     * @param type      Prompt to be provided 
     * @return String   User input 
     */
    public String setUserStringInfo(String type) {
        Scanner sc = new Scanner(System.in);
        String temp = "";
        do {
            System.out.print("Set " + type + ": ");
            if(sc.hasNext()) {
                temp = sc.next();
            }
        } while(temp == "");
        return temp;
    }

    
    /** 
     * Password module
     * Forces the user to input a satisfactory password based on the displayed requirements before proceeding
     * @return String       Hashed password
     */
    public String setPassword(){
        Scanner sc = new Scanner(System.in);
        String temp = "", token = "";
        // Keep prompting for password until valid one is acquired
        do {
            System.out.println("\n-----Password must contain the folowing-----");
            System.out.println("At least 8 characters and at most 20 characters");
            System.out.println("At least one digit");
            System.out.println("At least one UPPERCASE and one lowercase alphabet");
            System.out.println("At least one special character which includes @#$%^&+=!");
            System.out.println("No white/blank space allowed\n"); 
            System.out.print("Set password: ");
            if(sc.hasNextLine()) {
                temp = sc.next().trim();
            }
        } while(!Security.isValidPassword(temp));

        // Hash the password
        try {
            token = Security.toHexString(Security.getSHA(temp));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
        return token;
    }
}
