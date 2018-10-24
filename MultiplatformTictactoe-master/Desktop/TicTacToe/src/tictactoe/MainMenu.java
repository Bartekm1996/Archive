/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import java.awt.Dimension;
import ConnectSoap.Soap;
import java.awt.GridLayout;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class MainMenu extends JFrame implements ActionListener {
    
                     
    private javax.swing.JButton quit;
    private javax.swing.JButton invite;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel menuText;
    private javax.swing.JButton leaderboard;
    private String uname;
    private Soap db;
    private String type = "O";
    private boolean threadRun = true;
    private boolean inviteBool = false;
    // End of variables declaration    
    
    public MainMenu(String uname, Soap db){
        this.uname = uname;
        this.db = db;

        this. setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                        quit();
            }
        });
        db.setOnline(uname);
        db.resetGame(uname);
        menu = new javax.swing.JPanel();
        invite = new javax.swing.JButton();
        menuText = new javax.swing.JLabel();
        leaderboard = new javax.swing.JButton();
        quit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        invite.setText("Invite a player");
        invite.addActionListener(this);

        menuText.setText(db.getStats(uname));

        leaderboard.setText("Leaderboard");
        leaderboard.addActionListener(this);

        quit.setText("Quit");
        quit.setToolTipText("");
        quit.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menuText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(leaderboard, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(invite, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 135, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(menuText)
                .addGap(18, 18, 18)
                .addComponent(invite)
                .addGap(18, 18, 18)
                .addComponent(leaderboard)
                .addGap(18, 18, 18)
                .addComponent(quit)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        pack();
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();    
       
        if(source.equals(quit)){
            dispose();
            threadRun = false;
            try {
                db.setOffline(uname);
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        if(source.equals(invite)){
            try {
                Object options[] = db.getOptions(uname);
                if(options.length > 1){
                String input = (String) JOptionPane.showInputDialog(null, "Players online",
        "Choose a player to play against", JOptionPane.QUESTION_MESSAGE, null, // Use
                                                                        // default
                                                                        // icon
        options, // Array of choices
        options[0]);
                    
                if(input != null && !input.equals("None"))
                    db.invite(uname, input);
                }
                else
                    JOptionPane.showMessageDialog(null, "Sorry nobody else is online");
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(source.equals(leaderboard)){
            threadRun = false;
            dispose();
            Leaderboard l = new Leaderboard(uname, db);
        }
    }
    
    public void displayJoinerOption(String u) throws Exception{
        int selection = JOptionPane.showConfirmDialog(null, "Play against "+u+"?", ""+u+"?", JOptionPane.YES_NO_OPTION);
        if(selection == 0){
            db.beginGame(uname, u);
            type = "X";
            nextScreen(u);
            threadRun = false;
        }
        else {
            inviteBool = false;
            db.denyGame(uname);
        }
    }

    public void nextScreen(String joiner) throws Exception {
        dispose();
        db.beginGame(uname, joiner);
        GameThread t = new GameThread(uname, joiner, type, db);
        t.start();
    }
    
    public boolean active(){
        return threadRun;
    }
    
    public String getStats(){
        return db.getStats(uname);
    }

    boolean getBoolDisplay() {
        return inviteBool;
    }
    
    void setBoolDisplay() {
        inviteBool = true;
    }


    private void quit() {
     dispose();
            threadRun = false;
            try {
                db.setOffline(uname);
            } catch (Exception ex) {
                Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
    }
}
