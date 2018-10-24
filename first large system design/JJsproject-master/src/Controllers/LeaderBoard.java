/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DatabaseController.LeaderboardDB;

/**
 *
 * @author Colum
 */
public class LeaderBoard {//A go-between for the UI and the database package.
    
    public String[][] getUsers(){
        LeaderboardDB lDB = new LeaderboardDB();
        String[][] uArr = lDB.getLeaderBoard();
        return uArr;
    }
}
