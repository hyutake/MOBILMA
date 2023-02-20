package Controller.FileAccess;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import Controller.FileAccessController;
import Model.DateI;
/** 
 * Manages all the logic involving the reading of the file containing the holiday database
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-10-24
 */
public class HolidayFileAccess implements FileAccessController {
    public static final String fileName = "Holiday.dat";

    private List<DateI> holidays;

    /**
     * Constructor to init Holiday file access
     */
    public HolidayFileAccess() {
        if(FileAccessController.verifyFile(fileName)) {
            this.holidays = (ArrayList) FileAccessController.readSerializedObject(fileName);
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Missing " + fileName + " in " + (userDir + dirPath) + "\nRun database generator? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {
                // unlike the other databases (so far), Holiday.dat generation creates an empty .dat file according to initHolidayUI()
                // the below is to my understanding, the same as what initHolidayUI() does
                this.holidays = new ArrayList<DateI>();
                FileAccessController.writeSerializedObject(fileName, holidays);
            }
        }
    }

    /** To return the data from the database file for implementation code(s) that may require it
     * @return List      :Returns the List object read from the database (.dat) file
     */
    public List getData() {
        return holidays;
    }

    /** To update the database file's contents
     * @param o    Takes in the Object (should be) updated list 
     */
    public void writeData(Object o) {
        if(o instanceof List) {
            FileAccessController.writeSerializedObject(fileName, (List) o);
        }
        else {
            System.out.println("Error in writing holiday data!");
        }
    }
}
