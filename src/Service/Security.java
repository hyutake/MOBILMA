package Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/** 
 * Provides the security services needed to ensure that the passwords were properly protected
 * @author  Don Lim
 * @version 1.0
 * @since   2022-10-17
 */
public class Security {
     /*
    Password security pattern validation
    SHA-256 encryption
     */

    /** To verify that the input password follows the set security requierments
     * @param password  The password value that was input by the user
     * @return boolean  Returns true/false for whether the password fulfills the security requirements or not
     */
    public static boolean isValidPassword(String password){
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=!])"
                        + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) return false;

        // Pattern claAatcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    /**
     * To get a byte array of the hashed value of the password
     * @param input     The password that has been verified to contain all the necessary characters
     * @return byte[]   Returns the byte array of the hashed value
     * @throws NoSuchAlgorithmException     MessageDigest.getInstance throws this error and this will be handled later on by the caller method/class
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
        
    /**
     * Converts the byte array to a String of hexadecimal values
     * @param hash      Byte array of the hashed value, acquired from getSha(String input)
     * @return String   Returns the hashed value in String format (that is in hexadecimal)
     */
    public static String toHexString(byte[] hash){
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
