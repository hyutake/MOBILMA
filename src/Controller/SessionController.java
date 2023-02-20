package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Controller.FileAccess.CineplexFileAccess;
import Model.Cinema;
import Model.Cineplex;
import Model.Session;

/** 
 * Manages all the logic involving the sessions for a particular movie booking
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-05
 */
public class SessionController {
    /**
     * Cineplex where the cinema that contains the particular session is from
     */
    private Cineplex cineplex;

    /**
     * Cinema that contains the session
     */
    private Cinema cinema;

    /**
     * All the sessions available from the cinema
     */
    private ArrayList<Session> cinemaSessions;

    /**
     * All the sessions from the cinema that is for a specific movie
     */
    private ArrayList<Session> movieSessions = new ArrayList<>();

    /**
     * Creates the SessionController that will initialise and find all the sessions for the given movie ID
     * @param cineplex      Cineplex chosen
     * @param cinema        Cinema chosen
     * @param movieID       Movie chosen as well as the linking factor between a session and a movie
     */
    public SessionController(Cineplex cineplex, Cinema cinema, int movieID) {
        this.cineplex = cineplex;
        this.cinema = cinema;
        cinemaSessions = cinema.getSessions();

        // Find matching sessions with movieID
        for(int i = 0 ; i < cinemaSessions.size(); i++) {
            Session s = cinemaSessions.get(i);
            if(s.getMovieID() == movieID) {
                movieSessions.add(s);
            }
        }

        sortByTime();   // sort movieSessions by showtime
    }

    
    /**
     * Updates the cinemaSession, which involves the adding, editing and deleting of sessions
     * @param s         The session involved in the update
     * @param task      To add/edit/delete
     * @param debug     To toggle the showing of the message for debugging purposes
     */
    public void updateCinemaSession(Session s, char task, boolean debug) {
        switch(task) {
            case 'a':   // add
                if (cinemaSessions.add(s) && debug) System.out.println("Successfully added session!");
                movieSessions.add(s);
                break;
            case 'd':   // delete
                if(cinemaSessions.remove(s) && debug) System.out.println("Successfully deleted session!");
                movieSessions.remove(s);
                break;
            case 'u':   // edit
                if (cinemaSessions.add(s) && debug) System.out.println("Successfully updated session!");
                movieSessions.add(s);
                break;
            default:
                System.out.println("Invalid option!");
        }
        // update file
        cinema.setSessions(cinemaSessions);
        CineplexFileAccess cineplexAccess = new CineplexFileAccess(cineplex.getLocation());
        cineplexAccess.updateCinema(cinema);
    }

    
    /** 
     * Retrieve the sessions for a specific movie in a cinema
     * @return List<Session>    List of sessions
     */
    public List<Session> getMovieSessions() {
        return movieSessions;
    }

    
    /** 
     * Displays the sessions to be selected and gets Admin input
     * @param prompt        To indicate the purpose of admin input (update / delete)
     * @return Session      Chosen session
     */
    public Session getSession(String prompt) {
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        do {
            try {
                System.out.println("Select session to " + prompt + ":");
                for(int i = 0; i < movieSessions.size(); i++) {
                    Session s = movieSessions.get(i);
                    System.out.println((i + 1) + ". " + s.getStringTimeSlot());
                }
                choice = sc.nextInt() - 1;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Try again");
                sc.nextLine();
            }
        } while(choice < 0 || choice >= movieSessions.size());

        return movieSessions.get(choice);
    }

    /**
     * Sorts the list of movie sessions by time
     */
    public void sortByTime() {
        Collections.sort(movieSessions, (Session one, Session other) -> {
            return one.getTimeSlot().compareTo(other.getTimeSlot());
        });
    }
}