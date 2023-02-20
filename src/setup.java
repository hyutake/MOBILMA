import Boundary.*;
import Constants.*;
import Controller.*;
import Controller.FileAccess.*;
import Exceptions.*;
import Model.*;
import Service.*;
import Service.Admin.*;
import Service.Sorting.*;

/**
 * @author      Cai Kaihang
 * @author      Don Lim
 * @verison     1.1
 * @since       06-11-2022
 */
public class setup {

    /** 
     * Entry point for program to run setup
     * @param args
     */
    public static void main(String[] args) {
        /* File generators */
        GenerateCinemaHalls.generateCinemaHalls("CineplexPunggol.dat");
        GenerateCinemaHalls.generateCinemaHalls("CineplexJurongEast.dat");
        GenerateCinemaHalls.generateCinemaHalls("CineplexOrchard.dat");
        GenerateMovieFile.generateMovie();
        GeneratePrices.generatePrices();
        GenerateHolidayData.generateHoliday();
        System.out.println("Please create an admin account");
        GenerateUserData.generateUsers();
        // MainApp.main(args);
    }
}
