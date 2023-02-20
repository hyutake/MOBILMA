package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;

import Model.Session;

/** 
 * Displays the user interface to get user input for booking their desired seat
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class BookSeatUI {
    private int bookedSeatID = -1;

    private int[][] layout;
    private int totalSeats;

    /**
     * Creates the BookSeatUI module that handles the input for booking a particular seat
     * @param seatLayout
     * @param seatCount
     */
    public BookSeatUI(int[][] seatLayout, int seatCount) {
        this.layout = seatLayout;
        this.totalSeats = seatCount;
        do {
            Session.printSeatLayout(seatLayout);
            this.bookedSeatID = getSeatID();
        } while(bookedSeatID <= 0 || bookedSeatID > totalSeats);
        updateSeatLayout();
    }

    
    /** 
     * Provide seat ID
     * @return int
     */
    public int returnSeatID() {
        return bookedSeatID;
    }

    
    /** 
     * Provide updated layout
     * @return int[][]
     */
    public int[][] returnUpdatedLayout() {  // to update the layout in Cinema class
        return layout;
    }

    
    /** 
     * Get the seat id input from the Customer
     * @return int      Chosen seat id
     */
    public int getSeatID(){
        Scanner sc = new Scanner(System.in);
        int id = 0;

        do {
            try {
                System.out.print("Enter the seat id to book (only 1 allowed at a time): ");
                id = sc.nextInt();
            } catch(InputMismatchException e) {
                sc.nextLine();
            }
        } while(!isAvailableSeat(id));

        return id;
    }

    
    /** 
     * Check if seat is available 
     * @param id            User input for desired seat (1 to totalSeats)
     * @return boolean      True/False as to whether it is available or not
     */
    public boolean isAvailableSeat(int id) {
        if(id > totalSeats || id < 0) {
            System.out.println("Seat ID does not exist! Try again");
            return false;
        }

        int tempID = id - 1;  // back to zero-indexing

        int column = tempID % 10;
        tempID /= 10;
        int row = tempID % 10;

        try {
            int seatStatus = layout[row][column];
            return (seatStatus == 1) ? false : true;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid seat ID! Try again");
            return false;
        }
    }

    /**
     * Update seat layout
     */
    public void updateSeatLayout() {
        int tempID = bookedSeatID - 1;  // back to zero-indexing

        int column = tempID % 10;
        tempID /= 10;
        int row = tempID % 10;

        layout[row][column] = 1;
    }
}
