package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.Demo;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.BabyLink;
import model.BabyLinkedList;
import model.BabyListIterator;
import model.MasterLink;
import model.MasterLinkedList;

public class FileHelper {
	private static double wordCount;
	private static double sentenceCount;
	private static double syllableCount;
	private static double fleschScore;
	static DecimalFormat df = new DecimalFormat("0.00");
	private static MasterLinkedList masterList;
	private String fileName;
	private File file;
	
	public FileHelper(String fileName) {
		super();
		this.fileName = fileName;
		this.file = new File(fileName);
	}
	
	public void readInFile(MasterLinkedList masterList) {
		Scanner fileScanner;
		
		try {
			fileScanner = new Scanner(this.file);
			String current = fileScanner.next();
			String next = fileScanner.next();
			
			masterList.insertLast(current, next);
			
			while(next != null) {
				current = next;
				if(fileScanner.hasNext()) {
					next = fileScanner.next();
				} else {
					next = null;
					return;
				}
				
				MasterLink masterLink = masterList.getLink(current);
				if(masterLink != null) {
					if(masterLink.hasBabyList()) {
						masterLink.getBabyList().insertLast(next);
					} else {
						System.out.println("ERROR: No babyList found for " + masterLink);
					}
				} else {
					masterList.insertLast(current, next);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}


	
	public String getRandomWord(MasterLink key, int num) {
		BabyListIterator theIterator = new BabyListIterator(key.getBabyList());
		String randWord = "***";
		if (theIterator.getCurrent().getNext() == null) {
			randWord = theIterator.getCurrent().getiData();
			return randWord;
		}
		for (int i = 0; i < num; i++) {
			randWord = theIterator.getCurrent().getiData();
			theIterator.nextLink();
			if (theIterator.getCurrent() == null) {
				theIterator.reset();
			}
		}
		return randWord;
	}

	public void saveText(String content) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT files (*.txt)", "*.txt"));
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Data Files", "*.dat"));
		File file = fileChooser.showSaveDialog(null);
		if (file != null) {
			SaveFile(content, file);
		}
	}

	public void SaveFile(String content, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void open(TextArea theArea, File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		Scanner fileScan = new Scanner(new BufferedReader(fr));
		while (fileScan.hasNextLine()) {
			sb.append(fileScan.nextLine() + "\n");
		}
		fileScan.close();
		theArea.setText(sb.toString());
	}

	public static void getResults(String textFile, TextArea theArea) {
		File file = new File(textFile);
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		Scanner fileScan = new Scanner(new BufferedReader(fr));
		while (fileScan.hasNextLine()) {
			sb.append(fileScan.nextLine() + "\n");
		}
		fileScan.close();
		theArea.setText(sb.toString());
	}

}
