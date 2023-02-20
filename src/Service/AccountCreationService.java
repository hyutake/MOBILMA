package Service;

import Constants.UserType;
import Controller.FileAccess.UserFileAccess;
import Exceptions.UserAlreadyExistsException;
import Model.User;
/** 
 * Provides the service to create and add a new user to the database
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class AccountCreationService {
    /**
     * The new User account to be added
     */
    private User newUser;

    /**
     * The main file access module 
     */
    private UserFileAccess userAccess;

    /**
     * Creates the service that validates that the user has entered a unique username and password combination before adding it to the database
     * @param user      A User object that contains all the new user's details
     * @throws UserAlreadyExistsException  
     */
    public AccountCreationService(User user) throws UserAlreadyExistsException {
        this.userAccess = new UserFileAccess();
        this.newUser = userAccess.findUser(user);    // Try and find an existing matching user in the database

        if(newUser.getUserType() == UserType.INVALID) {     // Existing user not found, new user can be added to database
            userAccess.addUser(user);
        } 
        else{
            throw new UserAlreadyExistsException();
        }
    }
}
