package Service.Admin;

import java.util.ArrayList;
import java.util.List;

import Boundary.AccountCreationUI;
import Constants.UserType;
import Controller.FileAccessController;
import Controller.FileAccess.UserFileAccess;
import Model.User;
/** 
 * Initialises the user data by prompting the creation of the 1st account (Admin) and then saving it to a .dat file
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-01
 */
public class GenerateUserData {
    /**
     * Generates an empty file and then prompts to create an Admin account
     * Only Admin accounts can create other admin accounts, hence the need to create it during the initialisation of data
     */
    public static void generateUsers() {
        // Generates an empty list 
        List<User> list = new ArrayList<>();
        FileAccessController.writeSerializedObject(UserFileAccess.fileName, list);
        // Generates an Admin account
        AccountCreationUI createAccUI = new AccountCreationUI(UserType.ADMIN);
    }
}
