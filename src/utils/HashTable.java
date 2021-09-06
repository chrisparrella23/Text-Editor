package utils;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.scene.control.TextArea;

public class HashTable {
	
	public static String getDictionary(String file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		Scanner fileScan = new Scanner(new BufferedReader(fr));
		while (fileScan.hasNextLine()) {
			sb.append(fileScan.nextLine() + " ");
		}
		fileScan.close();
		return sb.toString();
	}
	
	public static HashSet<String> fillHashTable(HashSet<String> set, String string) {
		String[] words = string.split("\\s+");
		int length = words.length;
		for (int i = 0; i < words.length; i++) {
			set.add(words[i]);
		}
		return set;
	}
	
	public static LinkedList<String> checkWordSpelling(TextArea theArea, HashSet<String> set) {
		LinkedList<String> errorWords = new LinkedList<String>();
		String[] words = theArea.getText().split("[^\\w']+");
		for (int i = 0; i < words.length; i++) {
			if (!set.contains(words[i]) && !set.contains(words[i].toLowerCase())) {
				errorWords.add(words[i]);
			}
		}
		return errorWords;
	}
	
//	public static String removePunctuations(String s) {
//	    String res = "";
//	    for (Character c : s.toCharArray()) {
//	        if(Character.isLetterOrDigit(c))
//	            res += c;
//	    }
//	    return res;
//	}
	
}
