/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MachineLearningOpinion;

/**
 *
 * @author Colum
 */
public class OpinionCalculator {
    private double randomNum ;
    public OpinionCalculator(){
    randomNum = (double)(Math.random()*99+1);
    }
    public double getOpinionValue(){
      return randomNum;  
    }
}

