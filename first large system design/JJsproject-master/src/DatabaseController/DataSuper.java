/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;

import AccountManager.Account;


public interface DataSuper {

    Account getAccount(String id);

    boolean check(String username, String password);

    String[][] getPreviousBets(String uname);

    String[][] getLeaderboard();

    Player getPlayer(String name);
    
    String[][] getAllPlayers();

    String[][] getAllTeams();
    
    void update(String name, double odds);

    boolean addUser(String userName, String password, String email);

    Team getTeam(String name);

    void update(String name, double odds, String tname);

    public void updateAccount(String username, double balance);

    public void placebet(Account user, double amount, double odds);

    public String getStats(String id);

    public void closebet(Account user, double amount, double odds);
    
}
