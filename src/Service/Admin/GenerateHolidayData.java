package Service.Admin;

import java.util.ArrayList;
import Controller.FileAccess.HolidayFileAccess;
import Controller.FileAccessController;
import Model.DateI;
/**
 * Initialises Holiday database
 * @author    Don Lim
 * @verison   1.0
 * @since     06-11-2022
 */
public class GenerateHolidayData {
    /**
     * Generate holiday database
     * @see DateI
     */
    public static void generateHoliday(){
        ArrayList<DateI> alist = new ArrayList<>();  
        
        //adding random holiday date
        alist.add(new DateI("22-11-2022"));
        alist.add(new DateI("23-11-2022"));
        alist.add(new DateI("06-12-2022"));
        alist.add(new DateI("25-12-2022"));
        alist.add(new DateI("26-12-2022"));

        FileAccessController.writeSerializedObject(HolidayFileAccess.fileName, alist);
    }
}
