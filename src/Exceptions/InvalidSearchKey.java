package Exceptions;
/** 
 * Exception to indicate that the input search key was invalid 
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-17
 */
public class InvalidSearchKey extends Exception {
    public InvalidSearchKey() {
        super("Invalid search key!");
    }
    public InvalidSearchKey(String message) {
        super(message);
    }
}
