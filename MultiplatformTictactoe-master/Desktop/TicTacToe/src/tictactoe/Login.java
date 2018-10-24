 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;
//here ya bitch

import java.awt.Color;
import ConnectSoap.Soap;
import java.awt.Dimension;
import java.awt.GridLayout;
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
public class Login extends JFrame implements ActionListener {
    private JPanel menu;
    private JLabel menuText;
    private JButton submit;
    private JTextField uField;
    
    private JPasswordField pField;
    private Soap db = new Soap();              
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
  
    public Login(){
        setDefaultCloseOperation(Welcome.EXIT_ON_CLOSE);
         menu = new javax.swing.JPanel();
        submit = new javax.swing.JButton();
        uField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        pField = new javax.swing.JPasswordField();
        menuText = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();


        submit.setText("Submit");
        submit.addActionListener(this);

        jLabel1.setText("Enter Username: ");

        menuText.setText("Enter Register Details");

        jLabel2.setText("Password:");

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
                            .addComponent(jLabel2))
                        .addGap(61, 61, 61)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pField)
                            .addComponent(uField, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menuText)
                            .addComponent(submit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(menuText)
                .addGap(42, 42, 42)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(uField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
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
            verifyLogin();
        }
    }
    
    public void verifyLogin(){
        String uname = uField.getText();
        String pass = pField.getText();
        String output = db.loginVerify(uname, pass);
        if(output.length() == 0)
            continueMain(uname);
        else
            menuText.setText(output);
                
    }
    
    public void continueMain(String u){
        dispose();
        MenuThread m = new MenuThread(u, db);
        m.start();
    }
    
    
}
