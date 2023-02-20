package Model;

import java.io.Serializable;
import java.util.EnumMap;

import Constants.AgeGroup;
import Constants.CinemaClass;
import Constants.MovieType;

/** 
 * Represents the pricing information regarding ticket pricing
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class PriceList implements Serializable {
    /**
     * Base price of a ticket
     */
    private double basePrice;

    /**
     * Mapping of age groups to specific multipler values
     */
    private EnumMap<AgeGroup, Double> agePricing;

    /**
     * Mapping of cinema classes to specific multipler values
     */
    private EnumMap<CinemaClass, Double> cinemaPricing;

    /**
     * Mapping of movie types to specific multipler values
     */
    private EnumMap<MovieType, Double> movieTypePricing;

    /**
     * Additional flat fee for holidays
     */
    private double holidayPrice;

    public PriceList() {}

    /**
     * Creates a list of prices to be assigned for each aspect of ticket pricing
     * @param basePrice             Base price of a ticket
     * @param agePricing            Mapping of age groups to specific modifier values
     * @param cinemaPricing         Mapping of cinema classes to specific modifier values
     * @param movieTypePricing      Mapping of movie types to specific modifier values
     * @param holidayPrice          Additional price charged for holidays/weekends
     */
    public PriceList(double basePrice, EnumMap<AgeGroup, Double> agePricing, EnumMap<CinemaClass, Double> cinemaPricing,
            EnumMap<MovieType, Double> movieTypePricing, double holidayPrice) {
        this.basePrice = basePrice;
        this.agePricing = agePricing;
        this.cinemaPricing = cinemaPricing;
        this.movieTypePricing = movieTypePricing;
        this.holidayPrice = holidayPrice;
    }

    /**
     * Get the base price set for a ticket
     * @return double   Base price
     */
    public double getBasePrice() {
        return basePrice;
    }

    /**
     * Set the base price of a ticket (all other prices will scale accordingly)
     * @param basePrice     New base price
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Get the mapping of age groups to multipliers
     * @return EnumMap      Mapping of age groups to multipliers
     */
    public EnumMap<AgeGroup, Double> getAgePricing() {
        return agePricing;
    }

    /**
     * Set the mapping of age groups to multipliers
     * @param agePricing        New mapping of age groups to multipliers
     */
    public void setAgePricing(EnumMap<AgeGroup, Double> agePricing) {
        this.agePricing = agePricing;
    }

    /**
     * Get the mapping of cinema classes to multipliers
     * @return EnumMap      Mapping of cinema classes to multipliers
     */
    public EnumMap<CinemaClass, Double> getCinemaPricing() {
        return cinemaPricing;
    }

    /**
     * Set the mapping of cinema classes to multipliers
     * @param cinemaPricing         New mapping of cinema classes to multipliers
     */
    public void setCinemaPricing(EnumMap<CinemaClass, Double> cinemaPricing) {
        this.cinemaPricing = cinemaPricing;
    }

    /**
     * Get the mapping of movie types to multipliers
     * @return EnumMap      Mapping of movie types to multipliers
     */
    public EnumMap<MovieType, Double> getMovieTypePricing() {
        return movieTypePricing;
    }

    /**
     * Set the mapping of movie types to multipliers
     * @param movieTypePricing      New mapping of movie types to multipliers
     */
    public void setMovieTypePricing(EnumMap<MovieType, Double> movieTypePricing) {
        this.movieTypePricing = movieTypePricing;
    }

    /**
     * Get the flat holiday price charge
     * @return double       Holiday price
     */
    public double getHolidayPrice() {
        return holidayPrice;
    }

    /**
     * Set the flat holiday price charge
     * @param holidayPrice      New holiday price
     */
    public void setHolidayPrice(double holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    /**
     * Prints all the relevant pricing details, overriden so that it is formatted for readability
     * @return String       Pricing information
     */
    @Override
    public String toString() {
        return "=== Pricing ===\nbasePrice = " + basePrice + "\nagePricing = " + agePricing + "\ncinemaPricing = " + cinemaPricing
                + "\nmovieTypePricing = " + movieTypePricing + "\nholidayPrice = " + holidayPrice;
    }

    /**
     * Reformats the printing of EnumMaps for readability
     * @param e         EnumMap to be printed
     * @param type      Identifier to distinguish what EnumMap is it
     */
    public void printEnumMap(EnumMap e, String type) {
        e.forEach((key, value) -> {
            System.out.println(" - " + type + ": "+ key + ", Modifer: " + value );
        });
    }
}
