/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import ConnectSoap.Soap;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jakec
 */
public class GameThread extends Thread{
    private boolean threadRun = false;
    private String type;
    private String joiner;
    private Soap db;
    private String uname;
    private TicTacToeGame m;

    public GameThread(String uname, String joiner, String type, Soap db){
        this.db = db;
        this.uname = uname;
        this.joiner = joiner;
        this.type = type;
        m = new TicTacToeGame(uname, joiner, type, db);
    }

    public void run(){
        while(m.threadRun()){
            String lastTurn = null;
            String temp = null;
            try {
                temp = db.getTurn(joiner);
                if(!temp.equals(lastTurn)){
                lastTurn = temp;
                String[] move = lastTurn.split(",");
                if(move[0].equals("MOVE")){
                    if(move[1].matches("[0-9]")){
                        m.disableReset();
                        m.play(Integer.parseInt(move[1]));
                        db.resetGame(joiner);
                    }
                    
                }
                else if(move[0].equals("QUIT")){
                    m.quit();
                }
                }
                sleep(1000);
            } catch (Exception ex) {
                Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
