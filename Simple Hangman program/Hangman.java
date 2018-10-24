import java.util.*;
import javax.swing.*;
/* The code not marked was either implemented or written by Colum or Jake while using some lines from Aaron and Susans code to optiimise the program*/ 
/*Aaron made all outputs including the gallows array*/
public class Hangman
{
	public static void main(String[] args)
	{
		
		String playAns = JOptionPane.showInputDialog("Play Hangman?\n\tYes/No\t:\t");
		String output = "";
		String selection = "";
		String guess = "";
		String word = "";
		boolean validInput = isInputValid(playAns), categories = true, guessValid = true, checked = true;
		int gamesPlayed = 0,y = 0, randomWord = 0, runThrough = 0;
		String ulQuotes[] = {"Dingin", "Muppet", "NeeNaw","pints","craic"};
		String programming[] = {"Array","Method","Bubblesort","JOptionpane","loops"};
		String bars[] = {"Stables","Scholars","Pavillion","Sports","Hurlers"};
		Object[] options = {"UL Quotes", "Programming", "Bars"};
		Object[] options2 = new Object[2];
		String words[] ={""};
		String[] gallows = {"+---+\n|    |\n|    @\n|   /|\\ \n|    |\n|   / \\ \n|\n+---- ","+---+\n|    |\n|    @\n|   /|\\ \n|    |\n|   / \n|\n+----",
		"+---+\n|    |\n|    @\n|  /|\\ \n|    |\n| \n|\n+----","+---+\n|    |\n|    @\n|   /|\\ \n|\n|\n|\n+----","+---+\n|    |\n|    @\n|   /\n|\n|\n|\n+----",
		"+---+\n|    |\n|    @\n|\n|\n|\n|\n+----","+---+\n|    |\n|\n|\n|\n|\n|\n+----"};
		while ((validInput)&&(selection != null))
		{
			runThrough = 0;
			word = "used";
			options = optionsChecker(ulQuotes, programming, bars);/* To Make options disappear from the drop down menue as the array's empty */
			if  (options == null)
			{
				word = "used";
				JOptionPane.showMessageDialog(null, "All words played!");/* if all options  are used up a prompt will appear to inform the end user(Start of Aarons code)*/
				selection = null;
			}
			else
			{
				selection = (String)
				JOptionPane.showInputDialog(null,"Select a Category","Categories",1,null,options,options[0]);
			}
			if(selection != null)
			{
				switch(selection) 
				{
					case "UL Quotes":{    /*y has been assigned a value here to tell us which category has been choosen for the Switch Case further down*/
						y = 1;
						words = ulQuotes;
						}						break;
					case "Programming":{
						y = 2;
						words = programming;
					}						break;
					case "Bars":{
						y=3;
						words = bars;
					}						break;				
				}
			}	/* (End of Aarons code)*/
			while ((word == "used")&&(selection != null))
			{/* (Start of Susans code)*/
				randomWord = (int) (Math.random()* words.length);
				word = words[randomWord];
				switch(y)
				{
					case 1: ulQuotes[randomWord] = "used" ; break;	
					case 2: programming[randomWord] = "used"; break;
					case 3: bars[randomWord] = "used" ; break;
				}
				word = word.toLowerCase();
			}	/* (End of Susans code)*/
			if ((word != null)&&(selection != null))
			{
				boolean ongoing = true; 
				int x = 6;
				String[] wordArray = word.split(""), guessArray = new String[word.length()], previousGuess = new String[word.length()+6];
				for(y= 0 ; y< word.length() ; y++)
					guessArray[y] = "_";
				while (ongoing)
				{
					output = "";
					for (y = 0; y<guessArray.length; y++)
					{
						output += guessArray[y]+" ";
					}
					guessValid = false;
					while (guessValid == false)
					{	
						checked = false;
						guess = gameOutput(gallows, output, x, gamesPlayed);
						guess = guess.toLowerCase();
						guess = guess.trim();
						guess = guess.replaceAll("\\W","");  /* This edits the Guess so that no Digits or non word Characters can be Guessed*/
						guess = guess.replaceAll("\\d","");
						if ((guess.length() != 0)&&(previousGuess.length != 0))
							for (y = 0; (y < previousGuess.length) && checked == false ; y++)
							{
								if (guess.equals(previousGuess[y]))
								{
									JOptionPane.showMessageDialog(null, "You've already guessed : "+guess);
									checked = true;
								}
							}
						if ((checked == false)&&(guess.length() != 0 ))
							guessValid = true;
					}
					previousGuess[runThrough++] = guess;
					String[] guessarrayCopy = guessArray;
					boolean compare = false ;
					if (guess.equals(word))
					{
						x++;
						guessArray = guess.split("");
					}
					else if (guess.length() == 1)
					{
						for (int i = 0; i<wordArray.length; i++)
						{
							if (wordArray[i].equals(guess))
							{
								guessArray[i] = guess;
								compare = true;
							}
						}
					}
					if (compare)
						x++;
					x--;
					if ((Arrays.equals(guessArray, wordArray))||(x == 0))
					{
						if (((Arrays.equals(guessArray, wordArray)) != true)&&(x == 0))
							JOptionPane.showMessageDialog(null, gallows[0] + "\nLose! \nThe word was "+word );
						else
							JOptionPane.showMessageDialog(null, "Win\nYou had "+ x+" guesses remaining.");
						ongoing = false;
					}
				}
				playAns = JOptionPane.showInputDialog("Play Again?");
				validInput = isInputValid(playAns);
				gamesPlayed++;
			}
		}
	}
	public static boolean isInputValid(String Answer)/*this Method is here to Validate the response to the question "play again" (Written by Colum*/
	{
	 Answer = Answer.toLowerCase();
	 Answer = Answer.substring(0,1);
	 String Response1= "y";String Response2="n";
	 boolean ans= false;
	 if(Answer.equals(Response1)||Answer.equals(Response2))
	 {
		if(Answer.equals(Response1))
		{
			 ans= true;
		     return ans;
		}
	    else 
			return ans;
	 }
	 else 
		JOptionPane.showMessageDialog(null, "Invalid input: Valid response is either Yes or No");
	 return ans; /*it also shows an error Dialogue box if the response is not in the Desired State*/
	}	
	public static String[] checkAnswer(String guess, String[] wordArray,String[] guessArray )/* Method written by Susan */
	{
		boolean compare = false ;/*this Method Compares the word array to the Guess and fills it into the Guess Array if correct*/
		if ((guess.length()>1)&&(guess.length() == wordArray.length))
		{
			String[] checkArray = guess.split("");
			for (int y = 0 ; (y < wordArray.length)&&(compare == false) ; y++)
			{
				if (guessArray[y] != wordArray[y])
					compare = true;
			}
			if (compare == false)
				guessArray = guess.split("");
		}
		else
		{
			for (int i = 0; i<wordArray.length; i++)
			{
				if (wordArray[i].equals(guess))
				{
					guessArray[i] = guess;
				}
			}
		}
		return guessArray;
	}
	public static String gameOutput(String[] gallows,String output, int x,int gamesPlayed)/*Method written by Aaron*/
	{
		
		
		String result = JOptionPane.showInputDialog(gallows[x] + "\n"+ output + "\nGuesses Remaining : "+x+"     Games Played : "+gamesPlayed+"\nEnter a letter or a word to guess\t:");
		return result;
	}
	public static Object[] optionsChecker(String[] ulQuotes,String[] programming,String[] bars)/* Method written by Jake*/
	{
		Object[] options = new Object[3];
		Object[] options2 = new Object[2];
		Object[] options3 = new Object[1];
		int y = 0;
		if (((ulQuotes[0].equals("used")) != true)||((ulQuotes[1].equals("used")) != true)||((ulQuotes[2].equals("used")) != true)||((ulQuotes[3].equals("used")) != true)||((ulQuotes[4].equals("used")) != true))
			options[y++] = "UL Quotes";
		if (((programming[0].equals("used")) != true)||((programming[1].equals("used")) != true)||((programming[2].equals("used")) != true)||((programming[3].equals("used")) != true)||((programming[4].equals("used")) != true))
			options[y++] = "Programming";
		if (((bars[0].equals("used")) != true||((bars[1].equals("used")) != true)||((bars[2].equals("used")) != true)||((bars[3].equals("used")) != true)||((bars[4].equals("used")) != true)))
			options[y++] = "Bars";
		if (y == 1)
		{
			options3[0] = options[0];
			options = options3;
		}
		else if (y == 2)
		{
			options2[0] = options[0];
			options2[1] = options[1];
			options = options2;
		}
		if (y == 0)
			return null;
		else 
			return options;
	}
}

