package Controller.FileAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Controller.FileAccessController;
import Model.PriceList;
import Service.Admin.GeneratePrices;
/** 
 * Manages all the logic involving the reading of the file storing the pricing information
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
// File Accessor JUST FOR MoviePricing.dat (Ticket price data)
public class PriceFileAccess implements FileAccessController {
    public static final String fileName = "MoviePricing.dat";
    
    private List<PriceList> priceList;

    /**
     * Creates a PriceFileAccess module that verifies that the database file is valid
     * If the file does not exist, will prompt to generate a pre-set file
     */
    public PriceFileAccess() {
        if(FileAccessController.verifyFile(fileName)) {
            this.priceList = (ArrayList) FileAccessController.readSerializedObject(fileName);
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Missing " + fileName + " in " + (userDir + dirPath) + "\nRun database generator? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {
                GeneratePrices.generatePrices();
                this.priceList = (ArrayList) FileAccessController.readSerializedObject(fileName);
            }
            else {
                System.out.println("NOTE: priceList initialised as null");
            }
        }
    }

    
    /** 
     * @return List
     */
    public List getData() {
        return priceList;
    }

    
    /** 
     * @param o
     */
    public void writeData(Object o) {
        if(o instanceof List) {
            FileAccessController.writeSerializedObject(fileName, (List) o);
        }
        else {
            System.out.println("Error in writing price data!");
        }
    }

    
    /** 
     * Get the pricing information
     * @return PriceList
     */
    public PriceList getPrices() {
        return priceList.get(0);
    }

    
    /** 
     * Updates the pricing information in the stored database
     * @param prices
     */
    public void updatePrices(PriceList prices) {
        priceList.set(0, prices);
        writeData(priceList);
    }
}
