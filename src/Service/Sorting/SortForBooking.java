package Service.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Constants.MovieStatus;
import Model.Movie;
import Service.SortService;
/** 
 * Provides the service to sort the movie list into the one shown during booking
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 * @see     SortService
 */
public class SortForBooking extends SortService {
    public SortForBooking() {
        super();
    }

    /** Sorts the ArrayList of Movies to be displayed for booking purposes (no END_OF_SHOWING, no COMING_SOON)
     * @return List     Returns the sorted ArrayList
     */
    public List sort() {
        ArrayList<Movie> result = new ArrayList<>();
        for(int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            if(m.getStatus() != MovieStatus.END_OF_SHOWING && m.getStatus() != MovieStatus.COMING_SOON) {
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
