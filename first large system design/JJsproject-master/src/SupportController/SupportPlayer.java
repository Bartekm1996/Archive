/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupportController;
import AccountManager.*;
import DatabaseController.AccountDB;
import DatabaseController.Player;
import DatabaseController.PlayerDB;
/**
 *
 * @author Colum
 */
public class SupportPlayer {
    private String id;
    private double amount;
    private Account user;
    private PlayerDB database;
    Player player;
    SupportPlayer(String name,String pname ,double amount) {//Handles a bet on a player
        this.id=name;
        this.amount=amount;
        AccountDB holder=new AccountDB();
        user=holder.getAccount(id);
        database= new PlayerDB();
        player=database.getPlayer(pname);
        double odds=player.getPlayerOdds();
        user.deductXP(amount);
        House temp= new House();
        temp.TransferXpHouse(user,amount,odds,pname);
    }
}
