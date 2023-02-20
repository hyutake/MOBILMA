package Service.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Boundary.SortListUI;
import Constants.MovieRating;
import Constants.MovieStatus;
import Constants.MovieType;
import Constants.UserType;
import Controller.FileAccessController;
import Controller.FileAccess.MovieFileAccess;
import Model.Movie;
import Model.Review;
/** 
 * Provides the service for an admin to modify movie details (hidden to Customers)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class AdminMovieService {
    private MovieFileAccess movieAccess;
    private List<Movie> movieList;

    public AdminMovieService() {
        movieAccess = new MovieFileAccess();
        movieList = movieAccess.getData();
    }

    /**
     * Gets Admin input and adds a new movie to the database
     */
    public void addMovie() {
        /* initialise Movie m */
        Movie m = new Movie();

        // type
        m.setType(getMovieType());
        // title
        m.setTitle(getMovieString("title"));
        // status
        m.setStatus(getMovieStatus());
        // synopsis
        m.setSynopsis(getMovieString("synopsis (press <ENTER> only when done)"));
        // director
        m.setDirector(getMovieString("director"));
        // cast
        m.setCast(getMovieCast());
        // audienceRating
        m.setMovieRating(getMovieRating());
        // pastReviews
        m.setPastReviews(getPastReviews());
        // update overall review score based on new reviews, otherwise it will use the one for Morbius which is 4.50
        m.setOverallRating();

        /* Writing the movie to file */
        movieList.add(m);
        movieAccess.writeData(movieList);
    }

    /**
     * Gets admin input and updates an existing movie in the database
     */
    public void updateMovie() {
        /* user input */
        Scanner sc = new Scanner(System.in);
        int choice = 0; 
        String dummy;

        // listing each existing Movie object in the file
        System.out.println("=== Movies in the Database ===");
        for(int i = 0; i < movieList.size(); i++) {
            Movie m = movieList.get(i);
            System.out.println((i + 1) + ". " + m.getTitle());
        }

        // user input for chosen movie
        System.out.print("Enter which movie to update: ");
        int movieIndex = sc.nextInt() - 1;  // zero-indexing
        dummy = sc.nextLine();

        Movie m;
        try {
            m = movieList.get(movieIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice, exiting...");
            return;
        }

        System.out.println("=== Selected " + m.getTitle() + " ===");
        do {
            System.out.println("Enter which attribute to update:\n"+
                                "1. Type\n"+
                                "2. Title\n" +
                                "3. Status\n"+
                                "4. Synopsis\n"+
                                "5. Director\n"+
                                "6. Cast\n"+
                                "7. Rating\n"+
                                "8. Reviews\n"+
                                "9. Exit");
            try{
                choice = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
            //dummy = sc.nextLine();
            switch(choice) {
                case(1):    // type
                    System.out.println("Current type: " + m.getType());
                    m.setType(getMovieType());
                    break;
                case(2):    // title
                    System.out.println("Current title: " + m.getTitle());
                    m.setTitle(getMovieString("title"));
                    break;
                case(3):    // status
                    System.out.println("Current status: " + m.getStatus());
                    m.setStatus(getMovieStatus());
                    break;
                case(4):    // synopsis
                    System.out.println("Current synopsis: " + m.getSynopsis());
                    m.setSynopsis(getMovieString("synopsis (press <ENTER> only when done)"));
                    break;
                case(5):    // director
                    System.out.println("Current director: " + m.getDirector());
                    m.setDirector(getMovieString("director"));
                    break;
                case(6):    // cast
                    System.out.println("Current cast: " + m.getCast().toString());
                    m.setCast(getMovieCast());
                    break;
                case(7):    // rating
                    System.out.println("Current movie rating: " + m.getMovieRating());
                    m.setMovieRating(getMovieRating());
                    break;
                case(8):    // reviews
                    System.out.println("Current reviews: " + m.reviewString());
                    m.setPastReviews(getPastReviews());
                default:
                    System.out.println("Exiting update...");
                    break;
            }
            m.setOverallRating();   // update overall rating - for the case if reviews get updated
        } while(choice >= 1 && choice <=8);

        /* Updating file file */
        movieList.set(movieIndex, m);
        movieAccess.writeData(movieList);
    }

    /**
     * Gets Admin input and removes a movie from the database
     */
    public void removeMovie() {
        /* user input */
        Scanner sc = new Scanner(System.in);
        String dummy;

        // listing each existing Movie object in the file
        System.out.println("=== Movies in the database ===");
        for(int i = 0; i < movieList.size(); i++) {
            Movie m = movieList.get(i);
            System.out.println((i + 1) + ". " + m.getTitle());
        }

        System.out.println((movieList.size() + 1) + ". Exit");   // Exit option

        // getting FileAccessController input
        int movieIndex = 0;
        System.out.print("Enter which movie to delete: ");
        try {
            movieIndex = sc.nextInt() - 1;  // zero-indexing
            if(movieIndex >= movieList.size() || movieIndex < 0) return; // Exit option - any invalid number 
        } catch(InputMismatchException e) {
            System.out.println("Invalid input for removeMovie()!");
        }
           
        dummy = sc.nextLine();

        // removing
        Movie m = movieList.get(movieIndex);
        System.out.println("Removing " + m.getTitle() + "...");

        /* Updating file */
        movieList.remove(m);
        if(movieList.size() == 0) {
            FileAccessController.deleteFile(MovieFileAccess.fileName);
            return;
        }
        movieAccess.writeData(movieList);
    }

    /**
     * Displays ALL the available movies, including those END_OF_SHOWING
     */
    public void viewMovie() {
        // listing each movie
        System.out.println("=== Movie titles ===");
        for (int i = 0 ; i < movieList.size() ; i++) {
            Movie m = movieList.get(i);
            System.out.println((i + 1) + ". " + m.getTitle());   // might need to edit the toString() function to tidy it up
        }

        // Print full details
        System.out.println("Show full details for each movie? Y/N");
        Scanner sc = new Scanner(System.in);
        char c = sc.nextLine().charAt(0);
        if(Character.toLowerCase(c) == 'y') {
            for (int i = 0 ; i < movieList.size() ; i++) {
                Movie m = movieList.get(i);
                System.out.println(m.toString());   // might need to edit the toString() function to tidy it up
            } 
        }
    }

    /**
     * Shows all the movies but with additional information shown only for Admin accounts
     */
    public void listMovie() {
        new SortListUI(UserType.ADMIN);
    }

    
    /** 
     * To get Admin user input for Movie Type
     * @return MovieType
     */
    private static MovieType getMovieType() {
        Scanner sc = new Scanner(System.in);
        int choice; String dummy;
        System.out.println("Enter the movie type: ");

        List<MovieType> types = Arrays.asList(MovieType.values());
        for(int i = 0; i < types.size(); i++) {
            System.out.println((i + 1) + ". " + types.get(i));
        }

        choice = sc.nextInt() - 1; // zero-indexed
        dummy = sc.nextLine();

        MovieType type = types.get(0);    // default return value
        try {
            type = types.get(choice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice, assigning default value of " + type);
        }
        return type;
    }

    
    /** 
     * To get Admin user input for Movie showing status
     * @return MovieStatus
     */
    private static MovieStatus getMovieStatus() {
        Scanner sc = new Scanner(System.in);
        int choice; String dummy;
        System.out.println("Enter the movie showing status:");

        List<MovieStatus> statuses = Arrays.asList(MovieStatus.values());
        for(int i = 0; i < statuses.size(); i++) {
            System.out.println((i + 1) + ". " + statuses.get(i));
        }

        choice = sc.nextInt() - 1; // zero-indexed
        dummy = sc.nextLine();

        MovieStatus status = statuses.get(0);    // default return value
        try {
            status = statuses.get(choice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice, assigning default value of " + status);
        }
        return status;
        
    }

    
    /** 
     * To get Admin user input for Movie audience rating
     * @return MovieRating
     */
    private static MovieRating getMovieRating() {
        Scanner sc = new Scanner(System.in);
        int choice; String dummy;

        System.out.println("Enter the movie audience rating:");

        List<MovieRating> ratings = Arrays.asList(MovieRating.values());
        for(int i = 0; i < ratings.size(); i++) {
            System.out.println((i + 1) + ". " + ratings.get(i));
        }

        choice = sc.nextInt() - 1; // zero-indexed
        dummy = sc.nextLine();

        MovieRating rating = ratings.get(0);    // default return value
        try {
            rating = ratings.get(choice);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid choice, assigning default value of " + rating);
        }
        return rating;
    }
    
    
    /** 
     * To get all Admin inputs that are to be recorded as Strings for the Movie (title, synopsis and director)
     * @param attribute
     * @return String
     */
    private static String getMovieString(String attribute) {    
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the movie " + attribute + ": ");
        return sc.nextLine();
    }

    
    /** 
     * To get Admin input for the cast of the movie
     * @return String[]
     */
    private static String[] getMovieCast() {
        Scanner sc = new Scanner(System.in);
        String dummy; String[] cast;

        System.out.print("Enter number of cast members: ");
        int castCount = sc.nextInt();
        dummy = sc.nextLine();
        cast = new String[castCount];
        for(int i = 0; i < castCount; i++) {
            System.out.print("Enter cast[" + i + "]: ");
            cast[i] = sc.nextLine();
        }
        return cast;
    }

    
    /** 
     * To get Admin input for the List of reviews
     * @return List<Review>
     */
    private static List<Review> getPastReviews() {
        Scanner sc = new Scanner(System.in);
        String dummy;

        System.out.print("Enter number of reviews (need >1 review to get overallRating): ");
        int reviewCount = sc.nextInt();
        dummy = sc.nextLine();
        List<Review> pastReviews = new ArrayList<Review>();
        for(int i = 0; i < reviewCount; i++) {
            System.out.println("Review[" + i + "]: ");
            System.out.print("Enter the reviewer's name: ");
            String name = sc.nextLine();
            System.out.print("Enter the review: ");
            String review = sc.nextLine();
            System.out.print("Enter the review score: ");
            int score = sc.nextInt();
            dummy = sc.nextLine();
            pastReviews.add(new Review(name, review, score));
        }
        return pastReviews;
    }
}
