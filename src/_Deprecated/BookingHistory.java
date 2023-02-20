package _Deprecated;

import java.io.Serializable;
/**
 @deprecated No Longer in development
 */

//show booking history of that customer, so need show TID, customer info, ticket details etc
public class BookingHistory implements Serializable {

	private final String TID; 
	private final Customer customer;
	private final Seats seat;
	
	public BookingHistory(String TID, Customer customer, Seats seat) {
		this.TID = TID; 
		this.customer = customer;
		this.seat = seat;
	}
	
	
	/** 
	 * @return String
	 */
	//print out the name, mobile no. and email address of customer, also printout the
	//movie title, showtime and seat position (system capture alr might as well printout)
	public String toString() {
		return TID + "\n" +
				"Name: " + customer.getName() + "\n" +
				"Mobile: " + customer.getMobile() + "\n" +
				"Email: " + customer.getEmail() + "\n" + 
				"Seat: " + seat.getseatID() + "\n";
				
	}
}
