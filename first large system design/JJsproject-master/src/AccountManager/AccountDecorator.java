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
public abstract class AccountDecorator implements Account {//Decorator base class
    protected Account decoratedAccount;
    public AccountDecorator(Account decoratedAccount)
    {
        this.decoratedAccount=decoratedAccount;
    }
    public double getMaxSupport()
    {
        return 1.2;
    }
}
