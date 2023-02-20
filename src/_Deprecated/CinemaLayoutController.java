package _Deprecated;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import Controller.FileAccessController;

/**
 @author  
 @verison
 @since
 */
public class CinemaLayoutController {
	public static final String SEPARATOR = "|";
	private static Scanner x;
	public CinemaLayoutController() {}
	
	
	/** 
	 * @param filename
	 * @return ArrayList
	 * @throws IOException
	 */
	public static ArrayList readSeatLayout(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList) FileAccessController.readTxtObject(filename);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				while(star.hasMoreTokens()) {
					String  hall = star.nextToken().trim();
					String  cinemaclass = star.nextToken().trim();	
					String  movietype = star.nextToken().trim();	
					String  moviename = star.nextToken().trim();	
					String showtime = star.nextToken().trim();		
					int  row = Integer.parseInt(star.nextToken().trim()); 
					int  column = Integer.parseInt(star.nextToken().trim()); 
					SeatLayout seatlayout = new SeatLayout(hall,cinemaclass, movietype, moviename, showtime, row, column);
					alr.add(seatlayout) ;
				}
				
			}
			return alr ;
	}
	
	
	/** 
	 * @param filename
	 * @param al
	 * @throws IOException
	 */
	public static void saveSeatLayout(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store seatlayout  data

        for (int i = 0 ; i < al.size() ; i++) {
				SeatLayout seatlayout = (SeatLayout )al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(seatlayout.getHall().trim());
				st.append(SEPARATOR);
				st.append(seatlayout.getCinemaClass().trim());
				st.append(SEPARATOR);
				st.append(seatlayout.getMovieType().trim());
				st.append(SEPARATOR);
				st.append(seatlayout.getMovieName());
				st.append(SEPARATOR);
				st.append(seatlayout.getShowTime());
				st.append(SEPARATOR);
				st.append(seatlayout.getRow());
				st.append(SEPARATOR);
				st.append(seatlayout.getColumn());
				alw.add(st.toString()) ;
			}
        FileAccessController.writeTxtObject(filename,alw);
	}
	
	
	/** 
	 * @return SeatLayout
	 */
	public static SeatLayout inputSeatLayout() {
		Scanner scan = new Scanner(System.in);  
		SeatLayout p1;
			System.out.println("---Assignment of showtime---");
			System.out.println("Enter Hall Number: ");
			String hallinput= scan.next();
			System.out.println("Enter Cinema Class: ");
			String cinemaclass= scan.next();
			System.out.println("Enter Movie Type: ");
			String movietype= scan.next();
			System.out.println("Enter Movie Name with no space inbetween: ");
			String moviename= scan.next();
			System.out.println("Enter Showtime: ");
			String showtime= scan.next();
			if (cinemaclass.equals("Platinum")) {
				 p1 = new SeatLayout(hallinput,cinemaclass,movietype,moviename, showtime, 6,6);
			}
			else{
				 p1 = new SeatLayout(hallinput,cinemaclass,movietype,moviename, showtime, 9,9);
			}
			
			return p1;
	}
	
	
	/** 
	 * @param filename
	 */
	public static void editSeatLayout(String filename) {
		
		
		Scanner scan = new Scanner(System.in); 
    	System.out.println(" Input The Hall Number That You Wish to Edit Cinema Layout");
    	String input=scan.next();
    	String tempFile= "temp.txt";
    	File oldfile= new File(filename);
    	File newFile= new File(tempFile);
    	
    	String hallnumber =""; String cinemaclass=""; String movietype=""; String moviename=""; String showtime=""; String row=""; String column="";
    	try {
    		System.out.println("Enter Cinema Class: ");
			String cinemaclassinput= scan.next();
			System.out.println("Enter Movie Type: ");
			String movietypeinput= scan.next();
			System.out.println("Enter Movie Name with no space inbetween: ");
			String movienameinput= scan.next();
			System.out.println("Enter Showtime: ");
			String showtimeinput= scan.next();
			if(cinemaclassinput.equals("Platinum")) {
				row="6";
				column="6";
			}
			else {
				row="9";
				column="9";
			}
			FileWriter fw= new FileWriter(tempFile,true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			x= new Scanner(new File(filename));
			x.useDelimiter("[|,\n]");
			
			while(x.hasNext()) {
				hallnumber=x.next();
				cinemaclass=x.next();
				movietype=x.next();
				moviename=x.next();
				showtime=x.next();
				row=x.next();
				column=x.next();
				if(hallnumber.equals(input)){
					pw.println(hallnumber+"|"+cinemaclass+"|"+movietype+"|"+moviename+"|"+showtime+"|"+row+"|"+column);
				}
				else {
					pw.println(hallnumber+"|"+cinemaclassinput+"|"+movietypeinput+"|"+movienameinput+"|"+showtimeinput+"|"+row+"|"+column);
				}
			}
			
			x.close();
			pw.flush();
			pw.close();
			oldfile.delete();
			File dump= new File(filename);
			newFile.renameTo(dump);
			
    	}catch(Exception e) {
    		System.out.println("Error");
    		e.printStackTrace();
    	}
} 
	
	
}
