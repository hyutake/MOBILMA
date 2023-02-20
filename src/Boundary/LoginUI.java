package Boundary;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import Constants.UserType;
import Controller.FileAccess.UserFileAccess;
import Model.User;
import Service.Security;

/** 
 * Shows the user login interface on the console and processes their inputs
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-11-01
 */
public class LoginUI {
    /**
     * Username from user's input
     */
    private String username;
    /**
     * Password from user's input, hashed if the password is valid, blank if not
     */
    private String password;
    /**
     * The user's account (if valid), else a 'guest' account
     */
    private User user;
    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor to init login UI
     */
    public LoginUI() {
        username = getUsername();
        password = getPassword();
        user = checkIfUserExists(new User(username, password));
        if(user.getUserType() != UserType.INVALID) {    // valid user - go to regular/admin 'homepage'
            if(user.getUserType() == UserType.CUSTOMER) {     // regular user UI
                UserUI userUI = new UserUI(user);
            }
            else {   // admin user UI
                AdminUI adminUI = new AdminUI();
            }
        }
        else {  // user does not exist
            System.out.println("Invalid user");
            System.out.println("Would you like to create an account? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {   // create account -> "log in" -> regular user UI
                AccountCreationUI createAcc = new AccountCreationUI(UserType.CUSTOMER);
                user = createAcc.returnUser().copyUser();
                new UserUI(user);
            }
        }
        sc.nextLine();  // clear buffer 
    }

    /** 
     * To save the input username (to be checked against database to see whether user exists or not - match username and password)
     * @return String   Returns the username that the user input
     */
    public String getUsername() {
        System.out.print("Username: ");
        if(sc.hasNext()) {
            return sc.next();
        }
        return "";
    }

    /** 
     * To save the input password (to be checked against database to see whether user exists or not - match username and password)
     * @return String   Returns the username that the user input
     */
    public String getPassword() {
        String temp = "", token = "";
        System.out.print("Password: ");
        if(sc.hasNext()) {
            temp = sc.next();
        }
        // Hashing the password
        if(Security.isValidPassword(temp)) { // only bother getting the hash if password is a valid one, else just return a blank password
            try {
                token = Security.toHexString(Security.getSHA(temp));
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown for incorrect algorithm: " + e);
                return "";
            }
        }
        return token;
    }

    /**
     * To check against the database of users whether the current one (who input the username and password) is an existing user
     * @return  User     Returns a User object which would either match the one used to login, or be of an INVALID type
     */
    public User checkIfUserExists(User u) {
        // Read user data (e.g. UserDatabase.dat) and get a list of users - hopefully sorted with Admins at earlier indexes
        UserFileAccess userAccess = new UserFileAccess();
        return userAccess.findUserLite(u);
    }
}