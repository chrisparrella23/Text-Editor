package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextArea;

public class Calculations {
	private static double wordCount;
	private static double sentenceCount;
	private static double syllableCount;
	private static double fleschScore;
	static DecimalFormat df = new DecimalFormat("0.00");

	public static double getWordCount(String textFile) throws FileNotFoundException {
		File file = new File(textFile);
		BufferedReader br = new BufferedReader(new FileReader(file));
		Scanner fileScanner = new Scanner(br);
		String str = "";
		String[] words = null;

		try {
			while ((str = br.readLine()) != null) {
				words = str.split(" ");
				wordCount += words.length;
				// System.out.println(Arrays.toString(words));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordCount;
	}

	public static double wordCountFromText(TextArea theArea) {
		String str = theArea.getText();
		String[] words = str.split("[ ]|[\n]+");
		wordCount = words.length;
		return wordCount;
	}

	public static double wordCountFromString(String areaText) {
		String[] words = areaText.split("[ ]|[\n]+");
		wordCount = words.length;
		return wordCount;
	}

	public static int wordCountRegex(TextArea theArea) {
		String str = theArea.getText();

		String pattern = "[ ]|[\n]";
		Matcher m = Pattern.compile(pattern).matcher(str);
		int count = 0;

		while (m.find()) {
			count++;
		}

		// return at least 1
		return Math.max(count, 1);
	}

	public static double sentenceCountFromText(TextArea theArea) {
		String str = theArea.getText();
		String[] sentences = str.split("[.?!]+");
		sentenceCount = sentences.length;
		return sentenceCount;
	}

	public static double sentenceCountFromString(String areaText) {
		String[] sentences = areaText.split("[.?!]+");
		sentenceCount = sentences.length;
		return sentenceCount;
	}

	public static double fleschFromText(TextArea theArea) {
		String str = theArea.getText();
		double wordCnt = (int) wordCountFromText(theArea);
		double sentenceCnt = (int) sentenceCountFromText(theArea);
		double syllables = totalSyllableCount(theArea);
		double wos = (wordCnt / sentenceCnt);
		double sow = (syllables / wordCnt);
		double flesch = 206.835 - 1.015 * (wos) - 84.6 * (sow);
		String fleschScore = df.format(flesch);
		return Double.valueOf(fleschScore);
	}

	public static int totalSyllableCount(TextArea area) {
		String[] words = area.getText().split(" ");
		int syllableCount = 0;

		for (int i = 0; i < words.length; i++) {
			syllableCount += countWithRegex(words[i]);
		}
		return syllableCount;
	}

	public static int totalSyllables(String areaText) {
		String[] words = areaText.split(" ");
		int syllableCount = 0;
		for (int i = 0; i < words.length; i++) {
			syllableCount += countWithRegex(words[i]);
		}
		return syllableCount;
	}

//	public static double syllableCounter(TextArea theArea) {
//		String str = theArea.getText();
//		String syllables = "(?i)(ea)|(?i)(ee)|(?i)(ou)|(?i)(io)|(?i)(ai)|(?i)(ia)|(?i)(au)|(?i)(ei)|(?i)(eu)|(?i)(ie)|(?i)(oe)|(?i)(oi)|(?i)(oo)|(?i)(ue)|(?i)(ua)|(?i)(eo)|(?i)(e )|(?i)[aeiou]";
//		Pattern syllablePatt = Pattern.compile(syllables);
//		Matcher syllableMatch = syllablePatt.matcher(str);
//		syllableMatch.reset();
//
//		int counter = 0;
//		while (syllableMatch.find()) {
//			counter++;
//		}
//		return counter;
//	}

	public static int countWithRegex(String word) {
		String i = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
		Matcher m = Pattern.compile(i).matcher(word);
		int count = 0;

		while (m.find()) {
			count++;
		}

		// return at least 1
		return Math.max(count, 1);
	}

	public static String[] truncate(String arr) {
		int SIZE = 20;
		int length = 5;
		String[] result = new String[SIZE];
		for (int i = 0; i < result.length; i++) {
			int percent = (int) (arr.length() * (length / 100.0));

			String s = arr.substring(0, (int) percent);
			result[i] = s;
			length = length + 5; // Increment by 5%
		}

		return result;
	}

	public double getSyllableCount() {
		return syllableCount;
	}

	public static long[] calcOneLoop(String[] trunc, TextArea area) {
		long[] times = new long[trunc.length];
		for (int i = 0; i < trunc.length; i++) {
			long time = System.currentTimeMillis();
			long[] values = oneLoop(trunc[i]);
			fleschScore = 206.835 - (1.015 * (values[1] / values[0])) - (84.6 * (values[2] / values[1]));
			time = (System.currentTimeMillis() - time);
			times[i] = time;
		}
//		 System.out.println(wordCount);
//		 System.out.println(sentenceCount);
//		 System.out.println(syllableCount);
//		 System.out.println(fleschScore);
		// for (int i = 0; i < times.length; i++) {
		// System.out.print(times[i] + " ");
		// }
		return times;
	}
	
	public static long[] oneLoop(String text) {
        final String sentREGEX = "[.?!]+";
        final String wordREGEX = "[ ]|[\n]+";
        final String syllREGEX = "(?i)[aiou][aeiou]*|e[aeiou]*(?!d?\\b)";
        
        Matcher m1 = Pattern.compile(sentREGEX).matcher(text);
        Matcher m3 = Pattern.compile(wordREGEX).matcher(text);
        Matcher m2 = Pattern.compile(syllREGEX).matcher(text);

        long one = 0;
        long two = 0;
        long three = 0;
        
        while(m2.find()) {
            if(m2.find()) {
                three++;
            }
            if(m1.find()) {
                one++;
            }
            if(m3.find()){
                two++;
            }
        }
        return new long[]{one, two, three};
    }

	public static long[] calcThreeLoops(String[] trunc, TextArea area) {
		long[] times = new long[trunc.length];
		// String text = area.getText();
		for (int j = 0; j < trunc.length; j++) {
			long time = System.currentTimeMillis();
				wordCount = wordCountFromString(trunc[j]);
				sentenceCount = sentenceCountFromString(trunc[j]);
				syllableCount = totalSyllables(trunc[j]);
			fleschScore = 206.835 - 1.015 * (wordCount / sentenceCount) - 84.6 * (syllableCount / wordCount);
			time = (System.currentTimeMillis() - time);
			times[j] = time;
		}
//		 System.out.println(wordCount);
//		 System.out.println(sentenceCount);
//		 System.out.println(syllableCount);
//		 System.out.println(fleschScore);
//		 for (int i = 0; i < times.length; i++) {
//		 System.out.print(times[i] + " ");
//		 }
		return times;
	}

}
