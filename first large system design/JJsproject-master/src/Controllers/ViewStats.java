/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import AccountManager.Account;
import DatabaseController.AccountDB;

/**
 *
 * @author Colum
 */

public class ViewStats {//Gets the stats for a given user.
    String Stats;
    public ViewStats(String username) {
        AccountDB temp = new AccountDB();
     Stats = temp.getStats(username);
    }
    public  String getstats(){
        return Stats;
    }
}
