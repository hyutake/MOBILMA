package Model;

import java.io.Serializable;
/**
 * Represent the format of how a date will be store
 @author    Don Lim
 @verison   1.0
 @since     03-11-2022
 @see       Serializable
 */

public class DateI implements Serializable
{  
    public String date;  
    /**
     * The format of date stored
     * @param date      String type
     */ 
    public DateI(String date)  
    {   
        this.date = date;  
    } 
    
    
    /** 
     * Get String from {@link DateI}
     * @return String
     */
    public String getDate(){
        return date;
    }
}