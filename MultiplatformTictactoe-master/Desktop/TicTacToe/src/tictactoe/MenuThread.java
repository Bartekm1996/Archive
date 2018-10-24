/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import ConnectSoap.Soap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakec
 */
public class MenuThread extends Thread{
    private boolean threadRun = false;
    private String joiner = "";
    private String gameStart = "";
    private Soap db;
    private String uname;
    private MainMenu m;

    public MenuThread(String uname, Soap db){
        this.db = db;
        this.uname = uname;
        threadRun = true;
        m = new MainMenu(uname, db);
    }

    public void run(){
        while(m.active() && threadRun){
            try {
                gameStart = db.checkGameStart(uname);
                if(!(gameStart.length() < 1)){
                    m.nextScreen(gameStart);
                    threadRun = false;
                } else {
                }
                joiner = db.getJoiner(uname);
                if(!(joiner.length() < 1) || m.getBoolDisplay()){
                    m.setBoolDisplay();
                    m.displayJoinerOption(joiner);
                }
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(MenuThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(MenuThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
