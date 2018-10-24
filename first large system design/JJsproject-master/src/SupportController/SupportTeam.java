/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupportController;

import AccountManager.Account;
import DatabaseController.AccountDB;
import DatabaseController.Team;
import DatabaseController.TeamDB;

/**
 *
 * @author Colum
 */
public class SupportTeam {

    private String id;
    private double amount;
    private Account User;
    private TeamDB database;
    Team team;
    SupportTeam(String id,String pid, double amount) {//Handles a bet on a team
        this.id=id;
        this.amount=amount;
        database= new TeamDB();
        team=database.getTeam(pid); 
        double odds=team.getTeamOdds();
        AccountDB holder=new AccountDB();
        User=holder.getAccount(id);
        User.deductXP(amount);
        House temp= new House();
        temp.TransferXpHouse(User,amount,odds,pid);
    }
}
