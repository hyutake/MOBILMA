package Controller;

import java.util.List;

import Constants.AgeGroup;
import Controller.FileAccess.CineplexFileAccess;
import Controller.FileAccess.MovieFileAccess;
import Controller.FileAccess.UserFileAccess;
import Model.BookingInfo;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Session;
import Model.User;
/** 
 * Manages the logic for a single booking session
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-02
 */
public class BookingController {
    /**
     * Stores all the booking information acquired from the user
     */
    private BookingInfo bookingInfo;

    /**
     * Creates a BookingController that is in charge of initialising the BookingInfo object with the parameters
     * Also responsible for updating the database files accordingly
     * @param user          Booking information
     * @param cineplex      Booking information
     * @param cinema        Booking information
     * @param movie         Booking information
     * @param session       Booking information
     * @param seatID        Booking information
     * @param ageGroup      Booking information
     * @param isHoliday     Booking information
     */
    public BookingController(User user, Cineplex cineplex, Cinema cinema, Movie movie, Session session, int seatID, AgeGroup ageGroup, boolean isHoliday) {
        // Generate BookingInfo object
        this.bookingInfo = new BookingInfo(movie, cineplex, cinema, ageGroup, session, seatID, isHoliday);

        // Update movie ticket sales
        updateMovieDatabase(movie, bookingInfo.getTicketPrice());

        // Update User information
        List<BookingInfo> bookingList = user.getPastBookings();
        bookingList.add(bookingInfo); 
        updateUserDatabase(user);

        // Update the Cinema information
        CineplexFileAccess cineplexAccess = new CineplexFileAccess(cineplex.getLocation());
        cineplexAccess.updateCinema(cinema);
    }

    
    /** 
     * Gets the booking information from the booking session
     * @return BookingInfo
     */
    public BookingInfo getBookingInfo() {
        return bookingInfo;
    }

    
    /** 
     * To update the actual movie database file data according to the booking made
     * @param m             Movie that was booked
     * @param ticketPrice   Additional ticket sales acquired as a result of the booking
     */
    public void updateMovieDatabase(Movie m, double ticketPrice) {
        MovieFileAccess movieAccess = new MovieFileAccess();
        double oldTicketSales = m.getTicketSales();
        m.setTicketSales(oldTicketSales + ticketPrice);
        movieAccess.updateMovie(m);
    }

    /** 
     * To update the actual user database file data according to the booking made
     * @param user      User who made the booking
     */
    public void updateUserDatabase(User user) {
        UserFileAccess userAccess = new UserFileAccess();
        userAccess.updateUser(user);
    }
}
