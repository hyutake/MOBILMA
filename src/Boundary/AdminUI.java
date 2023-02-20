package Boundary;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.CineplexLocation;
import Constants.UserType;
import Controller.FileAccessController;
import Controller.FileAccess.CineplexFileAccess;
import Controller.FileAccess.HolidayFileAccess;
import Controller.FileAccess.MovieFileAccess;
import Controller.FileAccess.PriceFileAccess;
import Controller.FileAccess.UserFileAccess;
import Service.Admin.AdminMovieService;
/** 
 * Displays the user interface to show all the available functionalities provided for Admin users
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-10-24
 */
public class AdminUI {
    /**
     * Create a list for file accessors
     */
    private List<FileAccessController> fileAccessors = new ArrayList<>();

    /**
     * Create object of movie service
     */
    private AdminMovieService movieService = new AdminMovieService();

    /**
     * Constructor to init AdminUI
     */
    public AdminUI() {
        verifyDBFiles();
        adminApp();
    }

    /**
     * Verify that each database file exists
     */
    public void verifyDBFiles() {
        fileAccessors.add(new MovieFileAccess());
        fileAccessors.add(new HolidayFileAccess());
        fileAccessors.add(new PriceFileAccess());
        fileAccessors.add(new UserFileAccess());
        fileAccessors.add(new CineplexFileAccess(CineplexLocation.JURONG_EAST));
        fileAccessors.add(new CineplexFileAccess(CineplexLocation.PUNGGOL));
        fileAccessors.add(new CineplexFileAccess(CineplexLocation.ORCHARD));
    }

    /**
     * Interface for admin to configure setting
     */
    public void adminApp() {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try{
                System.out.println("\n---Add/Update/Remove/View Movie(s)---");
                System.out.println("1. Add movie listing");
                System.out.println("2. Update movie listing");
                System.out.println("3. Remove movie listing");
                System.out.println("4. View movie listing");
                System.out.println("5. List movies\n");
    
                System.out.println("---Assignment/showtime listing---");
                System.out.println("6. Update movie sessions\n");
    
                System.out.println("---Pricing & Holiday---");
                System.out.println("7. Update pricing");
                System.out.println("8. Add holiday dates");
                System.out.println("9. Delete holiday dates");
                System.out.println("10. View holiday dates\n");

                System.out.println("---Account creation---");
                System.out.println("11. Create new Admin account\n");
    
                System.out.println("12. Exit");
    
                option = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
                    
            switch(option) {
                case 1:
                    movieService.addMovie();
                    break;
                case 2:
                    movieService.updateMovie();
                    break;
                case 3:
                    movieService.removeMovie();
                    break;
                case 4:
                    movieService.viewMovie();
                    break;
                case 5:     
                    movieService.listMovie();
                    break;
                case 6:     // add movie session
                    AdminSessionUI sessionUI = new AdminSessionUI();
                    break;
                case 7:     // update pricing
                    AdminPriceUI priceUI = new AdminPriceUI();
                    break;
                case 8:
                    System.out.println("Please enter date in this format: dd-MM-yyyy");
                    HolidayUI.setHolidayDate(sc.next());
                    break;
                case 9:
                    System.out.println("Please enter date in this format: dd-MM-yyyy");
                    HolidayUI.deleteHolidayDate(sc.next());
                    break;
                case 10:
                    HolidayUI.getHoliday();
                    break;
                case 11:
                    AccountCreationUI newAdminAcc = new AccountCreationUI(UserType.ADMIN);
                    break;
                default:
                    System.out.println("Exiting . . .");
            }
        } while(option >= 0 && option < 12);
    }
}