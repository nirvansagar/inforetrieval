package part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class Anagrammer {
	private HashMap<Character, BigInteger> primeToCharMap;
	private HashMap<BigInteger, ArrayList<String>> database;
	private boolean databaseInitialized;
	
	public Anagrammer(){
		databaseInitialized = false;
		primeToCharMap = new HashMap<>();
		database = new HashMap<>();
		initializePrimeToCharMap();
	}
	
	private void lazyInitializeDatabase(){
		long startTime = System.currentTimeMillis();
		File f = new File("files/SINGLE.TXT");
		Scanner scanner = null;
		try {
			scanner = new Scanner(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not found " + f.getAbsolutePath());
			System.exit(0);
		}
		while(scanner.hasNextLine()){
			String s = scanner.nextLine();
			BigInteger code = getStringUniqueCode(s);
			addTokenToDatabaseForCode(code, s);
		}
				
		databaseInitialized = true;
		scanner.close();
		long endTime = System.currentTimeMillis();
//		System.out.println("Database initialized: " + (endTime-startTime));
	}
	
	private void addTokenToDatabaseForCode(BigInteger code, String s) {
		ArrayList<String> listForCode;
		if(database.containsKey(code)){
			listForCode = (ArrayList<String>) database.get(code);
		} else {
			listForCode = new ArrayList<>();
		}
		listForCode.add(s);
		database.put(code, listForCode);
	}
	
	private void initializePrimeToCharMap() {
		primeToCharMap.put('a', BigInteger.valueOf(2));
		primeToCharMap.put('b', BigInteger.valueOf(3));
		primeToCharMap.put('c', BigInteger.valueOf(5));
		primeToCharMap.put('d', BigInteger.valueOf(7));
		primeToCharMap.put('e', BigInteger.valueOf(11));
		primeToCharMap.put('f', BigInteger.valueOf(13));
		primeToCharMap.put('g', BigInteger.valueOf(17));
		primeToCharMap.put('h', BigInteger.valueOf(19));
		primeToCharMap.put('i', BigInteger.valueOf(23));
		primeToCharMap.put('j', BigInteger.valueOf(29));
		primeToCharMap.put('k', BigInteger.valueOf(31));
		primeToCharMap.put('l', BigInteger.valueOf(37));
		primeToCharMap.put('m', BigInteger.valueOf(41));
		primeToCharMap.put('n', BigInteger.valueOf(43));
		primeToCharMap.put('o', BigInteger.valueOf(47));
		primeToCharMap.put('p', BigInteger.valueOf(53));
		primeToCharMap.put('q', BigInteger.valueOf(59));
		primeToCharMap.put('r', BigInteger.valueOf(61));
		primeToCharMap.put('s', BigInteger.valueOf(67));
		primeToCharMap.put('t', BigInteger.valueOf(71));
		primeToCharMap.put('u', BigInteger.valueOf(73));
		primeToCharMap.put('v', BigInteger.valueOf(79));
		primeToCharMap.put('w', BigInteger.valueOf(83));
		primeToCharMap.put('x', BigInteger.valueOf(89));
		primeToCharMap.put('y', BigInteger.valueOf(97));
		primeToCharMap.put('z', BigInteger.valueOf(101));
	}
	
	BigInteger getStringUniqueCode(String s){
		s=s.toLowerCase();
		BigInteger code = BigInteger.valueOf(1), charCode;
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			if(primeToCharMap.containsKey(c)){
				charCode = primeToCharMap.get(c);				
			} else {
				charCode = BigInteger.valueOf(1);
			}
			code = code.multiply(charCode);
		}		
		return code;
	}
	
	
	ArrayList<Entry<String, ArrayList<String>>> detectAnagrams(List<String> tokens){
		if(!databaseInitialized){
			lazyInitializeDatabase();
		}
		ArrayList<Entry<String, ArrayList<String>>> anagrams = new ArrayList<>();
//		System.out.println("database size " + database.size());
		for(int i=0; i<tokens.size();i++){
			BigInteger tokenCode = getStringUniqueCode(tokens.get(i));
			ArrayList<String> tokenAnagrams = database.get(tokenCode); 
			if(tokenAnagrams == null){
				tokenAnagrams = new ArrayList<>();
			}
//			System.out.println("found anagrams for " +tokens.get(i)+ tokenAnagrams.size());
			Entry<String, ArrayList<String>> e = new java.util.AbstractMap.SimpleEntry<>(tokens.get(i), tokenAnagrams);
			anagrams.add(e);
		}		
		return anagrams;		
	}
	
	@SuppressWarnings("unchecked")
	void printAnagrams(ArrayList<Entry<String, ArrayList<String>>> anagrams){
		System.out.println("***************Printing anagrams:");
		for(int i=0; i<anagrams.size();i++){
			Entry<String, ArrayList<String>> e = anagrams.get(i);
			ArrayList<String> sorted = (ArrayList<String>) e.getValue().clone();
			sorted.remove(e.getKey());
			Collections.sort(sorted);
			String allAnagrams = e.getKey() + ": ";
			for(int j=0;j<sorted.size();j++){
				allAnagrams += sorted.get(j) + " ";
			}
			System.out.println(allAnagrams);
		}		
	}

}
