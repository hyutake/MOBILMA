package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Constants.UserType;

/** 
 * Represents a user of the application
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-11-04
 */
public class User implements Serializable {
    /**
     * Type of user access given
     */
    private UserType userType;

    /**
     * Username of user
     */
    private String username;

    /**
     * Password of user, hashed
     */
    private String password;

    /**
     * Mobile number of user
     */
    private String mobileNumber;

    /**
     * Email of user
     */
    private String email;

    /**
     * Past bookings made by the user
     */
    private List<BookingInfo> pastBookings;

    /**
     * Creates a default "guest" user
     */
    public User() {
        this.userType = UserType.INVALID;
        this.username = "";
        this.password = "";
        this.mobileNumber = "";
        this.email = "";
        this.pastBookings = new ArrayList<>();
    }

    /**
     * Creates a temporary User object to check with the database during login
     * @param username
     * @param password
     */
    public User(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    /**
     * Creates a complete User during account creation
     * @param userType
     * @param username
     * @param password
     * @param mobileNumber
     * @param email
     */
    public User(UserType userType, String username, String password, String mobileNumber, String email) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.pastBookings = new ArrayList<>();
    }

    /**
     * Creates an exact User for copying
     * @param userType
     * @param username
     * @param password
     * @param mobileNumber
     * @param email
     * @param pastBooking
     */
    public User(UserType userType, String username, String password, String mobileNumber, String email, List<BookingInfo> pastBooking) {
        this.userType = userType;
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.pastBookings = pastBooking;
    }

    /**
     * Get the user access type
     * @return UserType     Access type
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Set the user access type
     * @param userType      New access type
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Get the username of the user
     * @return String   Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the username of the user
     * @param username  New username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the password of the user
     * @return String   Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the mobile number of the user
     * @return String   Mobile number
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Set the mobile number of the user
     * @param mobileNumber  New mobile number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Get the email of the user
     * @return String   Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     * @param email     New email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the list of past bookings made by the user
     * @return Model.{@link BookingInfo}    List of bookings made by the user
     */
    public List<BookingInfo> getPastBookings() {
        return pastBookings;
    }

    /**
     * Set the list of past bookings
     * @param pastBookings  New list of bookings made
     */
    public void setPastBookings(List<BookingInfo> pastBookings) {
        this.pastBookings = pastBookings;
    }

    /**
     * Formats the user information for readability when printed - for obvious reasons password is not included
     * @return String       All the pastReviews in String format
     */
    @Override
    public String toString() {
        return "=== User Info ===" + "\nUsername: " + username + "; userType = " + userType + "; mobileNumber = " + mobileNumber + "\nemail = "
                + email + "\n" + viewBookingHistory();
    }

    
    /** 
     * @return User
     */
    public User copyUser() {
        return new User(userType, username, password, mobileNumber, email, pastBookings);
    }

    /**
     * Matches users based on user type, username and password
     * @param u     Other user to be matched to 
     * @return boolean      True/False regarding whether a match is found
     */
    public boolean matchUser(User u) {
        return u.getUserType().equals(userType) &&
            u.getUsername().equals(username) &&
            u.getPassword().equals(password);
    }

    /**
     * Formats the booking history information for readability when printed
     * @return String   Entire booking history of user
     */
    public String viewBookingHistory() {
        StringBuilder st = new StringBuilder();
        st.append("=== Customer Booking History ===\n");
        if(pastBookings.size() == 0) return st.toString();
        for(int i = 0; i < pastBookings.size(); i++) {
            st.append("\n");
            st.append((i + 1) + ". " + pastBookings.get(i).toString());
            st.append("\n");
        }
        return st.toString();
    }  
}
