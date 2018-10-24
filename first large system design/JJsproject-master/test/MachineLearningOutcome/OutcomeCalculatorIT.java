/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MachineLearningOutcome;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jakec
 */
public class OutcomeCalculatorIT {
    
    public OutcomeCalculatorIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of OddsMaker method, of class OutcomeCalculator.
     */
    @Test
    public void testOddsMaker() {
        System.out.println("OddsMaker");
        int wins = 5;
        int losses = 1;
        OutcomeCalculator instance = new OutcomeCalculator();
        double expResult = 0.0;
        boolean tester = true;
        boolean testee = false;
        double result = instance.OddsMaker(wins, losses);
        System.out.println(result);
        if(result > 0 && result < 1)
               testee = true;
        else{
            fail("The test case is a prototype.");
        }
        // TODO review the generated test code and remove the default call to fail.
    }
    
}
