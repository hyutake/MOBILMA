package Controller.FileAccess;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import Controller.FileAccessController;
import Model.User;
import Service.Admin.GenerateUserData;
import Service.Sorting.SortByUserType;
/** 
 * Manages all the logic involving the reading of the file storing the user database
 * @author      Cai Kaihang
 * @author      Don Lim
 * @version     1.0
 * @since       2022-10-28
 */
public class UserFileAccess implements FileAccessController {
    public static final String fileName = "UserDatabase.dat";

    private List<User> users;

    /**
     * Creates a MovieFileAccess module that verifies that the database file is valid
     * If the file does not exist, will prompt to generate a pre-set file
     */
    public UserFileAccess() {
        if(FileAccessController.verifyFile(fileName)) {
            this.users = (ArrayList) FileAccessController.readSerializedObject(fileName);
        }
        else {
            Scanner sc = new Scanner(System.in);
            System.out.println("Missing " + fileName + " in " + (userDir + dirPath) + "\nRun database generator? Y/N");
            char c = sc.next().charAt(0);
            if(Character.toLowerCase(c) == 'y') {
                GenerateUserData.generateUsers();
                this.users = (ArrayList) FileAccessController.readSerializedObject(fileName);
            }
            else {
                System.out.println("NOTE: users initialised as null");
            }
        }
    }

    
    /** 
     * @return List
     */
    public List getData() {
        return users;
    }

    
    /** 
     * @param o
     */
    public void writeData(Object o) {
        if(o instanceof List) {
            List<User> list = SortByUserType.sort((List) o);    // sort the list first
            FileAccessController.writeSerializedObject(fileName, list);
        }
        else {
            System.out.println("Error in writing user data!");
        }
    }

    
    /** 
     * Does a 'less strict' matching
     * To identify if an account exists given only the username and password
     * @param user
     * @return User
     */
    public User findUserLite(User user) {
        User u;
        for(int i = 0; i < users.size(); i++) {
            u = users.get(i);
            // Match by username and password
            if(u.getUsername().equals(user.getUsername()) &&
                u.getPassword().equals(user.getPassword())) {
                return u;
            }
        }
        return new User();
    }

    
    /** 
     * Matches an account more strictly using username, password and usertype
     * @param user      User information to be matched with in the database
     * @return User     A copy of the matching account from the database, or an INVALID user account
     */
    public User findUser(User user) {
        User u;
        for(int i = 0; i < users.size(); i++) {
            u = users.get(i);
            // Match by UserType, username and password
            if(u.matchUser(user)) {
                return u;
            }
        }
        return new User();
    }

    
    /** 
     * To update old user data with a new one, mainly for bookings
     * @param newUser       New user data
     */
    public void updateUser(User newUser) {
        for(int i = 0; i < users.size(); i++) {
            User oldUser = users.get(i);
            if(oldUser.matchUser(newUser)) {
                users.set(i, newUser);
            }
        }
        writeData(users);
    }

    
    /** 
     * To add a new user to the database
     * @param newUser       New user to be added
     */
    public void addUser(User newUser) {
        users.add(newUser);
        writeData(users);
    }
}
