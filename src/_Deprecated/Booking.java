package _Deprecated;

import java.util.*;

import Model.Movie;

/**
 @deprecated No Longer in development
 */
public class Booking {
 
	//private Customer customer; //name, mobile, email, age grp, 
	private Seats seat;
	private Movie movie; //title and showtime? 
	private String TID; //tid is made up of time and date (timestamp and unique code we can come up with) 
	private CinemaClass cinemaClass;
	private double price;
	private List<Booking> pastBookings;
	//Seats seat = new Seats();
	
	public Booking(Seats seat, Movie movie, String TID, CinemaClass cinemaClass, double price, List<Booking> pastBookings) {
		//this.customer = customer;
		this.seat = seat;
		this.movie = movie;
		this.TID = TID;
		this.cinemaClass = cinemaClass; 
		this.price = price;
		this.pastBookings = pastBookings;
	}
	
	
	/** 
	 * @return Seats
	 */
	public Seats getSeat() {
		return seat;
	}
	
	
	/** 
	 * @param seat
	 * @return Seats
	 */
	public Seats setSeat(Seats seat) {
		this.seat = seat;
	}
	
	
	/** 
	 * @return Movie
	 */
	public Movie getMovie() {
		return movie;
	}
	
	
	/** 
	 * @param movie
	 * @return Movie
	 */
	public Movie setMovie(Movie movie) {
		this.movie = movie;
	}
	
	
	/** 
	 * @return String
	 */
	public String getTID() {
		return TID;
	}
	
	
	/** 
	 * @param TID
	 * @return String
	 */
	public String setTID(String TID) {
		this.TID = TID;
	}
	
	
	/** 
	 * @return cinemaClass
	 */
	public cinemaClass getCinemaClass() {
		return cinemaClass;
	}
	
	
	/** 
	 * @param cinemaClass
	 * @return cinemaClass
	 */
	public cinemaClass setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}
	
	
	/** 
	 * @return double
	 */
	public double getPrice() {
		return price;
	}
	
	
	/** 
	 * @param price
	 * @return double
	 */
	public double setPrice(double price) {
		this.price = price;
	}
	
	
	/** 
	 * @return List<Booking>
	 */
	public List<Booking> getPastBookings() {
		return pastBookings;
	}
	
	
	/** 
	 * @param pastBookings
	 * @return List<Booking>
	 */
	public List<Booking> setPastBookings(List<Booking> pastBookings) {
		this.pastBookings = pastBookings;
	}
	
	
	/** 
	 * @param bookings
	 * @return String
	 */
	//store seat, movie, TID, cinemaclass and price
	public String bookingString(List bookings) {
		StringBuilder st = new StringBuilder();
		for(int i=0; i<bookings.size(); i++) {
			st.append("\n");
            st.append((i + 1) + ". " + bookings.get(i).toString());
            st.append("\n");
		}
		return st.toString();
	}

}
