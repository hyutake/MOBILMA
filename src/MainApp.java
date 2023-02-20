import java.util.InputMismatchException;
import java.util.Scanner;

import Boundary.AccountCreationUI;
import Boundary.HolidayUI;
import Boundary.LoginUI;
import Boundary.UserUI;
import Constants.UserType;
import Controller.FileAccessController;
import Controller.TimeDateController;
import Controller.FileAccess.UserFileAccess;
import Model.User;

/**
 @author    Don Lim
 @verison   1.1
 @since     01-11-2022
 */
public class MainApp {
    
    /** 
     * Entry point for program to run
     * @param args
     */
    public static void main(String[] args) {

        int option= 0;
        Scanner sc = new Scanner(System.in);

        if(!FileAccessController.verifyFile(UserFileAccess.fileName)){
            System.out.println("Please run setup to initialized database");
            System.exit(0);
        }

        System.out.println("---Please wait syncing database to time---");
        HolidayUI.syncHoliday();
        System.out.println("---VERIFYING---");
        HolidayUI.syncHoliday();
        System.out.println("---COMPLETED---");
        System.out.print("\n\n\n");
        do {
            option = 0;     // reset option so that wrong user input will work properly
            try{
                System.out.println("-----Welcome to MOBILMA-----");
                System.out.println("Date: "+ TimeDateController.getDate()+ " Time: "+ TimeDateController.getTime());                
                System.out.println("----------------------------");
                System.out.println("Choose the following option:");
                System.out.println("1. User login");
                System.out.println("2. Customer account creation");
                System.out.println("----------------------------");
                System.out.println("3. View movies");
                System.out.println("4. Exit");
                option = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
            switch(option){
                case 1:
                    new LoginUI();
                    break;
                case 2:
                    new AccountCreationUI(UserType.CUSTOMER);
                    break;           
                case 3:
                    new UserUI(new User());
                    break;
                default:
                    System.out.println("Restarting...");
            }
        }while(option >= 0 && option < 4);
    }
}
