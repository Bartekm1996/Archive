/*
 * The go-between for the UI and the account database class when the user is logging in
 */
package LoginManager;

import DatabaseController.AccountDB;

/**
 *
 * @author Jack
 */
public class Login {
    private AccountDB accountChecker;
    
    public Login()//Default constructor
    {
        accountChecker = new AccountDB();
    }
    
    public boolean verifyLogin(String nName, String nPass)//Takes a username and password and sends them along to the database to check if they're valid.
            //Returns true or false based on that.
    {
        return accountChecker.check(nName, nPass);
    }
}
