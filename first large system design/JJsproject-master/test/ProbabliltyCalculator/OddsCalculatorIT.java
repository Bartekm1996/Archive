/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProbabliltyCalculator;

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
public class OddsCalculatorIT {
    
    public OddsCalculatorIT() {
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
     * Test of getOdds method, of class OddsCalculator.
     */
    @Test
    public void testGetOdds() {
        System.out.println("getOdds");
        double outcomeS = 0.5;
        OddsCalculator instance = new OddsCalculator();
        double expResult = 0.0;
        double result = instance.getOdds(outcomeS);
        if(result > 1 || result < 0)
            fail("The test case is a prototype.");
    }
    
}
