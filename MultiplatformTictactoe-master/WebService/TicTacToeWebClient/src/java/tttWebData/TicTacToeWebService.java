/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tttWebData;

import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;



@WebService(serviceName = "TicTacToeWebService")
public class TicTacToeWebService {
    private MySQLAccess db = new MySQLAccess();
    
    public TicTacToeWebService(){
        db.connectDataBase();
    }
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "register")
    public String register(@WebParam(name = "uname") String uname, @WebParam(name = "pass") String pass) {
        return db.register(uname, pass);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "loginVerify")
    public String loginVerify(@WebParam(name = "uname") String uname, @WebParam(name = "pass") String pass) {
        return db.loginVerify(uname, pass);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getOptions")
    public String getOptions(@WebParam(name = "u") String u) {
        return db.getOptions(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setOnline")
    @Oneway
    public void setOnline(@WebParam(name = "u") String u) {
        db.setOnline(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setOffline")
    @Oneway
    public void setOffline(@WebParam(name = "u") String u) {
        db.setOffline(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getJoiner")
    public String getJoiner(@WebParam(name = "u") String u) {
        return db.getJoiner(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "beginGame")
    public void beginGame(@WebParam(name = "u") String u, @WebParam(name = "joiner") String joiner) {
        db.beginGame(u, joiner);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "denyGame")
    public void denyGame(@WebParam(name = "u") String u) {
        db.denyGame(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkGameStart")
    public String checkGameStart(@WebParam(name = "u") String u) {
        return db.checkGameStart(u);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "invite")
    public void invite(@WebParam(name = "uname") String uname, @WebParam(name = "invite") String invite) {
        db.invite(uname, invite);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTurn")
    public String getTurn(@WebParam(name = "joiner") String joiner) {
        return db.getTurn(joiner);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setMove")
    public void setMove(@WebParam(name = "move") String move, @WebParam(name = "uname") String uname) {
        db.setMove(move, uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "resetGame")
    public void resetGame(@WebParam(name = "uname") String uname) {
        db.resetGame(uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getStats")
    public String getStats(@WebParam(name = "uname") String uname) {
        return db.getStats(uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setDraw")
    public void setDraw(@WebParam(name = "uname") String uname) {
        db.setDraw(uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setWin")
    public void setWin(@WebParam(name = "uname") String uname) {
        db.setWin(uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setLoss")
    public void setLoss(@WebParam(name = "uname") String uname) {
        db.setLoss(uname);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "connect")
    @Oneway
    public void connect() {
        db.connectDataBase();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLeaderboard")
    public String getLeaderboard() {
        return db.getLeaderboard();
    }
}
