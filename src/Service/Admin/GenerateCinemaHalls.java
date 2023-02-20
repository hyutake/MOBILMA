package Service.Admin;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Constants.CinemaClass;
import Controller.FileAccessController;
import Controller.SortController;
import Model.Cinema;
import Model.Movie;
import Model.Session;

/** 
 * Initialises the Cineplex files by generating multiple cinema data for a single cineplex
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class GenerateCinemaHalls {
    /**
     * Time to be set between each movie session in the same day
     */
    private static int timeBetweenSessions = 165;      // 2h 45min in minutes

    /**
     * Generates the 3 (or more) cinemas for a cineplex as a .dat file
     * Only to be used for the initialisation of the database files
     * @param fileName      Name of the file to be saved as
     */
    public static void generateCinemaHalls(String fileName) {
        // Total no. of sessions PER cinema hall (changeable)
        int numOfSessions = 26;

        SortController sortCtrl = new SortController("booking");
        List<Movie> movies = sortCtrl.allocateSort();
        int maxMovies = movies.size();      // Needed for initial allocation of movie indexes

        List<Cinema> list = new ArrayList<>();
        LocalDate nextMonday = calcNextMonday(LocalDate.now());     // gets the next monday relative to current week
        LocalTime s1StartTime = LocalTime.of(9, 15);
        LocalTime s2StartTime = LocalTime.of(8, 00);
        LocalTime s3StartTime = LocalTime.of(8, 45);

        ArrayList<Session> sessions1 = generateSession(true, numOfSessions, maxMovies, nextMonday, s1StartTime);   // cinema 1
        ArrayList<Session> sessions2 = generateSession(false, numOfSessions, maxMovies, nextMonday, s2StartTime);   // cinema 2
        ArrayList<Session> sessions3 = generateSession(false, numOfSessions, maxMovies, nextMonday, s3StartTime);   // cinema 3

        switch(fileName) {
            case "CineplexPunggol.dat":
                list.add(new Cinema("ABC", CinemaClass.PREMIUM, sessions1));
                list.add(new Cinema("BCD", CinemaClass.STANDARD, sessions2));
                list.add(new Cinema("CDE", CinemaClass.STANDARD, sessions3));
                break;
            case "CineplexJurongEast.dat":
                list.add(new Cinema("DEF", CinemaClass.PREMIUM, sessions1));
                list.add(new Cinema("EFG", CinemaClass.STANDARD, sessions2));
                list.add(new Cinema("FGH", CinemaClass.STANDARD, sessions3));
                break;
            case "CineplexOrchard.dat":
                list.add(new Cinema("GHI", CinemaClass.PREMIUM, sessions1));
                list.add(new Cinema("HIJ", CinemaClass.STANDARD, sessions2));
                list.add(new Cinema("IJK", CinemaClass.STANDARD, sessions3));
                break;
            default:    // additional option just in case
                list.add(new Cinema("VWX", CinemaClass.PREMIUM, sessions1));
                list.add(new Cinema("WXY", CinemaClass.STANDARD, sessions2));
                list.add(new Cinema("XYZ", CinemaClass.STANDARD, sessions3));
        } 
         FileAccessController.writeSerializedObject(fileName, list);
    }
    
    /**
     * Generates ArrayList of several sessions, of random dates and pseudo-random timings
     * To be used as part of the initialisation of the database files
     * @param isPremium         Number of seats is tied to this check
     * @param numOfSessions     Total number of sessions to have in the generated ArrayList
     * @param maxMovies         Total number of movies (that can be booked by Customers)
     * @param startDate         Starting date, should be the next monday of the current week
     * @param startTime         Earliest time that a movie session will be held
     * @return Model.{@link Session}    Array List of sessions (for a single cinema)
     */
    public static ArrayList<Session> generateSession(boolean isPremium, int numOfSessions, int maxMovies, LocalDate startDate, LocalTime startTime) {
        // startDate is the next monday from the current week
        int totalSeats = (isPremium) ? 30 : 50;     // 30 for premium, 50 for standard
        int[] days = {0, 0, 0, 0, 0, 0, 0};     // to remember the offset to be added
        int[] daysPickCount = {0, 0, 0, 0, 0, 0, 0};    // to keep track of how many times a day was randomly chosen
        
        LocalDate date;
        ArrayList<Session> sessions = new ArrayList<>();
        // generate the array list of sessions
        for(int i = 0; i < numOfSessions; i++) {
            // randomize dates, but limit to within the next week and limit each day to 5 sessions
            int randDate = 0;
            do {
                randDate = randomNumberGenerator(0, 6);
                daysPickCount[randDate]++;
            } while(daysPickCount[randDate] >= 6);
            
            date = startDate.plusDays(randDate);
            sessions.add(new Session(i % maxMovies, totalSeats, LocalDateTime.of(date, startTime.plus(Duration.ofMinutes(days[randDate])))));
            days[randDate] += timeBetweenSessions;
        }

        return sessions;
    }

    /**
     * Find the LocalDate with the value of the Monday of next week from now
     * @param d     LocalDate.now()
     * @return LocalDate    LocalDate for next Monday
     */
    public static LocalDate calcNextMonday(LocalDate d) {
        return d.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
    }

    /**
     * Generates a random number between start and end
     * @param start     Start of the set of numbers to choose from
     * @param end       End of the set of numbers to choose from
     * @return int      Random value within the range of start to end
     */
    public static int randomNumberGenerator(int start, int end) {
        return new Random().nextInt((end - start) + 1) + start;
    }

}