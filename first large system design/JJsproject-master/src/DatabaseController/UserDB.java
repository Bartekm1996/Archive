/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;

public class UserDB {
    private DataMapper dm = new DataMapper();
    private DataSuper ds;
    
    public UserDB(){
        ds = dm.getParserType();
    }
    
    public boolean add(String userName, String password, String email)//Tells the database to add a new user with the given details,
            //Returns true or false based on whether something goes wrong or not.
    {
        boolean result = ds.addUser(userName, password, email);
        return result;
    }
}
