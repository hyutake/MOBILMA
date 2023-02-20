package _Deprecated;

import java.io.Serializable;
/**
 @deprecated No Longer in development
 */
/*
 The format of how the data will be store as serializable

 This is for database, used by AdminDatabase & CustomerDatabase
 */
public class Database implements Serializable {  
    private String user;
    private String password;

    public Database(){
    }

    
    /** 
     * @return String
     */
    public String getUser() {
        return user;
    }

    
    /** 
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    
    /** 
     * @return String
     */
    public String getPassword() {
        return password;
    }

    
    /** 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    

}
