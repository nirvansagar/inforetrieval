package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

public class Starter {

	public static void main(String[] args) {
		String fileName = null;
		if(args.length == 0){
			//no filename given. user input required
			Scanner reader = new Scanner(System.in);  // Reading from System.in
			System.out.println("Enter file with complete path: ");
			fileName = reader.nextLine();
			reader.close();
		} else {
			fileName = args[0];
			System.out.println(fileName);
		}		 
		File file = new File(fileName);
		if(!file.exists()){
			System.out.println("File doesnt exist " + file.getAbsolutePath());
			System.exit(0);
			
		} else {
			System.out.println("File exists");
		}
		
		FileTokenizer ft = new FileTokenizer(file);
		try {
			ft.tokenizeFile();
		} catch (FileNotFoundException e) {
			System.out.println("Problem reading file");
			e.printStackTrace();
		}
		
		testPartA(ft);
		testPartB(ft);
		testPartC(ft);
		testPartD(ft);		
		
	}

	private static void testPartA(FileTokenizer ft) {
		ft.printTokens();		
	}

	private static void testPartB(FileTokenizer ft) {
		ft.computeTokenFrequencies(ft.getListOfTokens());
		ft.printTokenFrequencies();		
	}

	private static void testPartC(FileTokenizer ft) {
		ft.computeThreeGrams(ft.getListOfTokens());
		ft.printThreegrams();		
	}

	private static void testPartD(FileTokenizer ft) {
		Anagrammer ang = new Anagrammer();		
		
		ArrayList<Entry<String, ArrayList<String>>> anagrams;
		anagrams = ang.detectAnagrams(ft.getListOfTokens());
		ang.printAnagrams(anagrams);		
	}

}
