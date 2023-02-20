package Controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Model.DateI;
import Controller.FileAccess.HolidayFileAccess;
/**
 * Get date, time.
 * Printed our in following format: 
 * DATE: dd-MM-yyyy Example: 06-12-2022 TIME: HH:mm:ss aaa Example: 04:12:69 PM
 * @author    Don Lim
 * @verison   1.1
 * @since     03-11-2022
 */

public class TimeDateController {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static SimpleDateFormat timeFormat= new SimpleDateFormat("HH:mm:ss aaa");
    static Calendar cal = Calendar.getInstance();
    static Date dateNow = cal.getTime();
    public static String sessionTimePattern = "dd-MM-yyyy || HH:mm";

    
    /** 
     * Get date
     * @return String
     */
    public static String getDate(){

        return dateFormat.format(dateNow);
    }

    
    /** 
     * Get Time
     * @return String
     */
    public static String getTime(){

        return timeFormat.format(dateNow);
    }

    
    /** 
     * Check if date is valid. i.e. the date must be after real/current date
     * @param date :String
     * @return boolean
     */
    public static boolean isDateValid(String date) 
    {
        try {
            dateFormat.setLenient(false);
            Date date1= dateFormat.parse(date);
            Date date2= dateFormat.parse(TimeDateController.getDate());

            if(date1.compareTo(date2)>=0)
                return true;
            else
                return false;

        } catch (ParseException e) {
            System.out.println("Please enter a valid date in this format(dd-MM-yyyy). Today Date: "+TimeDateController.getDate());
            return false;
        }

    }

    
    /** 
     * Logic to GetDateTime into a specific format 
     * @param dateTime :String
     * @return LocalDateTime
     */
    public static LocalDateTime getDateTime(String dateTime) {
        LocalDateTime localDateTime = LocalDateTime.MAX;
        try {
            localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(sessionTimePattern));
        } catch(DateTimeParseException e) {
            System.out.println("Invalid date time format!");
        }
        return localDateTime;
    }

    
    /** 
     * Check if is Weekend or is a holiday date stored in the database {@link HolidayFileAccess}
     * @param date :String
     * @return boolean
     */
    public static boolean isHolidayORWeekend(String date) 
    {
        ArrayList<DateI> alist = (ArrayList) FileAccessController.readSerializedObject(HolidayFileAccess.fileName);
        try {
            Date date1= dateFormat.parse(date);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);            

            if((cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)  || (cal1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
                return true;
            }
            else{        
                boolean flag= false;        
                for(int i = 0; i < alist.size(); i++) {
                    DateI dummy= alist.get(i);
                    if(date.equals(dummy.getDate())){
                        flag=true; 
                        return true;
                    }
                }
                if(!flag)
                    return false;
            }
        } catch (ParseException e) {
            return false;
        }

        return false;

    }
    
}


