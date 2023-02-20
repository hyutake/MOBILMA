import java.util.ArrayList;

import Controller.FileAccessController;
import Controller.TimeDateController;

/**
 @deprecated No Longer in development
 */
public class LayoutApp {
    private static String cinema;
    private static String hall;

    final static String WALL="[|]";
    static int i=0;

    
    /** 
     * @param c
     */
    public static void layout(String c) {

		// read String from text file
		ArrayList stringArray = (ArrayList)FileAccessController.readTxtObject(c);

        for (i = 2 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
                String[] star= st.split(WALL);
                cinema= star[0];
                hall= star[1];

                


			}
    }

    
    /** 
     * @return String
     */
    public static String getCinema() {
        return cinema;
    }

    
    /** 
     * @return String
     */
    public static String getHall() {
        return hall;
    }
    
}
