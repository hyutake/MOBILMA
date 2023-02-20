import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.String;
import java.util.regex.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Controller.FileAccessController;
import Controller.TimeDateController;
import Boundary.AdminUI;
import Boundary.HolidayUI;
import Boundary.SortListUI;
import Boundary.UserUI;

/**
 @deprecated No Longer in development
Benefits:
Password is all hash, SHA256
Password must have standard security feature. i.e. special chara, Upper&lower, Number, 8-20chara
Stored in seperated Database
Only an admin can create a new admin account
Database validation before running main program
Sync database to Real/current time before starting the program
*/

public class LandingPageMain {
    static final Scanner sc = new Scanner(System.in);

    static final String AdminDatabase =  "AdminDatabase.dat";
    static final String CustomerDatabase =  "CustomerDatabase.dat";
    static final String MovieDatabase = "MovieDatabase.dat";
    static final String HolidayDatabase = "Holiday.dat";
    static final String PricingDatabase = "MoviePricing.dat";

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        int option = 0; 

        //FOR DEBUGGING
        //new AdminUI();
        //UserUI.UserApp();

        /*
        //SAMPLE FOR CHECKING
        // WEEKEND
        System.out.println(TimeDateController.isHolidayORWeekend("05-11-2022"));
        System.out.println(TimeDateController.isHolidayORWeekend("20-11-2022"));
        //DATABASE
        System.out.println(TimeDateController.isHolidayORWeekend("09-11-2022"));
        System.out.println(TimeDateController.isHolidayORWeekend("02-12-2022"));
        //DEAD
        System.out.println(TimeDateController.isHolidayORWeekend("10-10-2022"));
        System.out.println(TimeDateController.isHolidayORWeekend("25-12-2022"));
        */

        
        /*
        Check for database existence: Admin & Customer database
        Prompt user to init default configuration, if user deny, program terminate
         */
        try {
            // NEED this format cause i want this exception for better control of the flow...
            Scanner scanner = new Scanner(new FileInputStream(FileAccessController.Database()+AdminDatabase));
            Scanner scanner2 = new Scanner(new FileInputStream(FileAccessController.Database()+CustomerDatabase));
            scanner.close();
            scanner2.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("WARNING! DATABASE NOT FOUND! Create login database? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y'){
                List<Database> list = new ArrayList<>();
                list.add(new Database());
                FileAccessController.writeSerializedObject(AdminDatabase, list);
                FileAccessController.writeSerializedObject(CustomerDatabase, list);
                    //Default admin credentials
                    //User: admin
                    //Password: DeleteM@1
                List tlist = (ArrayList) FileAccessController.readSerializedObject(AdminDatabase);       
                Database data= new Database();
                data.setUser("admin");
                data.setPassword("8b99cca4deb0b7e39d6381b0546d26e0af66c82e0773875bf7b692a77be8a99f");
                tlist.add(data);
                FileAccessController.writeSerializedObject(AdminDatabase, tlist);
            }
            else
                System.exit(0);
            
		}

        /*
        Check for Movie database existence
        Only an admin can login at this point of time
        Not allow to create any new account both admin and customer
         */
        try {
            // NEED this format cause i want this exception for better control of the flow...
            Scanner scanner = new Scanner(new FileInputStream(FileAccessController.Database()+MovieDatabase));
            Scanner scanner2 = new Scanner(new FileInputStream(FileAccessController.Database()+HolidayDatabase));
            Scanner scanner3 = new Scanner(new FileInputStream(FileAccessController.Database()+PricingDatabase));
            scanner.close();
            scanner2.close();
            scanner3.close();

        } catch (FileNotFoundException e) {
            System.out.println("WARNING! CRITICAL DATABASE NOT FOUND! Login to complete initialization.");
            System.out.println("-----Admin login-----");
                if(false!=LoginFlow(1, 0))
                    new AdminUI();
                else
                    System.exit(0);
        }

        /*
        Main login UI after verifying database is available
        Display Welcome, Date, Time, basic menu option
        Sync Holiday database :V

        PS: NEED call twice cause of index 0, i just make it sound better
         */

