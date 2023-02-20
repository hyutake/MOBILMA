package Boundary;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Constants.CinemaClass;
import Constants.CineplexLocation;
import Controller.SessionController;
import Controller.SortController;
import Controller.TimeDateController;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.Session;

/** 
 * Displays the user interface to get Admin input for session changes
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class AdminSessionUI {
    /**
     * List of movies that can be booked
     */
    private List<Movie> movies;

    /**
     * Cineplex chosen by Admin
     */
    private Cineplex cineplex;

    /**
     * Cinema chosen by Admin
     */
    private Cinema cinema;

    /**
     * Movie chosen by Admin
     */
    private Movie movie;

    /**
     * To handle the updating of database information when a change is made
     */
    private SessionController sessionCtrl;

    /**
     * Index of movie - linked to corresponding session
     */
    private int movieID;

    private int option = 0;

    private Scanner sc = new Scanner(System.in);

    /**
     * Creates an AdminSessionUI module that handles all the Admin input and the showing of prompts
     * Will pass on the duties of updating the database based on Admin input to Controller.{@link SessionController}
     */
    public AdminSessionUI() {
        SortController sortCtrl = new SortController("booking");
        this.movies = sortCtrl.allocateSort();
        this.cineplex = getCineplex();
        this.cinema = getCinema();
        getMovieAndID();

        this.sessionCtrl = new SessionController(cineplex, cinema, movieID);
        boolean debug = true;
        String dummy;
        do {
            Session s;
            option = getAdminChoice();
            dummy = sc.nextLine();
            switch(option) {
                case 1:
                    viewSessions();
                    break;
                case 2:
                    s = addSession();
                    sessionCtrl.updateCinemaSession(s, 'a', debug);
                    break;
                case 3:
                    s = updateSession();
                    sessionCtrl.updateCinemaSession(s, 'u', debug);
                    break;
                case 4:
                    s = removeSession();
                    sessionCtrl.updateCinemaSession(s, 'd', debug);
                    break;
                default:
                    System.out.println("Exiting...");
            }
        } while(option >= 1 && option < 5);
    }

    
    /** 
     * Display interface choices and takes Admin's input
     * @return int
     */
    public int getAdminChoice() {
        int choice = 0;
        System.out.println("=== Session UI (" + movie.getTitle() + ") ===");
        System.out.println("1. View sessions");
        System.out.println("2. Add session");
        System.out.println("3. Edit session");
        System.out.println("4. Delete session");
        System.out.println("5. Exit");
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
        }
        return choice;
    }

    /**
     * Display available sessions for a particular movie
     */
    public void viewSessions() {
        List<Session> movieSessions = sessionCtrl.getMovieSessions();
        for(int i = 0; i < movieSessions.size(); i++) {
            movieSessions.get(i).printSession();
        }
    }

    
    /**
     * Add a session to the chosen cinema
     * @return Session      New session that was created
     */
    public Session addSession() {
        int seats;
        seats = (cinema.getCinemaClass() == CinemaClass.PREMIUM) ? 30 : 50;

        LocalDateTime sessionTimeSlot = LocalDateTime.MAX;
        do {
            System.out.print("Enter the timeslot to be assigned (DD-MM-YYYY || HH:MM): ");
            String dateTime = sc.nextLine();
            sessionTimeSlot = TimeDateController.getDateTime(dateTime);
        } while(sessionTimeSlot == LocalDateTime.MAX);
        return new Session(movieID, seats, sessionTimeSlot);
    }

    
    /** 
     * Update an existing session in the chosen cinema
     * @return Session      Updated session
     */
    public Session updateSession() {
        Session s = sessionCtrl.getSession("edit");

        LocalDateTime sessionTimeSlot = LocalDateTime.MAX;
        do {
            System.out.print("Enter the new timeslot to be assigned (DD-MM-YYYY || HH:MM): ");
            String dateTime = sc.nextLine();
            sessionTimeSlot = TimeDateController.getDateTime(dateTime);
        } while(sessionTimeSlot == LocalDateTime.MAX);

        sessionCtrl.updateCinemaSession(s, 'd', false); // delete existing session 
        s.setTimeSlot(sessionTimeSlot);
        return s;
    }

    
    /** 
     * Delete a session
     * @return Session      Session to be deleted
     */
    public Session removeSession() {
        return sessionCtrl.getSession("delete");
    }

    
    /** 
     * Display options and get Admin input for choice of cineplex
     * @return Cineplex     Chosen cineplex
     */
    public Cineplex getCineplex() {
        int choice = -1;
        List<CineplexLocation> locations = Arrays.asList(CineplexLocation.values());
        
        // Force user to enter 'correct' input
        do {
            try {
                System.out.println("Select cineplex: ");
                // Listing Age Group options
                for(int i = 0; i < locations.size(); i++) {
                    System.out.println((i + 1) + ". " + locations.get(i));
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
            }
        } while(choice < 0 || choice >= locations.size());

        /* Debug */
        System.out.println("Selected Cineplex at location: " + locations.get(choice));
        /* End Debug */
        return new Cineplex(locations.get(choice));
    }

    
    /** 
     * Display options and get Admin input for choice of cinema
     * @return Cinema       Chosen cinema
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
                    System.out.println((i + 1) + ". Cinema Hall " + (i + 1));
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
            }
        } while(choice < 0 || choice >= cinemaHalls.size());

        /* Debug */
        System.out.println("Selected Cinema: " + cinemaHalls.get(choice).getCinemaCode());
        /* End Debug */
        return cinemaHalls.get(choice);
    }

    /**
     * Display available movies and gets Admin input for the movie choice as well as it's index
     */
    public void getMovieAndID() {
        int choice = -1;
        do {
            try {
                System.out.println("Select Movie: ");
                // Listing Movie options
                for(int i = 0; i < movies.size(); i++) {
                    System.out.println((i + 1) + ". " + movies.get(i).getTitle());
                }
                choice = sc.nextInt() - 1;  // zero-index the choice input
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
            }
        } while(choice < 0 || choice >= movies.size());

        /* Debug */
        System.out.println("Selected Movie: " + movies.get(choice).getTitle());
        /* End Debug */

        this.movie = movies.get(choice);
        this.movieID = choice;
    }

}