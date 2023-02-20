package Service;

import java.util.ArrayList;
import Model.PriceModifier;

/** 
 * Provides the service for the calculation of ticket prices
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class PriceCalculator {
    /**
     * List of priceModifiers, generated during the booking process
     */
    private ArrayList<PriceModifier> priceModifiers;

    /**
     * Differentiates the output of the calculation depending on user access (Admin vs Customer)
     * Shows the breakdown of pricing for Admins
     */
    private boolean debugPrint = false; 

    /**
     * Creates the price calculator module for Customer use
     * @param priceModifiers    List of pricing factors acquired from booking
     */
    public PriceCalculator(ArrayList<PriceModifier> priceModifiers) {
        this.priceModifiers = priceModifiers;
    }

    /**
     * Creates the price calculator module for Admin use
     * @param priceModifiers    List of pricing factors acquired from a test booking
     * @param debugPrint        Option for the admin to enable or disable this feature
     */
    public PriceCalculator(ArrayList<PriceModifier> priceModifiers, boolean debugPrint) {
        this(priceModifiers);
        this.debugPrint = debugPrint;
    }

    
    /** 
     * Calculates the price by summing up the values of all the price modifiers
     * @return double   Total ticket price based on the factors provided
     */
    public double calculatePrice() {
        double result = 0;
        for(int i = 0; i < priceModifiers.size(); i++) {
            PriceModifier p = priceModifiers.get(i);
            result += p.getAdditionalPrice();
            if(debugPrint) System.out.println(p.toString()); // debug
        }
        return result;
    }
}
