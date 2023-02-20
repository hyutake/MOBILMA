package Service.Sorting;

import java.util.Collections;
import java.util.List;

import Model.User;
/** 
 * Provides the service to sort a list of users by UserType (Admins first, then Customers)
 * @author  Cai Kaihang
 * @version 1.0
 * @since   2022-11-02
 */
public class SortByUserType {
    /**
     * To sort the user database in 'alphabetical' order: ADMIN first, then NORMAL
     * @param users     Entire list of users in the database
     * @return List   Sorted list of users
     */
    public static List sort(List<User> users) {
        Collections.sort(users, (User one, User other) -> {
            return one.getUserType().compareTo(other.getUserType());
        });
        return users;
    }
}
