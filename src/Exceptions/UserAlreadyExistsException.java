package Exceptions;
/** 
 * Exception to indicate that the user account that the current user is trying to register as already exists
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("User already exists in the database!");
    }
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
