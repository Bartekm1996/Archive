/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonStatParser;

import MachineLearningOutcome.OutcomeCalculator;

/**
 *
 * @author Colum
 */
public class ReadPlayerStats {//Takes a string and gets the player's stats out of it.

    public void parse(String lineFromFile) {
       String[] archive;
        archive=lineFromFile.split(",");
        int kills=Integer.parseInt(archive[1]);
        int deaths=Integer.parseInt(archive[2]);
        OutcomeCalculator temp= new OutcomeCalculator(archive[0],kills,deaths,archive[3]);
    }
    
}
