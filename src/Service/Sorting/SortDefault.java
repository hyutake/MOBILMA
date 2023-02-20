package Service.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Constants.MovieStatus;
import Model.Movie;
import Service.SortService;
/** 
 * Provides the service to sort a list of movies in alphabetical order
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-17
 * @see     SortService
 */
public class SortDefault extends SortService {
    public SortDefault() {
        super();
    }

    /** 
     * Sorts the list of movies by alphabetical order
     * @return List         Sorted list of movies
     */
    public List sort() {
        ArrayList<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() != MovieStatus.END_OF_SHOWING) {
                result.add(m);
            }
        }
        // Sort by title (default)
        Collections.sort(result, (Movie one, Movie other) -> {
            return one.getTitle().compareTo(other.getTitle());
        });

        return result;
    }
}
