package Controller.FileAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.FileAccessController;
import Model.Movie;
import Service.Admin.GenerateMovieFile;
/** 
 * Manages all the logic involving the reading of the file storing the movie database
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-10-14
 */
public class MovieFileAccess implements FileAccessController {
    public static final String fileName = "MovieDatabase.dat";

    /**
     * List of movies from the database file
     */
    private List<Movie> movies;

    /**
     * Creates a MovieFileAccess module that verifies that the database file is valid
     * If the file does not exist, will prompt to generate a pre-set file
     */
    public MovieFileAccess() {
        if(FileAccessController.verifyFile(fileName)) {
            this.movies = (ArrayList) FileAccessController.readSerializedObject(fileName);
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Missing " + fileName + " in " + (userDir + dirPath) + "\nRun database generator? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {
                GenerateMovieFile.generateMovie();
                this.movies = (ArrayList) FileAccessController.readSerializedObject(fileName);
            }
            else {
                System.out.println("NOTE: movies initialised as null");
            }
        }
    }

    
    /** 
     * Get the full list of movies
     * @return List
     */
    public List getData() {
        return movies;
    }

    
    /** 
     * @param o
     */
    public void writeData(Object o) {
        if(o instanceof List) {
            FileAccessController.writeSerializedObject(fileName, (List) o);
        }
        else {
            System.out.println("Error in writing movie data!");
        }
    }

    
    /** 
     * Updates the movie database by updating one of the existing movies
     * @param newMovie      New movie to replace the old one
     */
    public void updateMovie(Movie newMovie) {
        newMovie.setOverallRating();    // update overall rating
        for(int i = 0; i < movies.size(); i++) {
            Movie oldMovie = movies.get(i);
            if(oldMovie.getTitle().equals(newMovie.getTitle())) {   // find matching movie by title
                movies.set(i, newMovie);
            }
        }
        writeData(movies);
    }
}
