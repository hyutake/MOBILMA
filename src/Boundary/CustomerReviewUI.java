package Boundary;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.UserType;
import Controller.SortController;
import Controller.FileAccess.MovieFileAccess;
import Model.Movie;
import Model.Review;
import Model.User;
/** 
 * Displays the user interface to get user input when leaving a movie review
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-03
 */
public class CustomerReviewUI {
    private List<Movie> movies;
    private Scanner sc = new Scanner(System.in);
    private User user;

    /**
     * Creates a CustomerReviewUI module that provides the interface to take in the Customer's movie review
     * @param user
     */
    public CustomerReviewUI(User user) {
        this.user = user;
        // Show movies (preview, coming soon, now showing) as options to choose from
        SortController sortCtrl = new SortController();     // default is that sort setting
        movies = sortCtrl.allocateSort();

        // Get user to select
        Movie m = getMovieChoice();
        
        // Get user review
        Review r = getUserReview();

        // Update the movie's List<Review>
        List<Review> movieReviews = m.getPastReviews();
        movieReviews.add(r);
        m.setPastReviews(movieReviews);

        // Update database
        MovieFileAccess movieAccess = new MovieFileAccess();
        movieAccess.updateMovie(m);
    }

    
    /** 
     * Retrive user review
     * @return Review
     */
    public Review getUserReview() {
        String reviewer, userReview;
        int rating;

        if(user.getUserType() == UserType.INVALID) {
            reviewer = getStringInput("username");
        }
        else reviewer = user.getUsername();

        userReview = getStringInput("review");

        rating = getRating();

        return new Review(reviewer, userReview, rating);
    }

    
    /** 
     * Option to select movie
     * @return Movie
     */
    public Movie getMovieChoice() {
        String dummy;
        int option = -1;
        do {
            try {
                System.out.println("Which movie would you like to leave a review for?");
                // Printing list of movies
                for(int i = 0; i < movies.size(); i++) {
                    System.out.println((i + 1) + ". " + movies.get(i).getTitle());
                }

                option = sc.nextInt() - 1;  // zero-indexing
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again");
            }
        } while(option < 0 || option >= movies.size());

        System.out.println("Selected " + movies.get(option).getTitle());
        dummy = sc.nextLine();
        return movies.get(option);
    }

    
    /** 
     * Passes String input
     * @param prompt
     * @return String
     */
    public String getStringInput(String prompt) {
        System.out.print("Enter your " + prompt + ": ");
        return sc.nextLine();
    }

    
    /** 
     * Retrive rating
     * @return int
     */
    public int getRating() {
        // force correct input
        int rating = 1;
        do {
            try {
                System.out.println("Rating (1 - 5): ");
                rating = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again");
            }
        } while(rating < 0 || rating > 5);
        return rating;
    }

}
