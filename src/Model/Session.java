package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Controller.TimeDateController;

/** 
 * Represents a single cinema session where a movie will be played
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-07
 */
public class Session implements Serializable {
    /**
     * No. of columns == No. of seats in a row
     */
    private int columns = 10;
    /**
     * The movie that this session is 'tied' to
     */
    private int movieID;
    /**
     * Total number of seats
     */
    private int totalSeats;
    /**
     * Seating layout to store the state of the seats (booked / not booked)
     */
    private int[][] seatLayout;
    /**
     * Time slot (date and time) for the movie session
     */
    private LocalDateTime timeSlot;     // Contains both date and time

    /**
     * Creates a session for a specific movie in a cinema
     * @param movieID       Identifier for the movie to be shown
     * @param totalSeats    Total number of seats
     * @param timeSlot      Time slot allocated for movie showing
     */
    public Session(int movieID, int totalSeats, LocalDateTime timeSlot) {
        this.movieID = movieID;
        this.totalSeats = totalSeats;
        this.seatLayout = generateSeatLayout();
        this.timeSlot = timeSlot;
    }

    /**
     * Gets the movie identifier that the session is for
     * @return int  Movie identifier
     */
    public int getMovieID() {
        return movieID;
    }

    /**
     * Sets the new movie that the session is for
     * @param movieID   New movie identifier
     */
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    
    /**
     * Gets the number of seats in a row
     * @return int  Number of columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Sets the number of seats in a row
     * @param columns   New number of seats
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Gets the assigned timeslot for the movie session
     * @return LocalDateTime    Date and Time of the movie session
     */
    public LocalDateTime getTimeSlot() {
        return timeSlot;
    }

    /**
     * Sets a new timeslot for the movie session
     * @param timeSlot  New timeslot for the movie session
     */
    public void setTimeSlot(LocalDateTime timeSlot) {
        this.timeSlot = timeSlot;
    }

    /**
     * Gets the total number of seats in the cinema hall session
     * @return int  Total number of seats
     */
    public int getTotalSeats() {
        return totalSeats;
    }

    /**
     * Sets the total number of seats in the cinema hall session
     * @param totalSeats    New total number of seats
     */
    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    /**
     * Gets the logical seating layout in terms of which is assigned and which is free
     * @return int[][]  Seating assignments based on bookings done
     */
    public int[][] getSeatLayout() {
        return seatLayout;
    }

    /**
     * Sets the logical seating layout
     * @param seatLayout    New seating layout
     */
    public void setSeatLayout(int[][] seatLayout) {
        this.seatLayout = seatLayout;
    }

    /**
     * Get the timeslot in the form of a String, formatted for readability
     * @return String   Timeslot
     */
    public String getStringTimeSlot() {
        return getTimeSlot().format(DateTimeFormatter.ofPattern(TimeDateController.sessionTimePattern));
    }

    /**
     * Generates the initial seating arrangements based on total seats and column size
     * @return int[][]      Initial seating arrangement
     */
    private int[][] generateSeatLayout(){
        int rows = totalSeats / columns;

        int[][] layout = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                layout[i][j] = 0;
            }
        }
        return layout;
    }

    /**
     * Prints the seating layout in a more readable format, mapping certain values to certain strings to be printed in their place
     * @param seatLayout    The current logical seating layout for the session
     */
    public static void printSeatLayout(int[][] seatLayout) {
        int seatID = 1;
        for(int i = 0; i < seatLayout.length; i++) {    // Row
            for(int j = 0; j < seatLayout[i].length; j++) {     // Column
                if(j == seatLayout[i].length / 2) {
                    System.out.print(" __ ");  // Add ' __ ' at the midpoint
                }

                String c = " ";
                if(seatLayout[i][j] == 0) {
                    c = "[" + (seatID < 10 ? "0" + seatID : seatID) + "]";
                }
                else if(seatLayout[i][j] == 1) {
                    c = "[XX]";
                }
                System.out.print(c);
                seatID++;
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints all the session information, for Admin side use
     */
    public void printSession() {
        System.out.println("=== Session " + movieID +  " ===\ncolumns = " + columns + "; totalSeats = " + totalSeats 
        + "; timeSlot = " + getStringTimeSlot() + "\nseatLayout:\n");
        printSeatLayout(seatLayout);
    }
}