package Service.Sorting;

import java.util.Comparator;
import Model.DateI;
/**
 Sort DateI, Output in ascending order

 @author    Don Lim
 @verison   1.0
 @since     03-11-2022
 */

public class SortDateI implements Comparator<DateI>   
{  
    
    /** 
     * sort {@link DateI} data in ascending order
     * @param a
     * @param b
     * @return int
     */

    @Override  
    public int compare(DateI a, DateI b)  
    {  
        String day1 = a.date.substring(0, 2);
            String month1 = a.date.substring(3, 5);
            String year1 = a.date.substring(6);
           
            String day2 = b.date.substring(0, 2);
            String month2 = b.date.substring(3, 5);
            String year2 = b.date.substring(6);
             
            // Condition to check the year
            if (year2.compareTo(year1) > 0)
                return -1;
            else if (year2.compareTo(year1) < 0)
                return 1;
             
            // Condition to check the month    
            else if (month2.compareTo(month1) > 0)
                return -1;
            else if (month2.compareTo(month1) < 0)
                return 1;
             
            // Condition to check the day
            else if (day2.compareTo(day1) > 0)
                return -1;
            else
                return 1;
    }  
} 
