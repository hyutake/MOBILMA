package _Deprecated;

import java.util.Arrays;
import java.io.Serializable;
/**
 @author  
 @verison
 @since
 */
public class SeatLayout implements Serializable{

	private Seat[][] layout;
	private String hall;
	private String  cinemaclass;
	private String  movietype ;
	private String  moviename ;
	private String showtime ;
	private int row;
	private int column;
	
	public SeatLayout() {}
	
	public SeatLayout(String hall,String cinemaclass, String movietype, String moviename, String showtime,int row, int column) {
		this.hall=hall;
		this.cinemaclass=cinemaclass;
		this.movietype=movietype;
		this.moviename=moviename;
		this.showtime=showtime;
		this.row=row;
		this.column=column;
		layout= new Seat[row][column];
		for (int i=0; i<row;i++) {
			for( int j =0; j<column;j++) {
					layout[i][j]= new Seat(i*this.row+j);
				}	
			}
		}

	
	
	
	public void showEmptySeats() {
	System.out.print("\nX means occupied, 0 means not occupied.\n");
	for (int i = 0; i < row; i++) {
		System.out.print("Seats " + (i * column) + " - " + ((i + 1) * column - 1) + ":\t");
		for (int j = 0; j < column; j++) {
			if(layout[i][j].isOccupied()) System.out.print("X ");
			else System.out.print("0 ");
			if(j == column/2 - 1) System.out.print("\t");
		}
		System.out.print("\n");
		}
	}
	
	
	/** 
	 * @return String
	 */
	public String getHall() {
		return this.hall;
	}

	
	/** 
	 * @param hall
	 */
	public void setHall(String hall) {
		 this.hall=hall;
	}
	
	/** 
	 * @return String
	 */
	public String getCinemaClass() {
		return this.cinemaclass;
	}
	
	/** 
	 * @param cinemaclass
	 */
	public void setCinemaClass(String cinemaclass) {
		 this.cinemaclass=cinemaclass;
	}
	
	/** 
	 * @return String
	 */
	public String getMovieType() {
		return this.movietype;
	}
	
	
	/** 
	 * @param movietype
	 */
	public void setMovieType(String movietype) {
		 this.movietype=movietype;
	}
	
	
	/** 
	 * @return String
	 */
	public String getMovieName() {
		return this.moviename;
	}
	
	/** 
	 * @param moviename
	 */
	public void setMovieName(String moviename) {
		 this.moviename=moviename;
	}
	
	
	/** 
	 * @return String
	 */
	public String getShowTime() {
		return this.showtime;
	}
	
	/** 
	 * @param showtime
	 */
	public void setShowTime(String showtime) {
		 this.showtime=showtime;
	}
	
	
	/** 
	 * @return int
	 */
	public int getRow() {
		return this.row;
	}
	
	
	/** 
	 * @return int
	 */
	public int getColumn() {
		return this.column;
	}
	
	
	/** 
	 * @return Seat[][]
	 */
	public Seat[][] getLayout(){
		return this.layout;
	}
	
	public void printlayoutheader() {
		System.out.println("Hall number:" +hall);
		System.out.println("Cinema Class: "+ cinemaclass);
		System.out.println("Movie Type: "+ movietype);
		System.out.println("Movie Name: "+ moviename);
		System.out.println("Showtime: "+ showtime);
	}
	
	/** 
	 * @param seatID
	 */
	public void assignSeat(int seatID) {
		
				if( checkSeats(seatID) == false) {
					int i = seatID/row;
					layout[i][seatID - row*i].assign();
					System.out.println("Seat Assigned!");
				}
				else
					System.out.println("Seat already assigned to a customer.");
	}
	
	
	/** 
	 * @param seatID
	 */
	public void unAssignSeat(int seatID) {
				if(checkSeats(seatID)== true) {
					int i = seatID/row;
					layout[i][seatID - row*i].unAssign();
					System.out.println("Seat Unassigned!");
				}
	}
	
	
	/** 
	 * @param seatID
	 * @return boolean
	 */
	public boolean checkSeats(int seatID) {
		int i = seatID/row;
		return layout[i][seatID - row*i].isOccupied();
	}
}
