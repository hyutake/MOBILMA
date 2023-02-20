package Model;

import java.io.Serializable;

/** 
 * Stores the reviews set by users for a Movie
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 */
public class Review implements Serializable {
    /**
     * Username of reviewer
     */
    private String reviewer;

    /**
     * Full text review
     */
    private String review;

    /**
     * Rating given
     */
    private int rating;
    
    /**
     * Creates a Review object that stores the reviewer's username, text review and rating given
     * @param reviewer
     * @param review
     * @param rating
     */
    public Review(String reviewer, String review, int rating) {
        this.reviewer = reviewer;
        this.review = review;
        this.rating = rating;
    }
    
    /**
     * Get the reviewer's username
     * @return String       Reviewer's username
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * Sets the reviewer's username
     * @param reviewer      New reviewer's username
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * Get the text review
     * @return String   Full text review
     */
    public String getReview() {
        return review;
    }

    /**
     * Set the text review
     * @param review    New full text review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     * Get the rating given
     * @return int  Rating by reviewer
     */
    public int getRating() {
        return rating;
    }

    /**
     * Set the rating given
     * @param rating    New rating by reviewer
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Prints the individual review, reformatted for readability
     * @return String       Reformatted review
     */
    @Override
    public String toString() {
        return reviewer + ": rating = " + rating + "; review = " + review;
    }
}
