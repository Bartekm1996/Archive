/*
 * The go-between for the UI and the account database class when the user is registering a new account
 */
package LoginManager;

import DatabaseController.UserDB;

/**
 *
 * @author Jack
 */
public class Register {
    
    private final UserDB account;
    
    public Register()//Default constructor
    {
        account = new UserDB();
    }
    
    public boolean registerUser(String nUser, String nPass, String nMail)//Takes a username, password, and email and sends them along to the database
            //to add a new user. Returns true or false based on whether anything went wrong or not.
    {
        boolean out = account.add(nUser, nPass, nMail);
        return out;
    }
    
}
