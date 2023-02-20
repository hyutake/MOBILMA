package _Deprecated;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 @author  
 @verison
 @since
 */
public class SeatUI {

	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		 CinemaLayoutController control= new  CinemaLayoutController();
    	String filename = "cinema1.txt" ;
    	int option=0;
    	
    	do {
    		try{
    			
                System.out.println("---Add/Update/View Cinema Layout(s)---");
                System.out.println("1. Input New Cinema Layout");
                System.out.println("2. Edit Current Cinema Layout");
                System.out.println("3. View All Cinema Layouts");
                System.out.println("4. View Movie Layout");
                System.out.println("5. Exit");

                option = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
                switch(option) {
                case 1:
        		try {
        			ArrayList al = control.readSeatLayout(filename) ;
        			SeatLayout p1= CinemaLayoutController.inputSeatLayout();
        			al.add(p1);
        			 CinemaLayoutController.saveSeatLayout(filename, al);
        		}catch (IOException e) {
        			System.out.println("IOException > " + e.getMessage());
        		}
             
                    break;
                case 2: 
        				control.editSeatLayout(filename);
                    break;
                case 3:
                	try {
                		ArrayList al = control.readSeatLayout(filename) ;
                		for (int i = 0 ; i < al.size() ; i++) {
            				SeatLayout seatlayout = (SeatLayout)al.get(i);
            				seatlayout.printlayoutheader();
            				System.out.print("\n");
            				seatlayout.showEmptySeats();
            			}
                	}catch (IOException e) {
            			System.out.println("IOException > " + e.getMessage());
            		}
                    break;
                case 4:
                	try {
                	Scanner scan = new Scanner(System.in); 
                    System.out.println("Input The Movie name with no space inbetween for Cinema Layout");
                    String input=scan.next();
            		ArrayList al = control.readSeatLayout(filename) ;
            		for (int i = 0 ; i < al.size() ; i++) {
        				SeatLayout seatlayout = (SeatLayout)al.get(i);
        				String checker =seatlayout.getMovieName();
        			if(checker.equals(input)) {
        				seatlayout.printlayoutheader();
        				System.out.print("\n");
        				seatlayout.showEmptySeats();
        			}
        			}
            		System.out.println("There is no Hall that is showing this movie");
            	}catch (IOException e) {
        			System.out.println("IOException > " + e.getMessage());
        		}
                	break;
                default:
                    System.out.println("Exiting . . .");
            }
    		
    	}while(option>=1 && option <5);
    
	}

}
