package utils;

import model.BabyListIterator;
import model.MasterLink;
import model.MasterLinkedList;

public class TextGenerator {
private MasterLinkedList masterLinkedList;
	
	public TextGenerator(MasterLinkedList masterLinkedList) {
		super();
		this.masterLinkedList = masterLinkedList;
	}

	public String generateRandomText(String startingWord, int numWords) {
		String result = startingWord + " ";
		MasterLink current = masterLinkedList.getLink(startingWord);
		if(current != null) {
			for(int i = 0; i < numWords-1; i++) {
				int random = (int)(Math.random() * 20) + 1;
				String randWord = getRandomWord(current, random);
				current = masterLinkedList.getLink(randWord);
				result += randWord + " ";
			}
		} else {
			return "'" + startingWord + "' not found in masterList.";
		}
		return result;
	}
	
	public String getRandomWord(MasterLink key, int num) {
		BabyListIterator theIterator = new BabyListIterator(key.getBabyList());
		String randWord = "***";
		if(theIterator.getCurrent().getNext() == null) {
			randWord = theIterator.getCurrent().getiData();
			return randWord;
		}
		for(int i = 0; i < num; i++) {
			randWord = theIterator.getCurrent().getiData();
			theIterator.nextLink();
			if(theIterator.getCurrent() == null)
				theIterator.reset();
		}
		return randWord;
	}
}
