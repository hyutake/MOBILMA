package Controller;

import java.util.ArrayList;
import java.util.EnumMap;

import Constants.AgeGroup;
import Constants.CinemaClass;
import Constants.MovieType;
import Controller.FileAccess.PriceFileAccess;
import Model.PriceModifier;
import Service.PriceCalculator;
import Model.PriceList;
/** 
 * Manages all the logic involving the searching of a particular movie (based on several categories)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class PriceController {
    /**
     * Toggle to enable additional information to be shown, but only for Admin users
     */
    private boolean debugPrint = false;     // Shows breakdown of calculation if debugPrint == true 

    /**
     * Pricing information for all the determinants of ticket price
     */
    private PriceList p;

    /**
     * List of price modifiers that map the determinants of ticket prices with their respective modifier values
     */
    private ArrayList<PriceModifier> priceModifiers = new ArrayList<>();

    /**
     * Creates the PriceController, which generates the list of price modifiers based on the parameters given and the current PriceList
     * @param cinemaClass       Ticket price determinant
     * @param movieType         Ticket price determinant
     * @param ageGroup          Ticket price determinant
     * @param isHoliday         Ticket price determinant
     */
    public PriceController(CinemaClass cinemaClass, MovieType movieType, AgeGroup ageGroup, boolean isHoliday) {
        PriceFileAccess priceAccess = new PriceFileAccess();
        this.p = priceAccess.getPrices();
        priceModifiers.add(new PriceModifier(p.getBasePrice()));    // base price
        priceModifiers.add(new PriceModifier("GST", p.getBasePrice(), 0.07));    // GST
        priceModifiers.add(new PriceModifier("Age Group", p.getBasePrice(), p.getAgePricing().get(ageGroup)));
        priceModifiers.add(new PriceModifier("Cinema Class", p.getBasePrice(), p.getCinemaPricing().get(cinemaClass)));
        priceModifiers.add(new PriceModifier("Movie Type", p.getBasePrice(), p.getMovieTypePricing().get(movieType)));
        priceModifiers.add(new PriceModifier("Holiday", p.getHolidayPrice(), isHoliday ? 1 : 0));  // holiday price is fixed for now, either add or don't add
    }

    /**
     * Creates the PriceController for Admin use
     * @param cinemaClass       Ticket price determinant
     * @param movieType         Ticket price determinant
     * @param ageGroup          Ticket price determinant
     * @param isHoliday         Ticket price determinant
     * @param debugPrint        Additional Admin toggle option to show breakdown of pricing if desired
     */
    public PriceController(CinemaClass cinemaClass, MovieType movieType, AgeGroup ageGroup, boolean isHoliday, boolean debugPrint) {
        this(cinemaClass, movieType, ageGroup, isHoliday);
        this.debugPrint = debugPrint;
    }
    
    /** 
     * Generates the ticket pricing by passing over the list of price modifiers to Service.{@link PriceCalculator}
     * @return double       The final ticket price
     */
    public double generateTicketPrice() {
        PriceCalculator pCalc = (debugPrint) ? new PriceCalculator(priceModifiers, debugPrint) : new PriceCalculator(priceModifiers);
        return pCalc.calculatePrice();
    }
}
