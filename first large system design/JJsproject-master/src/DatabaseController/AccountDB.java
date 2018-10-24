
package DatabaseController;

import AccountManager.Account;
public class AccountDB {
        
    private DataMapper dm = new DataMapper();
    private DataSuper ds;
    
    public AccountDB(){
        ds = dm.getParserType();
    }
    public boolean check(String username, String password)//Takes a username and password to be checked for validity by the database.
            //If they're valid, returns true, otherwise returns false.
    {
        boolean out = ds.check(username, password);
        return out;
    }
    public void update(String username,double balance){//Takes a username and a balance to update the account to and sends them to the database to be processed.
        ds.updateAccount(username, balance);
    }
    
    public Account getAccount(String id){//Gets the account with a specific id from the database and passes it back up.
        return ds.getAccount(id);
    }
     public String getStats(String id){//Gets the stats of a certain account and passes them up as a string.
        String output= ds.getStats(id);
        Account temp= getAccount(id);
        return (temp.Uname()+"Supported "+output+"times and now has a balance of"+temp.balance()+"XP");
    }
}
