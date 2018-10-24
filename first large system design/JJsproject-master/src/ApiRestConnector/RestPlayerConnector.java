
package ApiRestConnector;

import JsonStatParser.ReadPlayerStats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

public class RestPlayerConnector {
    public RestPlayerConnector(){//Gets the players and adds them to the database.
        //switched to csv
        String filename="Player.csv";
        String lineFromFile="";
        String fileElements[];
        Scanner in;
        File aFile;
        aFile= new File(filename);
        try {
        in= new Scanner(aFile);
        ReadPlayerStats parser= new ReadPlayerStats ();
        while(in.hasNext())
        {
            lineFromFile=in.nextLine();
            parser.parse(lineFromFile);
            
        }
        in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestPlayerConnector.class.getName()).log(Level.SEVERE, null, ex);
        }         
                
        
    }
    }
  
       /*    pandascoreURL= new URL("https://api.pandascore.co/lol/players.json?page1&token="+token+"&sort=id");
           try {
               HttpsURLConnection conn = (HttpsURLConnection) pandascoreURL.openConnection();
               jsonString1 = printContent(conn);
             
           } catch (IOException ex) {
               Logger.getLogger(RestPlayerConnector.class.getName()).log(Level.SEVERE, null, ex);
           }
           
    }
    private static String printContent(HttpsURLConnection conn){
	String output = "Invalid connection";
        if(conn!=null)
        {
            output = "";
            try 
            {
                BufferedReader br =
                    new BufferedReader(
			new InputStreamReader(conn.getInputStream()));

                String input;

                while ((input = br.readLine()) != null){
                output += input + "\n";
	   }
	   br.close();
           
            }
            catch (IOException ioe)
            {
                System.out.println(ioe.toString());
            }
       }
        return output;
   }*/

