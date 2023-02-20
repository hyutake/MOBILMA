package Service;

import java.util.ArrayList;
import java.util.List;

import Controller.FileAccessController;
import Controller.FileAccess.MovieFileAccess;
import Model.Movie;
/** 
 * Provides the service to sort a list of movies
 * Inherited by several sub classes to perform different sorting algorithms (strategy pattern)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-17
 */
public abstract class SortService {
    /**
     * Array list of movies to be sorted
     */
    protected ArrayList<Movie> movies;

    /**
     * File accessor to acquire the list of movies
     */
    protected FileAccessController fileAccess;
    
    /**
     * Constructor to init {@link SortService}
     */
    public SortService() {
        fileAccess = new MovieFileAccess();
        movies = (ArrayList) fileAccess.getData();
    }

    /**
     * abstract funtion for sort
     * @return List
     */
    public abstract List sort();
}
