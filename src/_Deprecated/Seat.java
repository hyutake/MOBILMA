package _Deprecated;
/**
 @author  
 @verison
 @since
 */

public class Seat {
	
	private int seatID;
	private boolean assigned=false;
	
	public Seat() {
	}
	
	public Seat(int seatID) {
		this.seatID=seatID;
	}
	
	public Seat(int seatID, boolean assigned) {
		this.seatID=seatID;
		this.assigned=assigned;
	}
	
	
	/** 
	 * @return int
	 */
	public int getseatID() {
		return this.seatID;
	}
		
	
	/** 
	 * @return boolean
	 */
	public boolean isOccupied() {
		return this.assigned;
	}
	
	public void assign() {
		this.assigned= true;
	}
	
	public void unAssign() {
		this.assigned= false;
	}

}

