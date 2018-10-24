/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Colum
 */
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
public class is15142434 {

    /**
     * @param args the command line arguments
     */
    public static int max = 0; 
    public static void main(String[] args) {
        
        ArrayList<String[]> matrix = readInput();
        int v=matrix.size();
        int[][] similarity = new int [v][v];
        int[] ordering = new int [v];
        Set(ordering);
        int P=0,Gen=0,Cr=0,Mu=0,H=0;
        boolean valid=false;
        if(addToSimilarity(similarity,matrix)){
            while(!valid)
		{
                    try
                    {
               		P=Integer.parseInt(JOptionPane.showInputDialog(null,"enter the size of the Population"));
                        if(P<=fact(v)&&P>0)
                        {
                            valid=true;
                        }
                    } 
                    catch(Exception ex)
                    {
                	JOptionPane.showMessageDialog(null, "You must enter a number");
                    }
            	}
            valid=false;
                while(!valid)
		{
                    try
                    {
               		Gen=Integer.parseInt(JOptionPane.showInputDialog(null,"enter the number of generations"));
                        if(Gen>0)
                        {
                             valid=true;
                        }
                    } 
                    catch(Exception ex)
                    {
                	JOptionPane.showMessageDialog(null, "You must enter a number");
                    }
            	}
            valid=false;
                while(!valid)
		{
                    try
                    {
               		Cr=Integer.parseInt(JOptionPane.showInputDialog(null,"enter the probability of cross-over"));
                        Mu=Integer.parseInt(JOptionPane.showInputDialog(null,"enter the probability of mutation"));
                        if((Cr<=100)&&(Cr>0)&&(Mu<=100)&&(Mu>0)&&(Cr+Mu<=100))
                        {
                            valid=true;
                        }
                    } 
                    catch(Exception ex)
                    {
                	JOptionPane.showMessageDialog(null, "You must enter a number");
                    }
            	}
                valid=false;
            while(!valid)
		{
                    try
                    {
               		H=Integer.parseInt(JOptionPane.showInputDialog(null,"enter the generations you wish to view the heat map for"));
                        
                        if(Gen>H)
                        {
                            valid=true;
                        }
                    } 
                    catch(Exception ex)
                    {
                	JOptionPane.showMessageDialog(null, "You must enter a number");
                    }
            	}
            max = MaxValue(similarity);
	RandomOrdering(P,ordering);
	set(ordering);		  // Find the maximum value in the similarity matrix. It is needed in order to encode the colors
            TableCreation(similarity, ordering.length, ordering); // send your data for heat map visualization.
            
        }
        else
        {
             JOptionPane.showMessageDialog(null,"error Matrix is not valid along the diagonal axis");
        }
    }
    
    public static ArrayList readInput(){
        String filename="input.txt";
        String lineFromFile="";
        String fileElements[];
        ArrayList<String[]> matrix= new ArrayList<String[]>(); 
        Scanner in = null;
        File aFile= new File(filename);
        try {
            in= new Scanner(aFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(is15142434.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(in.hasNext())
            {
                lineFromFile=in.nextLine();
                fileElements= lineFromFile.split("  ");
                matrix.add(fileElements);
            }
        in.close();
        if(!(matrix.size()==matrix.get(1).length)){
            JOptionPane.showMessageDialog(null,"error Matrix is not a square");
           
        }
        return matrix;
    }
    
    public static void TableCreation(int[][] sim, int v, int[] ord) {

		final Object rowData[][] = new Object[v][v];
		for (int i = 0; i < v; i++) {
			for (int j = 0; j < v; j++) {
				rowData[i][j] = sim[i][j];
			}
		}
		final String columnNames[] = new String[v];
		for (int i = 0; i < v; i++) {
			columnNames[i] = ord[i] + "";
		}
		final JTable table = new JTable(rowData, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setDefaultRenderer(Object.class, new EvenOddRenderer());
		JFrame frame = new JFrame("Heat Map");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.setSize(50*v, 50*v);
		frame.setVisible(true);
	}

    private static boolean addToSimilarity(int[][] similarity, ArrayList<String[]> matrix) {
        for(int i=0; i<matrix.size();i++)
        for(int j=0;j<matrix.size();j++)
        {
            similarity[i][j]=Integer.parseInt(matrix.get(i)[j]);
        }
        boolean check=true;
        int x=0;
        for(int j=0;j<matrix.size();j++){
          if(!(similarity[j][x]==0)){
              check=false;
          }
          x++;
        }
        return check;
    }

    private static long fact(int v) {
        if (v == 0) {
            return 1;
        }
        long fact = 1; // this  will be the result
        for (int i = 1; i <= v; i++) {
            fact *= i;
        }
        return fact;
    }

    private static void RandomOrdering(int P, int[] ordering) 
    {
        int index, temp;
        String[] orderingWriter = new String [P];
        Random random = new Random();
        for(int j=0;j<P;j++)
        {
            orderingWriter[j]="";
            for (int i = ordering.length - 1; i >= 0; i--)
            {
                index = random.nextInt(i + 1);
                temp = ordering[index];
                ordering[index] = ordering[i];
                ordering[i] = temp;
                orderingWriter[j]+=ordering[i]+" ";
            }
            
        }
        WriteOrderings(orderingWriter);
    }

    private static void WriteOrderings(String[] ordering) {
         PrintWriter writer = null;
            try {
                writer= new PrintWriter("AI17.txt");
                for(int x = 0; x < ordering.length ;x++)
                {
                    writer.println(ordering[x]);
                }   
            } catch (IOException ex) {
                Logger.getLogger(is15142434.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                    writer.close();
               
            }
    }

    private static void Set(int[] ordering) {
        for(int p=0;p<ordering.length;p++){
            ordering[p]=p+1;
        }
    }

    private static int MaxValue(int[][] similarity) 
    {
    int m = similarity[0][0];
    for(int r=0;r < similarity.length;r++){
    for (int ktr = 0; ktr < similarity.length; ktr++) {
        if (similarity[r][ktr] > m) {
            m = similarity[r][ktr];
        }
    }
    }
    return m;
}
    }

class EvenOddRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		Color foreground, background;

		double val = Double.parseDouble(value.toString());
		val = 255 - 255 * ((double) val / (double) is15142434.max);
		Color col = new Color((int) val, 0, 0);
		foreground = Color.WHITE;
		background = col;

		renderer.setForeground(foreground);
		renderer.setBackground(background);
		return renderer;
	}
}
