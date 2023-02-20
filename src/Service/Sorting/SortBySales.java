package Service.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Constants.MovieStatus;
import Model.Movie;
import Service.SortService;
/** 
 * Provides the service to sort a list of movies in descending order of ticket sales
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-04
 * @see     SortService
 */
public class SortBySales extends SortService {
    public SortBySales() {
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
        
        // Sort by ticket sales 
        Collections.sort(result, new Comparator<Movie>() {
            @Override public int compare(Movie m1, Movie m2) {
                return (m1.getTicketSales() >  m2.getTicketSales() ? -1:1); 
            };
        });

        return result;
    }
}
