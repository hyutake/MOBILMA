package Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import Constants.MovieRating;
import Constants.MovieStatus;
import Constants.MovieType;

/** 
 * Represents a movie that can be shown in the cinema.
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-04
 */
public class Movie implements Serializable {
    /**
     *  Movie type associated with the movie 
     */
    private MovieType type;

    /**
     *  Title of the movie 
     */
    private String title;

    /**
     *  Movie status associated with the movie
     */
    private MovieStatus status;

    /**
     *  Synopsis of the movie 
     */
    private String synopsis;

    /**
     *  Director of the movie 
     */
    private String director;

    /**
     *  (Main) Cast of movie  
     */
    private String[] cast; 

    /**
     *  Overall rating based on >1 reviews of the movie 
     */
    private String overallRating;

    /**
     *  Movie audience rating associated with the movie 
     */
    private MovieRating movieRating;

    /**
     *  Reviews of the movie
     */
    private List<Review> pastReviews;

    /**
     *  Sales from tickets sold
     */
    private double ticketSales;

    /**
     * Creates a Movie for Admin to edit and add into the database
     */
    public Movie() {
        this.ticketSales = 0;
    }

    /**
     * Creates a Movie with the given parameters
     * Overall rating is generated and updated independently based on pastReviews
     * @param type              Movie type
     * @param title             Movie title
     * @param status            Movie showing status
     * @param synopsis          Movie synopsis
     * @param director          Director of the movie
     * @param cast              Cast involved in the movie
     * @param movieRating       Movie audience rating
     * @param pastReviews       Reviews about the movie
     * @param ticketSales       Sales generated from selling tickets for this movie
     */
    public Movie(MovieType type, String title, MovieStatus status, String synopsis, String director, String[] cast
                    , MovieRating movieRating, List<Review> pastReviews, double ticketSales) {
        this.type = type;
        this.title = title;
        this.status = status;
        this.synopsis = synopsis;
        this.director = director;
        this.cast = cast;
        this.movieRating = movieRating;
        this.pastReviews = pastReviews;
        this.ticketSales = ticketSales;
        setOverallRating();
    }

    /**
     * Get the movie type of the movie
     * @return MovieType    Type of the movie
     */
    public MovieType getType() {
        return type;
    }

    /**
     * Set the movie type of the movie
     * @param type      New type for the movie
     */
    public void setType(MovieType type) {
        this.type = type;
    }

    /**
     * Get the title of the movie
     * @return String    Title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the movie
     * @param title     New title of the movie
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the movie showing status
     * @return MovieStatus    Showing status of the movie
     */
    public MovieStatus getStatus() {
        return status;
    }

    /**
     * Set the movie showing status
     * @param status        New showing status of the movie
     */
    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    /**
     * Get the movie synopsis
     * @return String    Synopsis of the movie
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Set the movie synopsis
     * @param synopsis      New synopsis for the movie
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Get the director of the movie
     * @return String    Director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set the director of the movie
     * @param director      New director of the movie
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Get the cast of the movie
     * @return String[]    Cast of the movie
     */
    public String[] getCast() {
        return cast;
    }

    /**
     * Set the cast of the movie
     * @param cast      New cast members of the movie
     */
    public void setCast(String[] cast) {
        this.cast = cast;
    }

    /**
     * Get the overall rating of the movie
     * @return String    Overall rating of the movie
     */
    public String getOverallRating() {
        return overallRating;
    }

    /**
     * Generates the overall rating of the movie based on pastReviews
     */
    public void setOverallRating() {
        if(pastReviews.size() <= 1) {
            this.overallRating = "NA";
        }
        else {
            float totalReviewScore = 0;
            for(int i = 0; i < pastReviews.size(); i++) {
                totalReviewScore += pastReviews.get(i).getRating();
            }
            this.overallRating = String.format("%.1f", (totalReviewScore / pastReviews.size()));    // must be to 1 d.p
        }
    }

    /**
     * Get the list of reviews for the movie
     * @return Model.{@link Review}    List of reviews for the movie
     */
    public List<Review> getPastReviews() {
        return pastReviews;
    }

    /**
     * Set the list of reviews for the movie
     * @param pastReviews       New list of reviews for the movie
     */
    public void setPastReviews(List<Review> pastReviews) {
        this.pastReviews = pastReviews;
    }

    /**
     * Get the movie audience rating
     * @return MovieRating    Audience rating of the movie
     */
    public MovieRating getMovieRating() {
        return movieRating;
    }

    /**
     * Set the movie audience rating
     * @param movieRating       New audience rating of the movie
     */
    public void setMovieRating(MovieRating movieRating) {
        this.movieRating = movieRating;
    }

    /**
     * Get the ticket sales of the movie
     * @return double    ticket sales of the movie
     */
    public double getTicketSales() {
        return ticketSales;
    }

    /**
     * Set the ticket sales of the movie
     * @param ticketSales       New ticket sale value for the movie
     */
    public void setTicketSales(double ticketSales) {
        this.ticketSales = ticketSales;
    }

    /**
     * Prints all the relevant movie details, overriden so that it is formatted for readability
     * @return String       Movie details
     */
    @Override
    public String toString() {
        return "=== " + title + " (" + movieRating + ")" + " ===" + "\nMovie type = " + type + "; Status = " + status + 
            "\nSynopsis = " + synopsis + "\n\ndirector = " + director + "; cast = " + Arrays.toString(cast) + 
            "\noverallRating = " + overallRating + "\nReviews: " + reviewString();
    }

    /**
     * Formats the list of reviews such that it will be more readable when printed
     * @return String       All the pastReviews in String format
     */
    public String reviewString() {
        StringBuilder st = new StringBuilder();
        for(int i = 0; i < pastReviews.size(); i++) {
            st.append("\n");
            st.append((i + 1) + ". " + pastReviews.get(i).toString());
            st.append("\n");
        }
        return st.toString();
    }  
}