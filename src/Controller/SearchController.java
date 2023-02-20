package Controller;

import java.util.ArrayList;
import java.util.List;

import Constants.MovieRating;
import Constants.MovieType;
import Exceptions.InvalidSearchKey;
import Model.Movie;
import Service.SearchService;
/** 
 * Manages all the logic involving the searching of a particular movie (based on several categories)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 */
public class SearchController {
    /**
     * Category of search that is requested via user input
     */
    private String category;

    /**
     * Search key used in the search to be matched against the database
     */
    private Object key;

    /**
     * The list of movies that matches both the category and the key
     */
    private ArrayList<Movie> result = new ArrayList<>();

    /**
     * Handles the actual logic behind the search functionality
     */
    private SearchService searchService;

    public SearchController() {
        searchService = new SearchService();
    }

    /**
     * Creates the SearchController and initialises the category and key based on prior user input acquired from Boundary.{@link SearchUI}
     * @param category
     * @param key
     */
    public SearchController(String category, Object key) {
        this();
        this.category = category;
        this.key = key;
    }

    /** 
     * Returns the list of movies that match the user specified search category and key
     * @return List         List of movies that satisfies the search requirements
     */
    public List search() {
        try {
            switch(category) {
                case "Movie title":
                    if(key instanceof String == false) throw new InvalidSearchKey("Invalid movie title!");
                    result = searchService.searchByTitle((String) key);
                    break;
                case "Movie type":
                    if(key instanceof MovieType == false) throw new InvalidSearchKey("Invalid movie type!");
                    result = searchService.searchByType((MovieType) key);
                    break;
                case "Movie director":
                    if(key instanceof String == false) throw new InvalidSearchKey("Invalid movie director!");
                    result = searchService.searchByDirector((String) key);
                    break;
                case "Movie cast":
                    if(key instanceof String == false) throw new InvalidSearchKey("Invalid movie cast!");
                    result = searchService.searchByCast((String) key);
                    break;
                case "Movie audience rating":
                    if(key instanceof MovieRating == false) throw new InvalidSearchKey("Invalid movie rating!");
                    result = searchService.searchByAudienceRating((MovieRating) key);
                    break;
                default:
                    System.out.println("Error in input!");
            }
            
        } catch (InvalidSearchKey e) {
            System.out.println("Invalid search key!");
            System.out.println(e.getMessage());
        }
        return result;
    }
}
