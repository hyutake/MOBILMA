package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Constants.CinemaClass;
/** 
 * Represents a cinema, where there are multiple of in a cineplex
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-04
 */
public class Cinema implements Serializable {
    /**
     *  Unique code for the cinema
     */
    private String cinemaCode;

    /**
     *  Class of the cinema
     */
    private CinemaClass cinemaClass;

    /**
     *  Movie watching sessions in the cinema
     */
    private ArrayList<Session> sessions;

    /**
     * Creates a Cinema that contains a unique cinemaCode, cinemaClass and several movie sessions in an ArrayList
     * @param cinemaCode    Unique identifier for the cinema, also used for TID
     * @param cinemaClass   Class of cinema - quality of seats and service
     * @param sessions      List of movie sessions for the cinema
     */
    public Cinema(String cinemaCode, CinemaClass cinemaClass, ArrayList<Session> sessions) {
        this.cinemaCode = cinemaCode;
        this.cinemaClass = cinemaClass;
        this.sessions = sessions;
    }

    /** 
     * Gets the unique identifier of the cinema
     * @return String   Cinema code
     */
    public String getCinemaCode() {
        return cinemaCode;
    }

    /**
     * Gets the class of the cinema
     * @return CinemaClass  Cinema class
     */
    public CinemaClass getCinemaClass() {
        return cinemaClass;
    }

    /**
     * Gets the list of sessions showing for the cinema
     * @return Model.{@link Session}    Arraylist of sessions
     */
    public ArrayList<Session> getSessions() {
        return sessions;
    }

    /**
     * Sets the list of sessions for the cinema
     * @param sessions      New list of sessions
     */
    public void setSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    
    /**
     * To compare if 2 cinemas are the same
     * @param c     Cinema to be compared against
     * @return boolean      Result of the matching
     */
    public boolean matchCinema(Cinema c) {
        return (c.getCinemaClass().equals(cinemaClass));
    }

    /**
     * To display the cinema information for debugging, formatted for readability
     * @return String   Cinema information
     */
    @Override
    public String toString() {
        printSessions();
        return "Cinema " + cinemaCode + "; cinemaClass = " + cinemaClass +
            "\n";
    }

    /**
     * To display the list of sessions in a more readable format
     */
    public void printSessions() {
        for(int i = 0; i < sessions.size(); i++) {
            sessions.get(i).printSession();
        }
    }
}