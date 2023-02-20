package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Constants.UserType;
import Controller.FileAccess.UserFileAccess;
import Model.User;
/** 
 * Displays the user interface to show all the available functionalities provided for Customers as well as 'Guests'
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-11-01
 */
public class UserUI {
    public User currentUser;

    /**
     * Constructor for User UI with refrence to {@link User}
     * @param user
     */
    public UserUI(User user) {     // "user" will only be invalid if the actual user does not login and chooses NOT to create an account when prompted
        currentUser = user;
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do {
            if(user.getUserType() != UserType.INVALID) 
                System.out.println("\nWelcome, " + user.getUsername());    // welcome message
            System.out.println("=== Movie-goer Menu ===");
            System.out.println("1. Search for a movie");
            System.out.println("2. List movies");
            System.out.println("3. Book movie ticket"); 
            System.out.println("4. Show booking history");
            System.out.println("5. Leave review");
            System.out.println("6. Exit");
            try {
                option = sc.nextInt();
                    
                switch(option) {
                    case 1:
                        SearchUI searchUI = new SearchUI();
                        break;
                    case 2:
                        SortListUI listUI = new SortListUI();
                        break;
                    case 3:
                        if(user.getUserType() != UserType.INVALID){
                            BookingUI bookUI = new BookingUI(currentUser);
                        }
                        else
                            System.out.println("Please exit menu and login first");
                        break;
                    case 4:
                        if(user.getUserType() != UserType.INVALID){
                            System.out.println(currentUser.viewBookingHistory());
                        }
                        else
                            System.out.println("Please exit menu and login first");
                        break;
                    case 5:
                        if(user.getUserType() != UserType.INVALID){
                            CustomerReviewUI reviewUI = new CustomerReviewUI(currentUser);
                        }
                        else
                            System.out.println("Please exit menu and login first");
                        break;
                    default:
                        System.out.println("Exiting . . .");
                }
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
            currentUser = refreshUser(user);
        } while(option > 0 && option < 6);
    }

    
    /** 
     * Look for User u in UserDatabase and re-assign it to currentUser
     * @param u
     * @return User
     */
    public User refreshUser(User u) {
        UserFileAccess userAccess = new UserFileAccess();
        User updatedUser = userAccess.findUser(u);
        return new User(updatedUser.getUserType(), updatedUser.getUsername(), updatedUser.getPassword(),
             updatedUser.getMobileNumber(), updatedUser.getEmail(), updatedUser.getPastBookings());
    }
}
