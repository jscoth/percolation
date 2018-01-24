package fire;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordFrequency {

	public static void main (String args[]) throws FileNotFoundException
	{
		Scanner input = new Scanner(new File("C:\\Users\\Home\\Documents\\answers.txt"));
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		while (input.hasNextLine())
		{
			String[] thisLine = input.nextLine().split(" ");
			for (String s : thisLine)
			{
				System.out.println(s);
			}
		}
		
		input.close();
	}
	
}
