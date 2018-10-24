/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;
public class LeaderboardDB {
    private DataMapper dm = new DataMapper();
    private DataSuper ds;
    
    public LeaderboardDB(){
        ds = dm.getParserType();
    }
    
    public String[][] getLeaderBoard()//Gets the current leaderboard as a two-dimensional array of strings.
    {
        String[][] out = ds.getLeaderboard();
        return out;
    }
}
