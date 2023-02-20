package Service.Admin;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import Constants.AgeGroup;
import Constants.CinemaClass;
import Constants.MovieType;
import Controller.FileAccessController;
import Controller.FileAccess.PriceFileAccess;
import Model.PriceList;
/** 
 * Initialises the ticket pricing details with preset (default) values and saves it as a .dat file
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class GeneratePrices {
    /**
     * Generates the ticket pricing details
     */
    public static void generatePrices() {
        EnumMap<AgeGroup, Double> agePricing = new EnumMap<>(AgeGroup.class);
        EnumMap<CinemaClass, Double> cinemaPricing = new EnumMap<>(CinemaClass.class);
        EnumMap<MovieType, Double> movieTypePricing = new EnumMap<>(MovieType.class);

        double basePrice = 5;

        agePricing.put(AgeGroup.CHILD, 0.3);
        agePricing.put(AgeGroup.ADULT, 0.5);
        agePricing.put(AgeGroup.SENIOR, 0.0);

        cinemaPricing.put(CinemaClass.STANDARD, 0.2);
        cinemaPricing.put(CinemaClass.PREMIUM, 0.5);

        movieTypePricing.put(MovieType.BLOCKBUSTER, 0.2);
        movieTypePricing.put(MovieType.IMAX, 0.3);
        movieTypePricing.put(MovieType.THREE_D, 0.4);

        double holidayPrice = 2;

        PriceList testPricing = new PriceList(basePrice, agePricing, cinemaPricing, movieTypePricing, holidayPrice);

        System.out.println(testPricing.toString()); // debug

        List<PriceList> list = new ArrayList<>();
        list.add(testPricing);
        FileAccessController.writeSerializedObject(PriceFileAccess.fileName, list);
    }
}
