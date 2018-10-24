/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;
public class PlayerDB {

    private DataMapper dm = new DataMapper();
    private DataSuper ds;
    
    public PlayerDB(){
        ds = dm.getParserType();
    }
    public Player getPlayer(String id)//Gets a player from the database given their id.
    {
        Player out = ds.getPlayer(id);
        return out;
    }
    
    public String[][] showAllPlayers()//Gets a two-dimensional array containing all players.
    {
        String[][] out = ds.getAllPlayers();
        return out;
    }
    
    public void update(String name, double odds, String tname)//Tells the database to add a new player.
    {
        ds.update(name, odds, tname);
    }

   
}
