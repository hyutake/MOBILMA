package Boundary;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.MovieRating;
import Constants.MovieType;
import Controller.SearchController;
import Model.Movie;
/** 
 * Displays the user interface to show the searching functionalities provided
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-16
 */
public class SearchUI {
    /**
     * User's choice of category
     */
    private String searchCategory;

    /**
     * User's choice of search key
     */
    private Object searchKey;

    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor to init Search UI
     */
    public SearchUI() {
        this.searchCategory = getSearchCategory();
        this.searchKey = getSearchKey();
        returnSearchResult();
    }
    
    /** 
     * Displays the available options for search category and takes in user input
     * @return String   Chosen search category
     */
    public String getSearchCategory() {
        int option = 0;
        Hashtable<Integer, String> options = new Hashtable<>();
        options.put(1, "Movie title");
        options.put(2, "Movie type");
        options.put(3, "Movie director");
        options.put(4, "Movie cast");
        options.put(5, "Movie audience rating");
        options.put(6, "Exit");

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
        if (option < 1 || option >= 6) return null;

        String dummy = sc.nextLine();
        return options.get(option);       
    }

    
    /** 
     * Displays the available options (if necessary) for search key and takes in user input
     * @return Object       Encapsulates both the Enums and the String search keys
     */
    public Object getSearchKey() {
        if(searchCategory == null) return null;
        int option = 0;
        System.out.println("Search (" + searchCategory + "): ");

        switch(searchCategory) {    
            case "Movie title":
                return sc.nextLine();
            case "Movie type":
                // force user to enter MovieRating
                List<MovieType> types = Arrays.asList(MovieType.values());
                printList(types);
                try{
                    option = sc.nextInt() - 1; //zero-indexing
                }catch(InputMismatchException e){
                    System.out.println("Wrong input");
                    sc.next();
                }
                return types.get(option);
            case "Movie director":
                return sc.nextLine();
            case "Movie cast":
                return sc.nextLine();
            case "Movie audience rating":
                // force user to enter MovieRating
                List<MovieRating> ratings = Arrays.asList(MovieRating.values());
                printList(ratings);
                try{
                    option = sc.nextInt() - 1; // zero-indexing
                }catch(InputMismatchException e){
                    System.out.println("Wrong input");
                    sc.next();
                }
                return ratings.get(option);
            default:
                System.out.println("Error in input!");
                return null;
        }
    }

    /**
     * Display search result
     */
    public void returnSearchResult() { 
        if(searchKey == null) return;
        SearchController searchController = new SearchController(searchCategory, searchKey);
        List<Movie> result = searchController.search();
        if(result == null || result.size() <= 0) {
            System.out.println("=== No movies found ===");
            return;
        }
        // Printing only the titles by default
        System.out.println("=== Movies found ===");
        for(int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ". " + result.get(i).getTitle());
        }

        // Print full details
        System.out.println("Show full details for each movie? Y/N");
        Scanner sc = new Scanner(System.in);
        char c = sc.nextLine().charAt(0);
        if(Character.toLowerCase(c) == 'y')
            printList(result);
    }

    /**
     * Funtion to print out as a list
     * @param list
     */
    private void printList(List list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }       
    }
}
