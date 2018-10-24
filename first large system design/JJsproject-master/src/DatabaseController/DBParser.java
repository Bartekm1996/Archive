/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseController;

import AccountManager.Account;
import AccountManager.AccountFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jakec
 */
public class DBParser implements DataSuper{
    
    private String dbName = "stropse_no_elbmag";
    private PreparedStatement preparedStatement;
    private Connection c;
    public DBParser(){
        DBConnector.getInstance().connectDataBase();
    }
    
  
  public boolean check(String u, String p){//Checks the username and password against the database.
      try {
      ResultSet resultSet = DBConnector.getInstance().execute("select uname, password from "+dbName+".users where uname = '"+u+"'");
        if(resultSet.next()){
            String u2 = resultSet.getString("uname");
            String p2 = resultSet.getString("password");
            if(p.equals(p2)){
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }
    catch(Exception e){
        return false;
    }
  }
  
  public String[][] getAllTeams(){//Gets a list of all the teams in a two-dimensional array.
      try {
        ArrayList<String[]> arr = new ArrayList<String[]>();
        ResultSet resultSet = DBConnector.getInstance().execute("select odds, teamname from "+dbName+".teams");
        while(resultSet.next()){
            String[] sArr = new String[2];
            sArr[0] = resultSet.getString("teamname");
            sArr[1] = ""+resultSet.getDouble("odds");
            arr.add(sArr);
        }
        String[][] out = new String[arr.size()][2];
        for(int i = 0; i < arr.size(); i++){
           out[i][0] = arr.get(i)[0];
           out[i][1] = arr.get(i)[1];
          
        }
        return out;
    }
    catch(Exception e){
        String[][] out = new String[1][2];
        System.out.println(e);
        return out;
    }
  }
  
  public String[][] getLeaderboard(){//Gets a leaderboard and returns it as a two-dimensional array.
      try{
        ResultSet resultSet = DBConnector.getInstance().execute("select Bal, uname from "+dbName+".users ORDER BY (Bal) ASC;");
         ArrayList<String[]> arr = new ArrayList<String[]>();
        while(resultSet.next()){
            String[] sArr = new String[2];
            sArr[0] = ""+resultSet.getDouble("Bal");
            sArr[1] = resultSet.getString("uname");
            arr.add(sArr);
        }
        
        String[][] out = new String[arr.size()][2];
        for(int i = 0; i < arr.size(); i++){
           out[i][0] = arr.get(i)[0];
           out[i][1] = arr.get(i)[1];
        }
        return out;
      }
      catch(SQLException e){
        String[][] out = new String[1][2];
        out[0][0] = "Error";
        out[0][1] = "";
        System.out.println(e);
        return out;
      }
  }
  
  public void updateWins(String uname){//Updates the recorded wins for a certain user.
        try {
            ResultSet rs = DBConnector.getInstance().execute("Select wins from "+dbName+" where uname = '"+ uname+"';");
            if(rs.next()){
                int x = rs.getInt("wins");
                preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("UPDATE ?.users set wins = ? where uname = ?");
                preparedStatement.setString(1, dbName);
                preparedStatement.setInt(2, x);
                preparedStatement.setString(1, uname);
                DBConnector.getInstance().execute(preparedStatement);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  }
  
  public String[][] getPreviousBets(String uname){//Gets a users previous bets
  try{
        ResultSet resultSet = DBConnector.getInstance().execute("select BetID, amount, team, winner from "+dbName+".placed_bets where uname = '"+uname+"';");
         ArrayList<String[]> arr = new ArrayList<String[]>();
        String[] sArr = new String[5];
        while(resultSet.next()){
            int x = resultSet.getInt("BetID");
            ResultSet rs = DBConnector.getInstance().execute("select team1, team2 from "+dbName+".bets where BetID = '"+x+"';");
            sArr[0] = rs.getString("team1");
            sArr[1] = rs.getString("team2");
            sArr[2] = ""+resultSet.getInt("amount");
            sArr[3] = resultSet.getString("team");
            sArr[4] = resultSet.getString("winner");
            arr.add(sArr);
        }
        
        String[][] out = new String[arr.size()][5];
        for(int i = 0; i < arr.size(); i++){
           out[i][0] = arr.get(i)[0];
           out[i][1] = arr.get(i)[1];
           out[i][2] = arr.get(i)[2];
           out[i][3] = arr.get(i)[3];
           out[i][4] = arr.get(i)[4];
        }
        return out;
      }
      catch(SQLException e){
        String[][] out = new String[1][2];
        out[0][0] = "Error";
        out[0][1] = ""+e;
        System.out.println(e);
        return out;
      }
  }

    public void update(String name, double odds) {//Either updates existing details for a team or adds a new one.
        try {
            ResultSet rs = DBConnector.getInstance().execute("Select TeamName from "+dbName+".teams where TeamName = '"+name+"';");
            if(rs.next()){
                preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("UPDATE "+dbName+".teams set odds = ? where TeamName = ?");
                preparedStatement.setDouble(1, odds);
                preparedStatement.setString(2, name);
                DBConnector.getInstance().execute(preparedStatement);
            }
            else{
                preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("insert into "+dbName+".teams (teamname, odds) values (?, ?)");
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, odds);
                
                DBConnector.getInstance().execute(preparedStatement);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean addUser(String userName, String password, String email) {//Adds a user with the given details.
        
        try {
            preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("insert into "+dbName+".users (uname, password, email ,Bal) values ( ?, ?, ?, ?)");
            
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setDouble(4, 100);
            
            DBConnector.getInstance().execute(preparedStatement);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Account getAccount(String id) {//Gets the account with the given id.
        try {
            ResultSet rs = DBConnector.getInstance().execute("Select Bal, Uname from "+dbName+".users where Uname= '"+id+"';");
            if(rs.next()){
                Double xp = rs.getDouble("Bal");
                String username = rs.getString("Uname");
                AccountFactory af = new AccountFactory();
                return af.getAccount(username, xp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(String name, double odds, String tname) {//Either updates existing details for a player or adds a new one.
        try {
                ResultSet rs = DBConnector.getInstance().execute("Select name from "+dbName+".players where name = '"+name+"';");
                if(rs.next()){
                    preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("UPDATE "+dbName+".players set odds = ? where name = ?");
                    preparedStatement.setDouble(1, odds);
                    preparedStatement.setString(2, name);
                    DBConnector.getInstance().execute(preparedStatement);
                }
                else{
                    preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("insert into "+dbName+".players (name, teamname, odds) values (?, ?, ?)");
                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, tname);
                    preparedStatement.setDouble(3, odds);
            
                    DBConnector.getInstance().execute(preparedStatement);
                }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String[][] getAllPlayers() {//Returns a list of all players in a two-dimensional array.
    try {
        ArrayList<String[]> arr = new ArrayList<String[]>();
        ResultSet resultSet = DBConnector.getInstance().execute("select odds, name from "+dbName+".players");
        while(resultSet.next()){
            String[] sArr = new String[2];
            sArr[0] = resultSet.getString("name");
            sArr[1] = ""+resultSet.getDouble("odds");
            arr.add(sArr);
        }
        String[][] out = new String[arr.size()][2];
        for(int i = 0; i < arr.size(); i++){
           out[i][0] = arr.get(i)[0];
           out[i][1] = arr.get(i)[1];
        }
        return out;
        
        }
        catch(Exception e){
            String[][] out = new String[1][2];
            System.out.println(e);
            return out;
        }
    }

    public Player getPlayer(String name) {//Gets the player with a given name.
    try {
        System.out.println(name);
        ResultSet resultSet = DBConnector.getInstance().execute("select id, odds from "+dbName+".players where name = '"+name+"';");
        int id = 0;
        Double odds = null;
        if(resultSet.next()){
            id = resultSet.getInt("id");
            odds = resultSet.getDouble("odds");
        }
        return new Player(id, odds, name);
        }
        catch(Exception e){
            System.out.println(e.toString());
            return new Player(0, 0, "Error");
        }
    }

    public Team getTeam(String name) {//Gets the team with a given name.
        try {
        ArrayList<Player> arr = new ArrayList<Player>();
        ResultSet resultSet = DBConnector.getInstance().execute("select id, odds from "+dbName+".teams where Team_Name = '"+name+"';");
        int id = 0;
        Double odds = null;
        if(resultSet.next()){
            id  = resultSet.getInt("id");
            odds = resultSet.getDouble("odds");
        }
        resultSet = DBConnector.getInstance().execute("select id, name, odds from "+dbName+".players where team_name = '"+name+"';");
        String pname;
        Double podds;
        int pid;
        while(resultSet.next()){
            pid = resultSet.getInt("id");
            pname = resultSet.getString("name");
            podds = resultSet.getDouble("odds");
            arr.add(new Player(pid, podds, pname));
        }
        Player[] players = new Player[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            players[i] = arr.get(i);
        }
        return new Team(id, odds, name, players);
            
        }
        catch(Exception e){
            System.out.println("Error");
            return null;
        }
    }

    @Override
    public void updateAccount(String username, double balance) {//Updates the given account with a new balance
        try {
            ResultSet rs = DBConnector.getInstance().execute("Select uname from "+dbName+".users where uname = '"+username+"';");
            if(rs.next()){
                preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("UPDATE "+dbName+".users set bal = ? where uname = ?");
                preparedStatement.setDouble(1, balance);
                preparedStatement.setString(2, username);
                DBConnector.getInstance().execute(preparedStatement);
            }
            else{
              System.out.println("Error Account not found!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void placebet(Account user, double amount, double odds) {//Places a bet of a certain amount at certain odds for a given user.
                
        try {
            preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("insert into "+dbName+".house (uname,dates, amount ,odds) values ( ?, ?, ?, ?)");
            
            preparedStatement.setString(1, user.Uname());
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setDouble(4, odds);
            
            DBConnector.getInstance().execute(preparedStatement);
            preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("insert into "+dbName+".placedbets (uname,amount ,odds) values ( ?, ?, ?)");
            
            preparedStatement.setString(1, user.Uname());
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDouble(3, odds);
            DBConnector.getInstance().execute(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);     
        }
        finally{
            try {
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getStats(String id) {//Gets a given users stats.
        try {
            ResultSet rs = DBConnector.getInstance().execute("Select count(uname) AS count from "+dbName+".placedbets where uname = '"+id+"'&& amount>0;");
            if(rs.next()){
                int count = rs.getInt("count");
                System.out.print(count);
                return (""+count);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ("unexpected error please try again later");
    }

    @Override
    public void closebet(Account user, double amount, double odds) {//removes bet from house db when the result has been parsed.
        try {
            String uname = user.Uname();
            preparedStatement = DBConnector.getInstance().getConnect().prepareStatement("delete from"+dbName+".house (uname,dates, amount ,odds) where uname=? &&amount=?&& odds=?;");
            preparedStatement.setString(1, uname);
            preparedStatement.setDouble(2, amount);
            preparedStatement.setDouble(3, odds);
        } catch (SQLException ex) {
            Logger.getLogger(DBParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
