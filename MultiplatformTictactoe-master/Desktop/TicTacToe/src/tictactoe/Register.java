/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import javax.xml.soap.*;
import java.awt.BorderLayout;
import java.awt.Color;
import ConnectSoap.Soap;
import java.awt.Dimension;
import java.awt.GridLayout;
import static java.awt.SystemColor.menuText;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author jakec
 */
public class Register extends JFrame implements ActionListener {
    private JPanel menu;
    private JLabel menuText;
    private JButton submit;
    private JPasswordField pField[] = {new JPasswordField(1), new JPasswordField(1)};
    private Soap db = new Soap();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    
    private javax.swing.JTextField unameTextField;
    
        public Register(){
           setDefaultCloseOperation(Welcome.EXIT_ON_CLOSE);
        menu = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        unameTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        menuText = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        pField[0] = new javax.swing.JPasswordField();
        pField[1] = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        submit.setText("Submit");
        submit.addActionListener(this);


        jLabel1.setText("Enter Username: ");

        menuText.setText("Enter Register Details");

        jLabel2.setText("Password:");

        jLabel3.setText("Confirm Password:");

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(55, 55, 55)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(unameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(pField[0])
                            .addComponent(pField[1])))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuText)
                            .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(menuText)
                .addGap(42, 42, 42)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pField[0], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pField[1], javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(submit)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setVisible(true);
        }

    @Override
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();    
        
        if(source.equals(submit)){
            try {
                verifyDetails();
            } catch (Exception ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void verifyDetails() throws Exception{
        String uname, p1, p2;
        uname = unameTextField.getText();
        p1 = pField[0].getText();
        p2 = pField[1].getText();
        if(p1.equals(p2))
            if(p1.length() > 0)
                if(uname.length() > 0)
                    if (uname.length() <= 10){
                        String ver = "";
                        ver = db.register(uname, p1);
                        menuText.setText(ver);
                        if (ver.length() == 0){
                            mainMenu(uname);
                        }
                    }
                    else
                        menuText.setText("username too long");
                else
                    menuText.setText("no username entered");
            else
                menuText.setText("No password entered");
        else
            menuText.setText("Passwords don't match");
        
    }
    
    public void mainMenu(String uname) throws Exception {
        dispose();
        MenuThread m = new MenuThread(uname, db);
        m.start();
    }
}
