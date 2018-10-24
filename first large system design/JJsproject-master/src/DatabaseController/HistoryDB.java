/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;

import AccountManager.Account;
public class HistoryDB {private DataMapper dm = new DataMapper();
    private DataSuper ds;
    
    public HistoryDB(){
        ds = dm.getParserType();
    }
    public String[][] getHistory(String uname){//Given a username, gets the users previous bets.
        return ds.getPreviousBets(uname);
    }

    public void placebet(Account user, double amount, double odds) {//Given the details, tells the dtabase to place a bet for a certain user.
      ds.placebet(user,amount,odds);
    }

    public void closebet(Account user, double amount, double odds) {
        ds.closebet(user,amount,odds);
    }
    
}