        System.out.println("---Please wait syncing database to time---");
        HolidayUI.syncHoliday();
        System.out.println("---VERIFYING---");
        HolidayUI.syncHoliday();
        System.out.println("---COMPLETED---");
        System.out.print("\n\n\n");
        do {
            try{
                System.out.println("-----Welcome to MOBILMA-----");
                System.out.println("Date: "+ TimeDateController.getDate()+ " Time: "+ TimeDateController.getTime());                
                System.out.println("Choose the following option:");
                System.out.println("1. Admin login");
                System.out.println("2. Customer login");
                System.out.println("3. Customer account creation");
                System.out.println("----------------------------");
                System.out.println("4. View movies");
                System.out.println("5. Exit");
                option = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Wrong input");
                sc.next();
            }
            
            switch(option) {
                case 1:
                    System.out.println("-----Admin login-----");
                    if(false!=LoginFlow(1, 0)){
                        System.out.println("Create new admin account? Y/N");
                        char c = sc.next().charAt(0);
                        if(Character.toLowerCase(c) != 'y')
                            new AdminUI();
                        else{
                            LoginFlow(1, 1);
                            System.out.println("Account created, returning to previous menu...");
                        }
                    }
                break;
                
                case 2:
                    System.out.println("-----Customer login-----");
                    System.out.println("MOBILMA will be closed on the following dates: ");
                    HolidayUI.getHoliday();
                    System.out.println("------------------------------");
                    if(false!=LoginFlow(2,0))
                        UserUI.UserApp();
                break;

                case 3:
                    System.out.println("-----Customer account creation-----");
                    LoginFlow(2, 1);
                    System.out.println("Account created, returning to previous menu...");
                break;

                case 4:
                    System.out.println("-----Movie list-----");
                    new SortListUI();
                break;

                default:
                    System.out.println("Restarting...");
            }
        
        }while(option >= 0 && option < 5);
    }

    
    /** 
     * @param usertype
     * @param state
     * @return boolean
     */
    /*
    LoginFlow for authentication
    Ability to create new login account
     */

    public static boolean LoginFlow(int usertype, int state){
        String userinput="";
        String passwordinput="";
        String token="";

   
        System.out.println("Enter Username:");
        userinput= sc.next();
        System.out.println("Enter Password:");
        passwordinput= sc.next();

        if(false==isValidPassword(passwordinput)){
            System.out.println("-----Password must contain the folowing-----");
            System.out.println("At least 8 characters and at most 20 characters.");
            System.out.println("At least one digit.");
            System.out.println("At least one upper case alphabet.");
            System.out.println("At least one lower case alphabet.");
            System.out.println("At least one special character which includes @#$%^&+=.");
            System.out.println("It doesn't contain any white/blank space."); 
            System.out.println("Returning to previous menu..."); 
            return false;
        }
        else{
            try {
                token= toHexString(getSHA(passwordinput));
            } catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown for incorrect algorithm: " + e);
            }

            if(usertype==1){
                List alist = (ArrayList) FileAccessController.readSerializedObject(AdminDatabase);
                if(state==1){
                    Database data= new Database();

                    do{System.out.println("Enter Username again:");
                    }while(!userinput.equals(sc.next()));

                    do{System.out.println("Enter Password again:");
                    }while(!passwordinput.equals(sc.next()));

                    data.setUser(userinput);
                    data.setPassword(token);
                    alist.add(data);
                    FileAccessController.writeSerializedObject(AdminDatabase, alist);
                    return true;
                }
                else{
                    for(int i = 0; i < alist.size(); i++) {
                        Database data= (Database)alist.get(i);
                        if(userinput.equals(data.getUser()))
                            if(token.equals(data.getPassword()))
                                return true;
                    }
                    System.out.println("Invalid credentials");
                    return false;
                }
            }

            if(usertype==2){
                List clist = (ArrayList) FileAccessController.readSerializedObject(CustomerDatabase);
                if(state==1){
                    Database data= new Database();

                    do{System.out.println("Enter Username again:");
                    }while(!userinput.equals(sc.next()));

                    do{System.out.println("Enter Password again:");
                    }while(!passwordinput.equals(sc.next()));

                    data.setUser(userinput);
                    data.setPassword(token);
                    clist.add(data);
                    FileAccessController.writeSerializedObject(CustomerDatabase, clist);
                    return true;
                }
                else{
                    for(int i = 0; i < clist.size(); i++) {
                        Database data= (Database)clist.get(i);
                        if(userinput.equals(data.getUser()))
                            if(token.equals(data.getPassword()))
                                return true;
                    }
                    System.out.println("Invalid credentials");
                    return false;
                }
            }
            return false;
        }
        
    }

    
    /** 
     * @param password
     * @return boolean
     */
    /*
    Password security pattern validation
    SHA-256 encryption
     */

    public static boolean isValidPassword(String password){
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                        + "(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])"
                        + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern claAatcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
    }

    
    /** 
     * @param input
     * @return byte[]
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
        
    
    /** 
     * @param hash
     * @return String
     */
    public static String toHexString(byte[] hash){
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }

        return hexString.toString();

    }
}