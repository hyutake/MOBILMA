import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Boundary.AdminUI;
import Boundary.AdminPriceUI;
import Boundary.HolidayUI;
import Controller.FileAccessController;
import Service.Admin.GenerateMovieFile;
import Service.Admin.GeneratePrices;

/**
 @deprecated No Longer in development
 */
public class MobilmaAdminApp {
    static final String movieDatabase = "MovieDatabase.dat";
    static final String HolidayDatabase = "Holiday.dat";
    static final String PriceDatabase = "MoviePricing.dat";
   
    public static void AdminApp() {
        int option = 0;
        Scanner sc = new Scanner(System.in);

        do {
            if(FileAccessController.writeToDatabaseFolder) {
                System.out.println("writeToDatabaseFolder is set to true by default");
                System.out.println("Enter '0' as input to set it to false, to write in current directory");
            }
            try {
                if(FileAccessController.writeToDatabaseFolder){
                    Scanner scanner = new Scanner(new FileInputStream(FileAccessController.Database()+movieDatabase));
                    Scanner scanner2 = new Scanner(new FileInputStream(FileAccessController.Database()+HolidayDatabase));
                    Scanner scanner3 = new Scanner(new FileInputStream(FileAccessController.Database()+PriceDatabase));
                    scanner.close();
                    scanner2.close();
                    scanner3.close();
                }
                else{
                    Scanner scanner = new Scanner(new FileInputStream(movieDatabase));                    
                    Scanner scanner2 = new Scanner(new FileInputStream(HolidayDatabase));
                    Scanner scanner3 = new Scanner(new FileInputStream(PriceDatabase));
                    scanner.close();
                    scanner2.close();
                    scanner3.close();
                }
                do {
                    try{
                        System.out.println("---Add/Update/Remove/View Movie(s)---");
                        System.out.println("1. Add Movie Listing");
                        System.out.println("2. Update Movie Listing");
                        System.out.println("3. Remove Movie Listing");
                        System.out.println("4. View Movie Listings\n");
    
                        System.out.println("---Assignment/showtime listing---");
                        System.out.println("5. Assign movie to cinema hall");
                        System.out.println("6. Assign movie showtime");
                        System.out.println("7. View showtime of cinema hall");
                        System.out.println("8. View cinema hall layout\n");
    
                        System.out.println("---Pricing & Holiday---");
                        System.out.println("9. Update pricing");    // was previously separated as edit and view
                        System.out.println("10. Add holiday dates");
                        System.out.println("11. Delete holiday dates");
                        System.out.println("12. View holiday dates\n");
    
                        System.out.println("13. Exit");
    
                        option = sc.nextInt();
                    }catch(InputMismatchException e){
                        System.out.println("Wrong input");
                        sc.next();
                    }
                    
                    switch(option) {
                        case 0: 
                            // hidden case
                            FileAccessController.writeToDatabaseFolder = !FileAccessController.writeToDatabaseFolder;
                            System.out.println("Toggling writeToDatabaseFolder to " + FileAccessController.writeToDatabaseFolder);
                            System.out.println("System will reboot momentarily!");
                            System.exit(0);
                            break;
                        case 1:
                            AdminUI.addMovie(movieDatabase);
                            break;
                        case 2:
                            AdminUI.updateMovie(movieDatabase);
                            break;
                        case 3:
                            AdminUI.removeMovie(movieDatabase);
                            break;
                        case 4:
                            AdminUI.viewMovie(movieDatabase);
                            break;
                        case 5:     // assign movie to cinema
                            break;
                        case 6:     // assign showtime to movie
                            break;
                        case 7:     // view showtime
                            break;
                        case 8:     // view cinema layout
                            break;
                        case 9:     // update pricing
                            AdminPriceUI priceUI = new AdminPriceUI();
                            break;
                        case 10:
                            System.out.println("Please enter date in this format: dd-MM-yyyy");
                            HolidayUI.setHolidayDate(sc.next());
                            break;
                        case 11:
                            System.out.println("Please enter date in this format: dd-MM-yyyy");
                            HolidayUI.deleteHolidayDate(sc.next());
                            break;
                        case 12:
                            HolidayUI.getHoliday();
                            break;   
                        default:
                            System.out.println("Exiting . . .");
                    }
                } while(option >= 0 && option < 13);
            } catch (FileNotFoundException e) {
                System.out.println("WARNING! Movie & Holiday & Price database not found... Create new database? Y/N");
                char c = sc.next().charAt(0);
                if(Character.toLowerCase(c) == 'y'){
                    GenerateMovieFile.generateMovie();
                    HolidayUI.initHolidayUI();
                    GeneratePrices.generatePrices();
                }
            }         
        } while(option >= 0 && option < 5);
    }
}
