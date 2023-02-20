package Service.Admin;

import java.util.List;
import java.util.Scanner;

import Constants.AgeGroup;
import Constants.CinemaClass;
import Constants.MovieType;

import java.util.Arrays;
import java.util.EnumMap;

import Controller.PriceController;
import Controller.FileAccess.PriceFileAccess;
import Model.PriceList;
/** 
 * Provides the service for an admin to modify pricing details (hidden to Customers)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class AdminPriceService {
    /**
     * Choice of the Admin user
     */
    private int option;
    /**
     * Contains all the pricing information for each aspect of ticket pricing
     */
    private PriceList prices;
    /**
     * File accessor module for PriceList
     */
    private PriceFileAccess priceAccess;
    private Scanner sc = new Scanner(System.in);

    /**
     * Creats the module that provides the service of actually modifying ticket pricing values
     * @param option    Admin's choice based on the options shown on the console
     */
    public AdminPriceService(int option) {
        priceAccess = new PriceFileAccess();
        prices = priceAccess.getPrices();
        this.option = option;
    }
    /**
     * Updates the price values of a particular aspect depending on the Admin's input
     */
    public void updatePrices() {
        switch(option) {
            case 0:     // hidden case to reset prices
                GeneratePrices.generatePrices();
                break;
            case 1:     // view prices
                viewPrices();
                break;
            case 2:     // update base
                setBasePrice();
                break;
            case 3:     // update ageGroup
                setAgeGroupPrice();
                break;
            case 4:     // update cinemaClass
                setCinemaClassPrice();
                break;
            case 5:     // update movieType
                setMovieTypePrice();
                break;
            case 6:     // update holiday
                setHolidayPrice();
                break;
            case 7:
                testTicketPrice();
        }

        /* Update pricing file */
        if(option == 0) return;     // generatePrices() already overwrites the file, skip the updating of the file
        priceAccess.updatePrices(prices);
    }

    /**
     * Displays all the information relating to the 'current' ticket pricing
     */
    private void viewPrices() {
        System.out.println(prices.toString());
    }

    /**
     * Gets Admin input and updates the base price of the ticket
     */
    private void setBasePrice() {
        System.out.println("Current base price: " + prices.getBasePrice());
        System.out.print("New base price: ");

        try {
            double newBasePrice = sc.nextDouble();
            prices.setBasePrice(newBasePrice);
            return;
        } catch (Exception e) {     // invalid input
            System.out.println(e.getMessage());
        }
        return;
    }

    /**
     * Gets Admin input and updates the age group pricings of the ticket
     */
    private void setAgeGroupPrice() {
        int choice = 0;
        double modifier;
        EnumMap<AgeGroup, Double> ageMap = prices.getAgePricing();

        // Display current config
        System.out.println("Current ageGroup pricing:");
        prices.printEnumMap(ageMap, "ageGroup");
        
        // Display admin choices
        List<AgeGroup> ageGroups = Arrays.asList(AgeGroup.values());
        System.out.println("Change:");
        for(int i = 0; i < ageGroups.size(); i++) {
            System.out.println((i + 1) + ". " + ageGroups.get(i));
        }

        // Exec admin choice
        try {
            choice = sc.nextInt() - 1;  // '___groups' is zero-indexed, but valid options start from 1
            if(choice < 0 || choice > ageGroups.size() - 1) throw new Exception();
            System.out.print("Enter new modifer value: ");
            modifier = sc.nextDouble();
            ageMap.put(ageGroups.get(choice), modifier);    // update Map
            prices.setAgePricing(ageMap);   // update prices
        } catch(Exception e) {      // invalid choice, invalid modifier, invalid choice value -> do nothing
            System.out.println("Invalid input! Exiting...");
        }
    }

    /**
     * Gets Admin input and updates the cinema class pricings of the ticket
     */
    private void setCinemaClassPrice() {
        int choice = 0;
        double modifier;
        EnumMap<CinemaClass, Double> cinemaMap = prices.getCinemaPricing();

        // Display current config
        System.out.println("Current cinemaClass pricing:");
        prices.printEnumMap(cinemaMap, "cinemaClass");
        
        // Display admin choices
        List<CinemaClass> cinemaGroups = Arrays.asList(CinemaClass.values());
        System.out.println("Change:");
        for(int i = 0; i < cinemaGroups.size(); i++) {
            System.out.println((i + 1) + ". " + cinemaGroups.get(i));
        }

        // Exec admin choice
        try {
            choice = sc.nextInt() - 1;  // '___groups' is zero-indexed, but valid options start from 1
            if(choice < 0 || choice > cinemaGroups.size() - 1) throw new Exception();
            System.out.print("Enter new modifer value: ");
            modifier = sc.nextDouble();
            cinemaMap.put(cinemaGroups.get(choice), modifier);    // update Map
            prices.setCinemaPricing(cinemaMap);   // update prices
        } catch(Exception e) {      // invalid choice, invalid modifier, invalid choice value -> do nothing
            System.out.println("Invalid input! Exiting...");
        }
    }

    /**
     * Gets Admin input and updates the movie type pricings of the ticket
     */
    private void setMovieTypePrice() {
        int choice = 0;
        double modifier;
        EnumMap<MovieType, Double> movieTypeMap = prices.getMovieTypePricing();

        // Display current config
        System.out.println("Current movieType pricing:");
        prices.printEnumMap(movieTypeMap, "movieType");
        
        // Display admin choices
        List<MovieType> movieTypeGroups = Arrays.asList(MovieType.values());
        System.out.println("Change:");
        for(int i = 0; i < movieTypeGroups.size(); i++) {
            System.out.println((i + 1) + ". " + movieTypeGroups.get(i));
        }

        // Exec admin choice
        try {
            choice = sc.nextInt() - 1;  // '___groups' is zero-indexed, but valid options start from 1     
            if(choice < 0 || choice > movieTypeGroups.size() - 1) throw new Exception();
            System.out.print("Enter new modifer value: ");
            modifier = sc.nextDouble();
            movieTypeMap.put(movieTypeGroups.get(choice), modifier);    // update Map
            prices.setMovieTypePricing(movieTypeMap);   // update prices
        } catch(Exception e) {      // invalid choice, invalid modifier, invalid choice value -> do nothing
            System.out.println("Invalid input! Exiting...");
        }
    }

    /**
     * Gets Admin input and updates the during holiday/weekend price of the ticket
     */
    private void setHolidayPrice() {
        System.out.println("Current holiday price: " + prices.getHolidayPrice());
        System.out.print("New holiday price: ");

        try {
            double newHolidayPrice = sc.nextDouble();
            prices.setHolidayPrice(newHolidayPrice);
            return;
        } catch (Exception e) {     // invalid input
            System.out.println(e.getMessage());
        }
        return;
    }

    /**
     * Provides a testing interface for Admins to check the final price of any type of ticket
     */
    private void testTicketPrice() {
        int choice;
        // Age Group
        AgeGroup age;
        List<AgeGroup> ageGroups = Arrays.asList(AgeGroup.values());
        System.out.println("Select Age group:");
        for(int i = 0; i < ageGroups.size(); i++) {
            System.out.println((i + 1) + ". " + ageGroups.get(i));
        }
        choice = sc.nextInt() - 1;
        age = ageGroups.get(choice);

        // Cinema Class
        CinemaClass cinClass;
        List<CinemaClass> cinemaClasses = Arrays.asList(CinemaClass.values());
        System.out.println("Select Cinema Class:");
        for(int i = 0; i < cinemaClasses.size(); i++) {
            System.out.println((i + 1) + ". " + cinemaClasses.get(i));
        }
        choice = sc.nextInt() - 1;
        cinClass = cinemaClasses.get(choice);

        // Movie Type
        MovieType mType;
        List<MovieType> movieTypes = Arrays.asList(MovieType.values());
        System.out.println("Select Movie Type:");
        for(int i = 0; i < movieTypes.size(); i++) {
            System.out.println((i + 1) + ". " + movieTypes.get(i));
        }
        choice = sc.nextInt() - 1;
        mType = movieTypes.get(choice);

        // Holiday
        boolean hol;
        System.out.println("Holiday? Y/N");
        char c = sc.next().charAt(0);
        hol = (Character.toLowerCase(c) == 'y') ? true : false;

        PriceController pCtrl = new PriceController(cinClass, mType, age, hol, true);
        System.out.println("Ticket price: " + pCtrl.generateTicketPrice());
    }
}
