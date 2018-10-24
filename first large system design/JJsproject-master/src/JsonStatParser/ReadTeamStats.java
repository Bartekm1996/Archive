/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JsonStatParser;

import MachineLearningOutcome.OutcomeCalculator;
import java.util.ArrayList;

/**
 *
 * @author Colum
 */
public class ReadTeamStats {//Takes a string and gets the team's stats out of it.
    
    public void parse(String lineFromFile) {
       String[] archive;
        archive=lineFromFile.split(",");
        int wins=Integer.parseInt(archive[1]);
        int losses=Integer.parseInt(archive[2]);
        OutcomeCalculator temp= new OutcomeCalculator(archive[0],wins,losses);
    }
    
}
