package Service.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Constants.MovieStatus;
import Service.SortService;
import Model.Movie;
/** 
 * Provides the service to sort a list of movies in descending order of rating
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-17
 * @see     SortService
 */
public class SortByRating extends SortService {
    public SortByRating() {
        super();
    }
    
    /** 
     * Sorts the list of movies
     * @return List     Sorted list of movies
     */
    public List sort() {
        // filtering out movies that are END_OF_SHOWING
        ArrayList<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() != MovieStatus.END_OF_SHOWING) {
                result.add(m);
            }
        }

        // Sort by rating  
        Collections.sort(result, (Movie one, Movie other) -> {
            return one.getOverallRating().compareTo(other.getOverallRating());
        });
        Collections.reverse(result);
        // shifting "NA" to the bottom
        while(result.get(0).getOverallRating().equals("NA")) {
            Movie tmp = result.get(0);
            for(int i = 0; i < result.size() - 1; i++) {
                result.set(i, result.get(i + 1));
            }
            result.set(result.size() - 1, tmp);
        }
        return result;
    }
}
