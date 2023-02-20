package Boundary;

import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.UserType;
import Controller.SortController;
import Model.Movie;
/** 
 * Displays the user interface to show the sorting/filtering functionalities provided
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-20
 */
public class SortListUI {
    private String sortBy;
    private UserType userAccess = UserType.CUSTOMER;

    /**
     * Constructor to init list UI
     */
    public SortListUI() {
        this.sortBy = getListSortBy();
        returnSortedList();
    }

    /**
     * Constructor to init list UI via {@link UserType}
     */
    public SortListUI(UserType u) {
        this.userAccess = u;
        this.sortBy = getListSortBy();
        returnSortedList();
    }

    
    /** 
     * Display the available sorting options and get user input
     * @return String       User's choice for the filter to be applied on the movie list
     */
    public String getListSortBy() {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        Hashtable<Integer, String> options = new Hashtable<>();
        options.put(1, "List all movies (Coming Soon, Now Showing and Preview)");
        options.put(2, "List all movies by overall ratings");
        options.put(3, "List all movies by ticket sales");
        options.put(4, "Exit");


        System.out.println("Under which category would you like to search from?");
        for(int i = 1; i <= options.size(); i++) {
            System.out.println(i + ". " + options.get(i));
        }
        try{
            option = sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("Wrong input");
            sc.next();
        }
        if (option < 1 || option >= 4) return null;
        return options.get(option).replace("List all movies ", " ").trim();
    }


    /**
     * Provide sorted list
     */
    public void returnSortedList() {
        if(sortBy == null) return;
        Scanner sc = new Scanner(System.in);
        SortController sortController = new SortController(sortBy);
        List<Movie> result = sortController.allocateSort();
        if(result == null || result.size() <= 0) {
            System.out.println("=== No movies found ===");
            return;
        }

        System.out.println("=== Movies found ===");
        if(userAccess == UserType.ADMIN) {
            for(int i = 0; i < result.size(); i++) {
                Movie m = result.get(i);
                System.out.println((i + 1) + ". " + m.getTitle() +
                    " (" + m.getOverallRating() + ")" +
                    " [" + m.getTicketSales() + "]");
            }
        }
        else {
            // Printing only the titles by default 
            if(sortBy.equals("by overall ratings") || sortBy.equals("by ticket sales")) {
                for(int i = 0; i < 5; i++) {        // TOP 5
                    Movie m = result.get(i);
                    System.out.println((i + 1) + ". " + m.getTitle() +
                        " (" + m.getOverallRating() + ")");
                }
                // Print full details
                System.out.println("Show full details for each movie? Y/N");
                char c = sc.nextLine().charAt(0);
                if(Character.toLowerCase(c) == 'y') {
                    for(int i = 0; i < 5; i++) {
                        System.out.println(result.get(i).toString());
                    }
                }
            }
            else {
                for(int i = 0; i < result.size(); i++) {        // All available
                    Movie m = result.get(i);
                    System.out.println((i + 1) + ". " + m.getTitle() +
                        " (" + m.getOverallRating() + ")");
                }

                System.out.println("Show full details for each movie? Y/N");
                char c = sc.nextLine().charAt(0);
                if(Character.toLowerCase(c) == 'y') {
                    for(int i = 0; i < result.size(); i++) {
                        System.out.println(result.get(i).toString());
                    }
                }
            }
        }
    }
}
