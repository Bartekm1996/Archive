/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountManager;

/**
 *
 * @author Colum
 */
public class AccountFactory {
    
    public Account getAccount(String name, double balance){//Decides which kind of account a given user has.
        if(balance < 500){
            return new BronzeUser(name, balance);
        }
        else if (balance < 1500){
            return new SilverUser(new BronzeUser(name, balance));
        }
        else if (balance < 3500){
            return new GoldUser(new BronzeUser(name, balance));
        }
        else if (balance < 7500){
            return new PlatinumUser(new BronzeUser(name, balance));
        }
        else 
            return new DiamondUser(new BronzeUser(name, balance));
    }
}
