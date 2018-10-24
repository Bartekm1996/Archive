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
public interface Account {

    double getMaxSupport();
   double balance();
   String Uname();
    public void deductXP(double amount);
    public void add(double amount);
}
