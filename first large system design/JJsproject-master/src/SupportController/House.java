/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SupportController;

import AccountManager.Account;
import DatabaseController.HistoryDB;
import DatabaseController.ResultDB;

/**
 *
 * @author Colum
 */
public class House extends Observer{

   private double amount,odds;
   private String pid;
   private Account user;
   HistoryDB archive;
 void TransferXpHouse(Account user,double amount, double odds,String pid) {
        this.amount=amount;
        this.odds=odds;
        this.user=user;
        this.pid=pid;
        this.house = new ResultDB();
        this.house.attach(this);
        archive=new HistoryDB();
        archive.placebet(user,amount,odds);
        }
    public void pay(String winner){//Pays out a certain amount to a user
        if(winner==pid)
        user.add(amount*odds);
        archive.closebet(user,amount,odds);
    }
}
