package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import ConnectSoap.Soap;
import java.util.logging.Logger;


/**
 *
 * @author james.murphy
 */
public class TicTacToeGame extends JFrame implements ActionListener {

    private JPanel gameBoard;
    private JPanel menu;
    private JLabel menuText, curPlayerID;
    private JButton[] squares;
    private JButton reset;
    private String type;
    private int[][] board;
    private Color[] playerBadge;
    private int player;
    private int numSides;
    private int gameState;  /* -2: new game, -1: draw, 0: player1 win, 1: player2 win */
    private int numSquares;
    private boolean turn;
    private String uname;
    private String joiner;
    private Soap db;
    private boolean resetRequest = false;
    private boolean myReset = false;
    private boolean threadRun = true;
    
    public TicTacToeGame(String uname, String joiner, String type, Soap db){
        this.db = db;
        this.uname = uname;
        this.joiner = joiner;
        this.type = type;
        gameState = -2;
        player = 0;
        numSides = 3;
        numSquares = 9;
        
       if(type.equals("X"))
           turn = true;
       else
           turn = false;
       
       
        board = new int[numSides][numSides];
        playerBadge = new Color[2];
        
        playerBadge[0] = Color.RED;
        playerBadge[1] = Color.GREEN;
        
        this.setTitle("Tic Tac Toe Game board");
        this.setBounds(100,100,500,300);
        this.setPreferredSize(new Dimension(500,300));
        this.setLayout(new GridLayout(1,2));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        gameBoard = new JPanel();
        gameBoard.setBounds(new Rectangle(300,300));
        gameBoard.setLayout(new GridLayout(3,3));
        squares = new JButton[9];
        for(int i=0;i<9;i++) {
            squares[i] = new JButton(" ");
            squares[i].addActionListener(this);
            gameBoard.add(squares[i]);
        }
        
        for(int i=0;i<numSides;i++) {
            for(int j=0;j<numSides;j++) {
                board[i][j] = -1;
                int pos = ((i*numSides) + j);
                squares[pos].setBackground(Color.WHITE);
            }
        }

        
        menu = new JPanel();
        menu.setLayout(new GridLayout(3,1));
        
        menuText = new JLabel("Click on a square to start.", SwingConstants.CENTER);
        menuText.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        curPlayerID = new JLabel("It's player " + (player + 1) + "'s move", SwingConstants.CENTER);
        if(turn)
            curPlayerID.setText("It's your move");
        else
            curPlayerID.setText("It's player " +this.joiner+ "'s move");
        curPlayerID.setBackground(playerBadge[player]);
        curPlayerID.setForeground(Color.WHITE);
        curPlayerID.setOpaque(true);
        curPlayerID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        reset = new JButton("Quit");
        reset.addActionListener(this);
        
        menu.add(menuText);
        menu.add(curPlayerID);
        menu.add(reset);
        
        this.add(gameBoard);
        this.add(menu);
        this.pack();
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        JButton source = (JButton) ae.getSource();
        
        if(source.equals(reset)) {
            try {
                db.setMove("QUIT", uname);
                dispose();
                sleep(1000);
                quit();
            } catch (Exception ex) {
                Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(turn){
            if(source.equals(squares[0])) {
                play(0);
                try {
                    db.setMove("MOVE,0", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[1])) {
                play(1);
                try {
                    db.setMove("MOVE,1", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[2])) {
                play(2);
                try {
                    db.setMove("MOVE,2", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[3])) {
                play(3);
                try {
                    db.setMove("MOVE,3", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[4])) {
                play(4);
                try {
                    db.setMove("MOVE,4", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[5])) {
                play(5);
                try {
                    db.setMove("MOVE,5", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[6])) {
                play(6);
                try {
                    db.setMove("MOVE,6", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[7])) {
                play(7);
                try {
                    db.setMove("MOVE,7", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(source.equals(squares[8])) {
                play(8);
                try {
                    db.setMove("MOVE,8", uname);
                } catch (Exception ex) {
                    Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
    
    public void takeSquare(int x, int y, int p) {
        board[x][y] = player;
    }
    
    public void play(int n){
        if(turn)
            turn = false;
        else
            turn = true;
        
        
        if(gameState == -2) {
            if(!taken(n) && numSquares > 0) {
                squares[n].setBackground(playerBadge[player]);
                int y = n%numSides;
                int x = (int) n/numSides;
                takeSquare(x, y, player);
                gameState = getGameState(player);
                switch(gameState) {
                    case -2:
                        player = getPlayer();
                        numSquares = numSquares - 1;
                        if(turn)
                            curPlayerID.setText("It's your move");
                        else
                            curPlayerID.setText("It's player " +joiner+ "'s move");
                        curPlayerID.setBackground(playerBadge[player]);
                    break;
                    
                    case -1:
                        JOptionPane.showMessageDialog(null, "Game over.\n It's a draw", "Drawn Game!", INFORMATION_MESSAGE);
                        db.setDraw(uname);
                    break;
                    
                    case 0: 
                        JOptionPane.showMessageDialog(null, "Game over.\n Player 1 Wins!", "PLAYER 1 WINS!", INFORMATION_MESSAGE);
                        if(type.equals("X"))
                            db.setWin(uname);
                        else
                            db.setLoss(uname);
                        
                    break;
                    
                    case 1:
                        JOptionPane.showMessageDialog(null, "Game over.\n Player 2 Wins!", "PLAYER 2 WINS!", INFORMATION_MESSAGE);
                        if(!type.equals("X"))
                            db.setWin(uname);
                        else
                            db.setLoss(uname);
                    break;
                    
                    default:
                }
            } else {
                JOptionPane.showMessageDialog(null, "That square is already taken.\n Please try again.", "Square taken!", ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Game Over.\n Please press reset to restart.", "Game Over", ERROR_MESSAGE);    
        }
    }
    
    public int getPlayer() {
        player = player + 1;
        player = player % 2;
        return player;
    }
    
    public int getGameState(int p) {
        int state = -2;
        
        for(int i=0;i<numSides;i++) {
            if(board[i][0] == p && board[i][1] == p && board[i][2] == p) {
                state = p;
            }
            if(board[0][i] == p && board[1][i] == p && board[2][i] == p) {
                state = p;
            }
        }
        
        if(board[0][0] == p && board[1][1] == p && board[2][2] == p) {
            state = p;
        }
        if(board[0][2] == p && board[1][1] == p && board[2][0] == p) {
            state = p;
        }
        
        if(numSquares == 1) {
            state = -1;
        }
        return state;
    }
    
    public int[][] getBoard() {
        return board;
    }
    
    public boolean taken(int n) {
        int y = n%numSides;
        int x = (int) n/numSides;
        
        board = getBoard();
        if(board[x][y] == -1) {
            return false;
        } else {
            return true;
        }
    }
    
    public void reset() {
        resetRequest = false;
        gameState = -2;
        player = 0; 
        numSquares = 9;
        
        for(int i=0;i<numSides;i++) {
            for(int j=0;j<numSides;j++) {
                board[i][j] = -1;
                squares[((i*numSides) + j)].setBackground(Color.WHITE);
            }
        }
        if(turn)
            curPlayerID.setText("It's your move");
        else
            curPlayerID.setText("It's player " +joiner+ "'s move");
        curPlayerID.setBackground(playerBadge[player]);
    }

    public void resetRequest() {
        myReset = true;
        try {
            db.setMove("RESET", uname);
        } catch (Exception ex) {
            Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setReset(){
        resetRequest = true;
    }
    
    public boolean getReset(){
        return myReset;
    }
    public void disableReset(){
        resetRequest = false;
        myReset = false;
    }
    
    public boolean threadRun(){
        return threadRun;
    }
    public void quit(){
        try {
                threadRun = false;
                dispose();
                MenuThread m = new MenuThread(uname, db);
                m.start();
            } catch (Exception ex) {
                Logger.getLogger(TicTacToeGame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
