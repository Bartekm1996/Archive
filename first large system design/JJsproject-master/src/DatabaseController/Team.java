/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;
public class Team {

    int id;
    double odds;
    String tName;
    Player[] players;

    public Team(int id, double odds, String tName, Player[] players)
    {
        this.id = id;
        this.odds = odds;
        this.tName = tName;
        this.players = players;
    }

    public double getTeamOdds()
    {
        return odds;
    }
    
    public Player[] getTeamPlayers()
    {
        return players;
    }
}