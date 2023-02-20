package Boundary;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.AgeGroup;
import Constants.CineplexLocation;
import Controller.BookingController;
import Controller.SessionController;
import Controller.SortController;
import Controller.TimeDateController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Session;
import Model.User;
/** 
 * Displays the user interface to get user input for booking a movie ticket
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class BookingUI {
    private User currentUser;
    private Cineplex cineplex;
    private Cinema cinema;
    private Movie movie;
    private Session session;
    private int seatID;
    private AgeGroup ageGroup;
    private String bookingDate;
    private boolean isHoliday = false;

    private Scanner sc = new Scanner(System.in);

    /**
     * Constructor to init BookingUI with reference to {@link User}
     * @param user
     */
    public BookingUI(User user) {
        currentUser = user.copyUser();

        // get booking information
        this.cineplex = getCineplex();   // Cineplex
        this.cinema = getCinema();    // Cinema -> CinemaClass
        getMovieSession();
        this.seatID = getSeat();    // Seating
        this.ageGroup = getAgeGroup();  // AgeGroup
        this.isHoliday = checkBookingDate();  // Check if booking date

        BookingController bookingCtrl = new BookingController(currentUser, cineplex, cinema, movie, session, seatID, ageGroup, isHoliday);

        displayPayment(bookingCtrl.getBookingInfo().getTicketPrice());
    }

    
    /** 
     * Retrive cineplex info
     * @return Cineplex
     */
    public Cineplex getCineplex() {
        int choice = -1;
        List<CineplexLocation> locations = Arrays.asList(CineplexLocation.values());
        
        // Force user to enter 'correct' input
        do {
            try {
                System.out.println("Select cineplex location: ");
                // Listing Age Group options
                for(int i = 0; i < locations.size(); i++) {
                    System.out.println((i + 1) + ". " + locations.get(i));
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
                sc.nextLine();
            }
        } while(choice < 0 || choice >= locations.size());

        /* Debug */
        System.out.println("Selected Cineplex at location: " + locations.get(choice));
        /* End Debug */
        return new Cineplex(locations.get(choice));
    }

    
    /** 
     * Retrive cinema hall info
     * @return Cinema
     */
    public Cinema getCinema() {
        int choice = -1;
        List<Cinema> cinemaHalls = cineplex.getCinemaHalls();

        // Force user to enter 'correct' input
        do {
            try {
                System.out.println("Select Cinema hall:");
                // Listing Age Group options
                for(int i = 0; i < cinemaHalls.size(); i++) {
                    System.out.println((i + 1) + ". Cinema Hall " + (i + 1) + 
                        "(" + cinemaHalls.get(i).getCinemaClass() + ")");
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
                sc.nextLine();
            }
        } while(choice < 0 || choice >= cinemaHalls.size());

        /* Debug */
        System.out.println("Selected Cinema: " + cinemaHalls.get(choice).getCinemaCode());
        /* End Debug */
        return cinemaHalls.get(choice);
    }

    /**
     * Retrive movie session
     */
    public void getMovieSession() {
        int choice = -1;
        
        SortController sortCtrl = new SortController("booking");
        List<Movie> movies = sortCtrl.allocateSort();

        // Force user to enter 'correct' input
        do {
            try {
                System.out.println("Select Movie: ");
                // Listing Movie + Session options
                for(int i = 0; i < movies.size(); i++) {
                    System.out.println((i + 1) + ". " + movies.get(i).getTitle() +
                    "(" + movies.get(i).getStatus() + ")");
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
                sc.nextLine();
            }
        } while(choice < 0 || choice >= movies.size());

        /* Debug */
        System.out.println("Selected Movie: " + movies.get(choice).getTitle());
        /* End Debug */
        this.movie = movies.get(choice);

        // Get sessions corresponding to the movie
        SessionController sessionCtrl = new SessionController(cineplex, cinema, choice);
        this.session = sessionCtrl.getSession("book");
    }

    
    /** 
     * Retrive seat info
     * @return int
     */
    public int getSeat() {
        BookSeatUI seatUI = new BookSeatUI(session.getSeatLayout(), session.getTotalSeats());
        session.setSeatLayout(seatUI.returnUpdatedLayout());     // session gets updated w/ new seating

        // Update the cinema session
        List<Session> sessions = cinema.getSessions();
        for(int i = 0; i < sessions.size(); i++) {
            if(session.getTimeSlot() == sessions.get(i).getTimeSlot()) {
                sessions.set(i, session);
            }
        }

        /* Debug */
        System.out.println("Seat id: " + seatUI.returnSeatID());
        Session.printSeatLayout(session.getSeatLayout());
        /* End Debug */

        return seatUI.returnSeatID();
    }

    
    /** 
     * Retrive age group
     * @return AgeGroup
     */
    public AgeGroup getAgeGroup() {
        int choice = -1;
        List<AgeGroup> ageGroups = Arrays.asList(AgeGroup.values());
        // Force user to enter 'correct' input
        do {
            try {
                System.out.println("Select Age group:");
                // Listing Age Group options
                for(int i = 0; i < ageGroups.size(); i++) {
                    System.out.println((i + 1) + ". " + ageGroups.get(i));
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
                sc.nextLine();
            }
        } while(choice < 0 || choice >= ageGroups.size());

        /* Debug */
        System.out.println("Selected AgeGroup: " + ageGroups.get(choice));
        /* End Debug */
        return ageGroups.get(choice);
    }

    
    /** 
     * Check booking date
     * @return boolean
     */
    public boolean checkBookingDate() {
        bookingDate = session.getTimeSlot().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        System.out.println("Booking date string: " + bookingDate);
        return TimeDateController.isHolidayORWeekend(bookingDate);
    }

    
    /** 
     * Display payment output
     * @param ticketPrice
     */
    public void displayPayment(double ticketPrice) {
        System.out.println("The ticket price will be: $" + ticketPrice);
        System.out.println("<< Enter any key to continue with payment >>");
        sc.next();

        // Payment magically works here

        System.out.println("Payment successful!");
    }
}