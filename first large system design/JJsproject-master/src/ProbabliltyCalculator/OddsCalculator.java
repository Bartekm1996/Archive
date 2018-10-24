/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProbabliltyCalculator;

import DatabaseController.PlayerDB;
import DatabaseController.TeamDB;
import MachineLearningOpinion.OpinionCalculator;

/**
 *
 * @author Colum
 */
public class OddsCalculator {

    public OddsCalculator(){
        
    }
    
    public OddsCalculator(String name, double outcomeS) {//Updates the database with the new odds for the team
         TeamDB temp =new TeamDB();
        temp.update(name,getOdds(outcomeS));
        
    }

    public OddsCalculator(String name, double outcomeS, String Tname) {//Updates the database with the new odds for the player
        PlayerDB temp =new PlayerDB();
        temp.update(name,getOdds(outcomeS),Tname);
    }
    
    public double getOdds(double outcomeS){//Gets the odds based on the current odds and the opinion value.
        OpinionCalculator cal=new OpinionCalculator();
        double playerProb=outcomeS/cal.getOpinionValue();
        return playerProb;
    }
}
