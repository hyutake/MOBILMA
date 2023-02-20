package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Service.Admin.AdminPriceService;
import Service.Admin.GeneratePrices;
/** 
 * Displays the user interface to get Admin input for price changes/testing to be made
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class AdminPriceUI {
    private int option;
    /**
     * Handles the logic behind updating any pricing values
     */
    private AdminPriceService priceService;

    /**
     * Creates the AdminPriceUI module, uses Service.{@link AdminPriceService} to help realize the Admin's decisions
     */
    public AdminPriceUI() {
        option = getAdminChoice();
        while(option >= 0 && option < 8) {
            priceService = new AdminPriceService(option);
            priceService.updatePrices();
            option = getAdminChoice();
        }
    }

    /**
     * Generates the default pricing dataset
     */
    public static void initPriceUI() {
        GeneratePrices.generatePrices();
    }

    
    /** 
     * Displays the options available for the Admin to choose from
     * @return int      Option that the Admin picked
     */
    public int getAdminChoice() {
        int choice;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("=== PriceUI ===\n" +
                                "1. View current pricing\n" +
                                "2. Update base price\n" +
                                "3. Update ageGroup price modifiers\n" +
                                "4. Update cinemaClass price modifiers\n" +
                                "5. Update movieType price modifiers\n" +
                                "6. Update holiday/weekend price (flat fee)\n" +
                                "7. Test ticket price\n" +
                                "8. Exit"
                                );
            choice = sc.nextInt();
            return choice;
        }catch(InputMismatchException e){
            System.out.println("Wrong input");
            sc.next();
        }
        return 0;
    }
}
