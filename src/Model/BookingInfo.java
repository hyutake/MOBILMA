package Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;

import Constants.AgeGroup;
import Controller.PriceController;

/** 
 * Represents the booking information acquired when a customer books a movie ticket.
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-05
 */
public class BookingInfo implements Serializable {
    /**
     *  Movie booked by customer
     */
    private Movie movie;
    /**
     *  Cineplex chosen by customer
     */
    private Cineplex cineplex;
    /**
     *  Cinema chosen by customer
     */
    private Cinema cinema;
    /**
     *  Age group set for the booked ticket by customer
     */
    private AgeGroup ageGroup;
    /**
     *  Cinema session chosen by customer
     */
    private Session session;
    /**
     *  Identifier of the seat chosen by customer
     */
    private int seatID;
    /**
     *  Check whether the date chosen is a holiday/weekend or not
     */
    private boolean isHoliday;
    /**
     *  Transaction ID - generated based on cinema code and time of booking made
    */
    private String TID;
    /**
     *  Price of ticket - generated based on cinema class, age group, holiday/weekend and movie type
     */
    private double ticketPrice;
    
    /**
     * Creates a BookingInfo object with the following parameters
     * Each BookingInfo object will represent 1 instance of a successful booking done by the customer
     * @param movie         Chosen movie
     * @param cineplex      Chosen Cineplex
     * @param cinema        Chosen Cinema
     * @param ageGroup      Ticket for age group
     * @param seatID        Chosen seat ID in cinema
     * @param date          Date of movie booked
     * @param isHoliday     Whether booking date is holiday/weekend or not
     */
    public BookingInfo(Movie movie, Cineplex cineplex, Cinema cinema, AgeGroup ageGroup, Session session, int seatID, boolean isHoliday) {
        this.movie = movie;
        this.cineplex = cineplex;
        this.cinema = cinema;
        this.ageGroup = ageGroup;
        this.session = session;
        this.seatID = seatID;
        this.isHoliday = isHoliday;
        this.ticketPrice = generateTicketPrice();
        TID = generateTID(cinema.getCinemaCode());
    }

    /**
     * Get the transaction ID of the movie ticket booking
     * @return String   Transaction ID
     */
    public String getTID() {
        return TID;
    }

    /**
     * Get the ticket price
     * @return double   Ticket price for the booking
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Generate the ticket price based on Cinema class, age group, movie type and booking date chosen
     * @return double   Ticket price generated
     */
    public double generateTicketPrice() {
        PriceController priceCtrl = new PriceController(cinema.getCinemaClass(), movie.getType(), ageGroup, isHoliday);
        return priceCtrl.generateTicketPrice();
    }

    /**
     * Prints all the relevant booking details, overriden so that it is formatted for readability
     * @return String       Booking information
     */
    @Override
    public String toString() {
        return "=== BookingInfo ===\nMovie title = " + movie.getTitle() + "; Cineplex branch = " + cineplex.getLocation() + "; Cinema code = " + cinema.getCinemaCode() +
                "\nSession = " + session.getStringTimeSlot() + "; seatID: " + seatID +
                "\nageGroup = " + ageGroup + "; ticketPrice = $" + ticketPrice +
                "\nTransactionID = " + TID;
    }

    /**
     * Generate the transaction ID based on Cinema code and time of booking
     * @param cinemaCode    Identifier of the cinema chosen where the ticket is booked for 
     * @return String       Transaction ID
     */
    public String generateTID(String cinemaCode) {
        // Format: XXXYYYYMMDDhhmm (Y: year, M: month, D: day, h: hour, m: minute, X: cinemaCode)
        LocalDateTime now = LocalDateTime.now();

        int year = now.getYear();
        NumberFormat f = new DecimalFormat("00");
        String month = String.valueOf(f.format(now.getMonthValue()));
        String day = String.valueOf(f.format(now.getDayOfMonth()));
        String hour = String.valueOf(f.format(now.getHour()));
        String minute = String.valueOf(f.format(now.getMinute()));

        return cinemaCode + year + month + day + hour + minute;
    }
    
}
