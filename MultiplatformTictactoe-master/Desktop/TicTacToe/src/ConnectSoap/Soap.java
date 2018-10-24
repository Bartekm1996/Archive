
package ConnectSoap;

import javax.xml.ws.WebServiceRef;
import SoapConnector.*;
import java.util.List;
//test it
        
        
public class Soap {
    @WebServiceRef(wsdlLocation="http://localhost:8080/"
            + "TicTacToeWebClient/TicTacToeWebService?wsdl")
    static TicTacToeWebService_Service web;
    
    private TicTacToeWebService port;
    public Soap(){
        web = new TicTacToeWebService_Service();
        port = web.getTicTacToeWebServicePort();
   }
    
    public String register(String uname, String pass){
        return port.register(uname, pass);
    }
    
    public String loginVerify(String uname, String pass){
        return port.loginVerify(uname, pass);
    }
    
    public Object[] getOptions(String u){
        return port.getOptions(u).split(",");
    }
    
    public void setOnline(String u){
        port.setOnline(u);
    }
    
    public void setOffline(String u){
        port.setOffline(u);
    }
    
    public String getJoiner(String u){
        return port.getJoiner(u);
    }
    
    public void beginGame(String uname, String joiner){
        port.beginGame(uname, joiner);
    }
    
    public void denyGame(String uname){
        port.denyGame(uname);
    }
    
    public String checkGameStart(String u){
        return port.checkGameStart(u);
    }
    
    public void invite(String uname, String invite){
        port.invite(uname, invite);
    }
    
    public String getTurn(String joiner){
        return port.getTurn(joiner);
    }
    
     public void setMove(String move, String uname)  {
        port.setMove(move, uname);
    }

    public void setDraw(String uname){
         port.setDraw(uname);
    }
    
    public void setWin(String uname){
         port.setWin(uname);
    }
    
    public void setLoss(String uname){
        port.setLoss(uname);
    }

    public void resetGame(String uname) {
        port.resetGame(uname);
    }

    public String getStats(String uname) {
        return port.getStats(uname);
    }
    
    public String[] getLeaderboard(){
        return port.getLeaderboard().split(",");
    }
}
