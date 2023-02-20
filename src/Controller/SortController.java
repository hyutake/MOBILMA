package Controller;

import java.util.List;

import Service.SortService;
import Service.Sorting.SortByRating;
import Service.Sorting.SortBySales;
import Service.Sorting.SortDefault;
import Service.Sorting.SortForBooking;
/** 
 * Manages the allocation of sorting tasks depending on the user input acquired from Boundary.{@link SortListUI}
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-24
 */
public class SortController {
    /**
     * Sorting specification
     */
    private String sortBy;
    /**
     * Handles the actual sorting logic
     */
    private SortService sortService;

    public SortController() {
        this.sortBy = "(Coming Soon, Now Showing and Preview)";
    }
    public SortController(String sortBy) {
        this();
        this.sortBy = sortBy.replace("by", " ").trim();   // remove the "by " 
    }

    
    /** 
     * Allocates the sorting task based on the given sorting specification
     * @return List     Returns a sorted list of movies
     */
    public List allocateSort() {
        switch(sortBy) {
            case "(Coming Soon, Now Showing and Preview)":
                sortService = new SortDefault();
                break;
            case "overall ratings":
                sortService = new SortByRating();
                break;
            case "ticket sales":
                sortService = new SortBySales();
                break;
            case "booking":
                sortService = new SortForBooking();
                break;
            default:
                System.out.println("Invalid sorting constraint!");
                return null;
        }
        return sortService.sort();
    }
}
