/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;
public class Player
{
    
    int id;
    double odds;
    String pName;

    public Player(int id,double odds, String pName)
    {
        this.id = id;
        this.odds = odds;
        this.pName = pName;
    }
    public double getPlayerOdds()
    {
        return odds;
    }
}