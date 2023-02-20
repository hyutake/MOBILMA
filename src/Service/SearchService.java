package Service;

import java.util.ArrayList;

import Constants.MovieRating;
import Constants.MovieStatus;
import Constants.MovieType;
import Controller.FileAccessController;
import Controller.FileAccess.MovieFileAccess;
import Model.Movie;
/** 
 * Provides the service for the searching of movie by a particular metric
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 */
public class SearchService {
    /**
     * The main file accessor module (interface) in-charge of reading and writing from file
     */
    private FileAccessController fileAccess;
    
    /**
     * List of all movies in the database, regardless of movie showing status
     */
    private ArrayList<Movie> movies;

    /**
     * Movies that match the search
     */
    private ArrayList<Movie> result = new ArrayList<>(); 
    

    /**
     * Constructor for search service
     */
    public SearchService() {
        fileAccess = new MovieFileAccess();
        movies = (ArrayList) fileAccess.getData();
    }

    /** Search through the movie database by title (exact, case-independent matching)
     * @param searchKey     User's search input(movie title) 
     * @return ArrayList    Returns an ArrayList of movies that match that title
     */
    public ArrayList searchByTitle(String searchKey) {
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() == MovieStatus.END_OF_SHOWING) continue;
            if(m.getTitle().toLowerCase().equals(searchKey.toLowerCase())) // make it case independent
                result.add(m);
        }
        return result;
    }
    
    /** Search through the movie database by director (exact, case-independent matching)
     * @param searchKey     User's search input(movie director)
     * @return ArrayList    Returns an ArrayList of movies that have that director
     */
    public ArrayList searchByDirector(String searchKey) {
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() == MovieStatus.END_OF_SHOWING) continue;
            if(m.getDirector().toLowerCase().equals(searchKey.toLowerCase())) 
                result.add(m);
        }
        return result;
    }

    /** Search through the movie database by cast (exact, case-independent matching)
     * @param searchKey     User's search input(movie cast)
     * @return ArrayList    Returns an ArrayList of movies that have that cast member
     */
    public ArrayList searchByCast(String searchKey) {
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() == MovieStatus.END_OF_SHOWING) continue;
            String[] cast = m.getCast();
            for(int j = 0; j < cast.length; j++) {
                String castMember = cast[j].toLowerCase();
                if(castMember.equals(searchKey.toLowerCase())) { // if matching cast is found early, add the movie and go next
                    result.add(m);
                    break;  
                }
            }
        }
        return result;
    }    

    /** Search through the movie database by movie type (exact matching)
     * @param searchKey     User's search input(movie type - 'forced' to be valid)
     * @return ArrayList    Returns an ArrayList of movies that are of that movie type
     */
    public ArrayList searchByType(MovieType searchKey) {
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getType().equals(searchKey)) {
                result.add(m);
            }
        }
        return result;
    }

    /** Search through the movie database by audience rating (exact matching)
     * @param searchKey     User's search input(audience rating - 'forced' to be valid)
     * @return ArrayList    Returns an ArrayList of movies that are of that audience rating
     */
    public ArrayList searchByAudienceRating(MovieRating searchKey) {
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getMovieRating().equals(searchKey)) {
                result.add(m);
            }
        }
        return result;
    }
}

