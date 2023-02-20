package Controller.FileAccess;

import java.util.List;
import java.util.Scanner;

import Constants.CineplexLocation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;

import Controller.FileAccessController;
import Model.Cinema;
import Service.Admin.GenerateCinemaHalls;

/** 
 * Manages all the logic involving the reading of the Cineplex<Location>.dat files
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-04
 */
public class CineplexFileAccess implements FileAccessController, Serializable {
    /**
     * Mapping of CinemaLocation enum to their respective filenames
     */
    private EnumMap<CineplexLocation, String> cineplexFiles = new EnumMap<>(CineplexLocation.class);

    /**
     * List of cinemas in the cineplex
     */
    private List<Cinema> cinemas;

    /**
     * File name of the file-to-be-accessed
     */
    public String fileName;

    /**
     * Creates the CineplexFileAccess module that will read from one of the cineplex files
     * Cineplex file that is read from will depend on the location given as the parameter
     * @param location          Identifier for the cineplex and it's corresponding file to be read
     */
    public CineplexFileAccess(CineplexLocation location) {    // slightly different handling - because this may read 1 out of several .dat files
        // mapping files-to-be-read to location
        initializeFileMap();

        this.fileName = cineplexFiles.get(location);
        if(FileAccessController.verifyFile(fileName)) {
            this.cinemas = (ArrayList) FileAccessController.readSerializedObject(fileName);
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Missing " + fileName + " in " + (userDir + dirPath) + "\nRun database generator? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {
                GenerateCinemaHalls.generateCinemaHalls(fileName);
                this.cinemas = (ArrayList) FileAccessController.readSerializedObject(fileName);
            }
            else {
                System.out.println("NOTE: cinema halls initialised as null");
            }
        }
    }

    /**
     * Initialises the mapping of locations to filenames
     */
    private void initializeFileMap() {
        cineplexFiles.put(CineplexLocation.JURONG_EAST, "CineplexJurongEast.dat");
        cineplexFiles.put(CineplexLocation.PUNGGOL, "CineplexPunggol.dat");
        cineplexFiles.put(CineplexLocation.ORCHARD, "CineplexOrchard.dat");
    }

    
    /** 
     * Get the list of cinemas in the specified cineplex
     * @return List
     */
    public List getData() {
        return cinemas;
    }

    
    /** 
     * Funtion to write data into database
     * @param o
     */
    public void writeData(Object o) {
        if(o instanceof List) {
            FileAccessController.writeSerializedObject(fileName, (List) o);
        }
        else {
            System.out.println("Error in writing cinema hall data!");
        }
    }

    
    /** 
     * Updates the cinema information stored in the database
     * @param newCinema     The updated cinema information
     */
    public void updateCinema(Cinema newCinema) {
        for(int i = 0; i < cinemas.size(); i++) {
            Cinema oldCinema = cinemas.get(i);
            if(oldCinema.matchCinema(newCinema)) {     // matching cinema found
                cinemas.set(i, newCinema);
            }
        }
        writeData(cinemas);
    }
}
