/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import JsonStatParser.ReadPlayerStats;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Colum
 */
public class ViewUpcomingGames {
    private ArrayList <String[]> list;
    public ViewUpcomingGames(){//Gets the upcoming games and puts them in a list.
     
       
             list = new ArrayList<String[]>();
            String filename="uGames.csv";
            String lineFromFile="";
            Scanner in;
            File aFile;
            
            aFile= new File(filename);
        try {  
            in= new Scanner(aFile);
            while(in.hasNext())
            {
                lineFromFile=in.nextLine();
                String[] archive;
                archive=lineFromFile.split(",");
                list.add(archive);
            }
            in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewUpcomingGames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<String[]> getFixture(){//Returns the list
        return list;
    }
}
