package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Constants.CineplexLocation;
import Controller.FileAccessController;
import Controller.FileAccess.CineplexFileAccess;

/** 
 * Represents a cineplex
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-04
 */
public class Cineplex implements Serializable {
    /**
     * Location of the cineplex - the differing factor between cineplexes
     */
    private CineplexLocation location;

    /**
     * List of cinemas in the cineplex
     */
    private List<Cinema> cinemaHalls;

    public Cineplex(CineplexLocation location) {
        this.location = location;

        // cinemaHalls will get its list of cinemas from the file access controller for this
        FileAccessController cineplexAccess = new CineplexFileAccess(location);
        cinemaHalls = (ArrayList) cineplexAccess.getData();
    }
    
    /** 
     * Gets the location of the cineplex
     * @return CineplexLocation     Location of the cineplex
     */
    public CineplexLocation getLocation() {
        return location;
    }
    
    /** 
     * Sets the location of the cineplex
     * @param location      New location for the cineplex
     */
    public void setLocation(CineplexLocation location) {
        this.location = location;
    }
    
    /** 
     * Gets the list of cinemas in the cineplex
     * @return Model.{@link Cinema}     List of cinemas
     */
    public List<Cinema> getCinemaHalls() {
        return cinemaHalls;
    }
    
    /** 
     * Sets the list of cinemas in the cineplex
     * @param cinemaHalls       New list of cinemas
     */
    public void setCinemaHalls(List<Cinema> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }    
}
