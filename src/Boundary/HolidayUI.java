package Boundary;

import java.util.ArrayList;
import java.util.Collections;

import Model.DateI;
import Service.Sorting.SortDateI;
import Controller.FileAccessController;
import Controller.TimeDateController;
import Controller.FileAccess.HolidayFileAccess;
/**
 * Ability to save/delete/view date that is store in the database. 
 * Date is sorted in ascending order. 
 * @author      Don Lim
 * @verison     1.2
 * @since       03-11-2022
 * @see         SortDateI
  */

public class HolidayUI{
    private final static String HolidayDatabase= HolidayFileAccess.fileName;

    /**
     * Sort Holiday
     * @see SortDateI
     */
    private static void sortHoliday(){
        ArrayList<DateI> alist = (ArrayList) FileAccessController.readSerializedObject(HolidayDatabase);
        Collections.sort(alist,new SortDateI());
        FileAccessController.writeSerializedObject(HolidayDatabase, alist);
    }

    /**
     * Print out holiday
     */
    public static void getHoliday(){
        System.out.println("Listing dates (dd-MM-yyyy): ");
        ArrayList<DateI> alist = (ArrayList) FileAccessController.readSerializedObject(HolidayDatabase);
        for (DateI d : alist)   
        {  
            System.out.println(d.date);  
        }  
    }

    
    /** 
     * Store a valid date into the database
     * @param date :String
     */
    public static void setHolidayDate(String date){
        if(TimeDateController.isDateValid(date)){
            ArrayList<DateI> alist= (ArrayList) FileAccessController.readSerializedObject(HolidayDatabase);
            alist.add(new DateI(date));  
            FileAccessController.writeSerializedObject(HolidayDatabase, alist);
            sortHoliday();
            System.out.println("Successfully added!");
        }
    }

    
    /** 
     * Delete a date stored in the database
     * @param date :String
     */
    public static void deleteHolidayDate(String date){
        if(TimeDateController.isDateValid(date)){
            ArrayList<DateI> alist= (ArrayList) FileAccessController.readSerializedObject(HolidayDatabase);
            boolean flag= false;
            if(alist.size()==0)
                System.out.println("Not found!");
            else{
                for(int i = 0; i < alist.size(); i++) {
                    DateI dummy= alist.get(i);
                    if(date.equals(dummy.getDate())){
                        alist.remove(dummy);
                        flag=true;  
                        FileAccessController.writeSerializedObject(HolidayDatabase, alist);
                        System.out.println("Successfully deleted!");
                        break;
                    }
                }
                if(!flag)
                    System.out.println("Not found!");
            }
        }
    }

    /**
     * Sync Holiday to real/current time & delete dates no longer valid
     * @see TimeDateController
     */
    public static void syncHoliday() {
        ArrayList<DateI> alist= (ArrayList) FileAccessController.readSerializedObject(HolidayDatabase);

        for(int i = 0; i < alist.size(); i++) {
            DateI dummy= alist.get(i);
            if(!TimeDateController.isDateValid(dummy.getDate())){
                alist.remove(dummy);
                i=0;
            }
            else{
                break;
            }     
        }
        FileAccessController.writeSerializedObject(HolidayDatabase, alist);
    }
}


