package tttWebData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAccess {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;
  private boolean connectionFlag = false;

  public void connectDataBase() {
     try{
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect = DriverManager
                .getConnection("jdbc:mysql://localhost/tictactoe1?"
                        + "user=root&password=");

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        connectionFlag = true;
     }
     catch(Exception e){
         connectionFlag = false;
         
     }
      
  }
  public void readDataBase()  {
    if(!connectionFlag)
        connectDataBase();
    try {
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select * from tictactoe1.players");
      writeResultSet(resultSet);
    }
    catch(Exception e){
        
    }
    finally{
        close();
    }
  }
  public void clearDataBase() {
    if(!connectionFlag)
        connectDataBase();
      try{
      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  tictactoe1.players values ( ?, ?, ?, ?, ?, ?, ?)");
      // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
      // Parameters start with 1
      preparedStatement.setString(1, "jake");
      preparedStatement.setString(2, "jake");
      preparedStatement.setInt(3, 0);
      preparedStatement.setInt(4, 0);
      preparedStatement.setInt(5, 0);
      preparedStatement.setString(6, "ONLINE");
      preparedStatement.setString(7, "WAITING");
      
      preparedStatement.executeUpdate();

      preparedStatement = connect
          .prepareStatement("SELECT uname, password, wins, losses, draws, gamestate, lastMove from tictactoe1.players");
      resultSet = preparedStatement.executeQuery();
      writeResultSet(resultSet);

      /*// Remove again the insert comment
      preparedStatement = connect
      .prepareStatement("delete from FEEDBACK.COMMENTS where myuser= ? ; ");
      preparedStatement.setString(1, "Test");
      preparedStatement.executeUpdate();
      
      resultSet = statement
      .executeQuery("select * from FEEDBACK.COMMENTS");
      writeMetaData(resultSet);
      */
    } catch (Exception e) {
      
    } finally {
      close();
    }

  }
private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
      String user = resultSet.getString("uname");
      String website = resultSet.getString("password");
      String summery = resultSet.getString("wins");
      int date = resultSet.getInt("losses");
      String comment = resultSet.getString("gamestate");
      System.out.println("User: " + user);
      System.out.println("Website: " + website);
      System.out.println("Summery: " + summery);
      System.out.println("Date: " + date);
      System.out.println("Comment: " + comment);
    }
  }

  // You need to close the resultSet
  private void close() {
    try { 
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
      connectionFlag = false;
    } catch (Exception e) {

    }
  }
  
  public String register(String uname, String pass) {
      if(!connectionFlag)
        connectDataBase();
      try{
         preparedStatement = connect
          .prepareStatement("insert into  tictactoe1.players values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, uname);
        preparedStatement.setString(2, pass);
        preparedStatement.setInt(3, 0);
        preparedStatement.setInt(4, 0);
        preparedStatement.setInt(5, 0);
        preparedStatement.setString(6, "OFFLINE");
        preparedStatement.setString(7, null);
        preparedStatement.setString(8, null);
        preparedStatement.setString(9, null);
      
      preparedStatement.executeUpdate();
      }
      catch(Exception e) {
           return "Username already in use";
      }
      return "";
  }
  
  public String loginVerify( String uname, String pass) {
    if(!connectionFlag)
        connectDataBase();
    try {
       // return uname;
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select uname, password from tictactoe1.players where uname = '"+uname+"'");
        String u, p;
        if(resultSet.next()){
            u = resultSet.getString("uname");
            p = resultSet.getString("password");
            if(p.equals(pass)){
                return "";
            }
            else
                return "Password Incorrect";
        }
        else{
            return "Username incorrect"+uname+pass;
        }
    }
    catch(Exception e){
        
    }
      return null;
  }
  
  public String getOptions(String u) {
      if(!connectionFlag)
        connectDataBase();
    try {
      // Result set get the result of the SQL query
      resultSet = statement
          .executeQuery("select uname from tictactoe1.players where gamestate = 'ONLINE'");
        String output = "";
        String next = "";
        output = "None";
        while(resultSet.next()){
            next = resultSet.getString("uname");
            if(!next.equals(u))
                output +="," +next;
        }
        
        return output;
    }
    catch(Exception e){
        
    }
      return null;
  }
  
  public void setOnline(String u) {
        if(!connectionFlag)
        connectDataBase();
    try {
        preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set gamestate = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, "ONLINE");
        preparedStatement.setString(2, u);
      preparedStatement.executeUpdate();
      preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set startGame = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, u);
      preparedStatement.executeUpdate();
      preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set lastMove = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, u);
      preparedStatement.executeUpdate();
      preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set joiner = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, u);
      preparedStatement.executeUpdate();
    }
    catch(Exception e){
        
    }
  }
  
  public void setOffline(String u) {
        if(!connectionFlag)
        connectDataBase();
    try {
        
        preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set gamestate = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, "OFFLINE");
        preparedStatement.setString(2, u);
      preparedStatement.executeUpdate();
    }
    catch(Exception e){
        
    }
  }

    public String getJoiner(String u)  {
        if(!connectionFlag)
            connectDataBase();
        try {
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select joiner from tictactoe1.players where uname = '"+u+"'");
        if(resultSet.next()){
          String out = resultSet.getString("joiner");
        if(out == null)
            return "";
        else
            return out;
        }
        return "";
        }
        catch(Exception e){
            
        }    
      return null;
    }

    public void beginGame(String uname, String joiner)  {
    if(!connectionFlag)
        connectDataBase();
    try {
        
        preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set gamestate = ?, joiner = ?, lastMove = ? where uname = ? ;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, "IN GAME");
        preparedStatement.setString(2, null);
        preparedStatement.setString(3, uname);
        preparedStatement.setString(4, null);
        preparedStatement.executeUpdate();
        preparedStatement = connect
          .prepareStatement("UPDATE tictactoe1.players set startGame = ? where uname = ?;");
        // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
        // Parameters start with 1
        preparedStatement.setString(1, uname);
        preparedStatement.setString(2, joiner);
        preparedStatement.executeUpdate();
    }
    catch(Exception e){
        
    }
    }

    public void denyGame(String uname)  {
        if(!connectionFlag)
            connectDataBase();
        try {

            preparedStatement = connect
              .prepareStatement("UPDATE tictactoe1.players set joiner = ? where uname = ? ;");
            // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
            // Parameters start with 1
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, uname);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            
        }
    }

    public String checkGameStart(String u)  {
        
        if(!connectionFlag)
            connectDataBase();
        try {
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select startGame from tictactoe1.players where uname = '"+u+"'");
        if(resultSet.next()){
            String out = resultSet.getString("startGame");
            if(out == null)
                return "";
            else
                return out;
        }
        return "";
        }
        catch(Exception e){
            
        } 
      return null;
    }

    public void invite(String uname, String invite)  {
        if(!connectionFlag)
            connectDataBase();
        try {

            preparedStatement = connect
              .prepareStatement("UPDATE tictactoe1.players set joiner = ? where uname = ? ;");
            // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
            // Parameters start with 1
            preparedStatement.setString(1, uname);
            preparedStatement.setString(2, invite);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            
        }
    }

    public String getTurn(String joiner)  {
        if(!connectionFlag)
            connectDataBase();
        try {
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select lastMove from tictactoe1.players where uname = '"+joiner+"'");
        if(resultSet.next()){
            String out = resultSet.getString("lastMove");
            if(out == null)
                return "";
            else
                return out;
        }
        return "";
        }
        catch(Exception e){
            
        } 
      return null;
    }

    public void setMove(String move, String uname)  {
        if(!connectionFlag)
            connectDataBase();
        try {

            preparedStatement = connect
              .prepareStatement("UPDATE tictactoe1.players set lastMove = ? where uname = ? ;");
            // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
            // Parameters start with 1
            preparedStatement.setString(1, move);
            preparedStatement.setString(2, uname);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    public void resetGame(String uname)  {
        if(!connectionFlag)
            connectDataBase();
        try {

            preparedStatement = connect
              .prepareStatement("UPDATE tictactoe1.players set lastMove = ? where uname = ? ;");
            // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
            // Parameters start with 1
            preparedStatement.setString(1, null);
            preparedStatement.setString(2, uname);
            preparedStatement.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    public String getStats(String uname)  {
        if(!connectionFlag)
            connectDataBase();
        try {
            
         String out = ""+uname+" has won ";
        // Result set get the result of the SQL query
        resultSet = statement
            .executeQuery("select wins, losses, draws from tictactoe1.players where uname = '"+uname+"'");
        if(resultSet.next()){
            out += resultSet.getInt("wins")+ " times, lost ";
            out += resultSet.getInt("losses")+ " times and drawn ";
            out += resultSet.getInt("draws")+ " times";
            return out;
        }
        return "";
        }
        catch(Exception e){
            return "";
        } 
    }

    public void setDraw(String uname){
        try {
            if(!connectionFlag)
                connectDataBase();
            try {
                
                // Result set get the result of the SQL query
                resultSet = statement
                        .executeQuery("select draws from tictactoe1.players where uname = '"+uname+"'");
                if(resultSet.next()){
                    int i= resultSet.getInt("draws")+ 1;
                    preparedStatement = connect
                            .prepareStatement("UPDATE tictactoe1.players set draws = ? where uname = ? ;");
                    // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
                    // Parameters start with 1
                    preparedStatement.setInt(1, i);
                    preparedStatement.setString(2, uname);
                    preparedStatement.executeUpdate();
                }
            }
            catch(Exception e){
            }
        }
        catch(Exception ex){
          Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setWin(String uname){
        try {
            if(!connectionFlag)
                connectDataBase();
            try {
                
                // Result set get the result of the SQL query
                resultSet = statement
                        .executeQuery("select wins from tictactoe1.players where uname = '"+uname+"'");
                if(resultSet.next()){
                    int i= resultSet.getInt("wins")+ 1;
                    preparedStatement = connect
                            .prepareStatement("UPDATE tictactoe1.players set wins = ? where uname = ? ;");
                    // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
                    // Parameters start with 1
                    preparedStatement.setInt(1, i);
                    preparedStatement.setString(2, uname);
                    preparedStatement.executeUpdate();
                }
            }
            catch(Exception e){
            }
        }
        catch(Exception ex){
          Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void setLoss(String uname) {
        try {
            if(!connectionFlag)
                connectDataBase();
            try {
                
                // Result set get the result of the SQL query
                resultSet = statement
                        .executeQuery("select losses from tictactoe1.players where uname = '"+uname+"'");
                if(resultSet.next()){
                    int i= resultSet.getInt("losses")+ 1;
                    preparedStatement = connect
                            .prepareStatement("UPDATE tictactoe1.players set losses = ? where uname = ? ;");
                    // "myuser, webpage, datum, summery, COMMENTS from FEEDBACK.COMMENTS");
                    // Parameters start with 1
                    preparedStatement.setInt(1, i);
                    preparedStatement.setString(2, uname);
                    preparedStatement.executeUpdate();
                }
            }
            catch(Exception e){
            }
        }
        catch(Exception ex){
          Logger.getLogger(MySQLAccess.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public String getLeaderboard(){
         if(!connectionFlag)
        connectDataBase();
        try {
          // Result set get the result of the SQL query
          resultSet = statement
              .executeQuery("select uname, wins, losses from tictactoe1.players ORDER BY wins DESC");
            String output = "";
            String next = "";
            output = "Names  |  Wins  |  Losses";
            while(resultSet.next()){
                next = resultSet.getString("uname") + "  |  "+ resultSet.getInt("wins" )+"  |  "+ resultSet.getInt("losses");
                output +="," +next;
            }

            return output;
        }
        catch(Exception e){
            return "error";
        }
    }
    
} 
