package model;

public class BabyLink {
	public String nextWord;
	public String currentWord;
	public BabyLink next;
	private String word;
	
//	public BabyLink(String nextWord) {
//		this.nextWord = nextWord;
//		this.count = count++;
//	}
	
//	public void display() {
//		System.out.print(nextWord + " ");
//	}
	
//	public String getNextWord() {
//		return nextWord;
//	}
	
//	public void setNextWord(String str) {
//		this.nextWord = str;
//	}
	
//	public BabyLink getNext() {
//		return next;
//	}
	
//	public void setNext(BabyLink next) {
//		this.next = next;
//	}
	
//	public void displayLink() {
//		System.out.println(nextWord + " ");
//	}
	
	// NEW CODE STARTS HERE
	
	public BabyLink(String word) {
		this.word = word;
	}
	
	public String getiData() {
		return word;
	}
	
	public void setiData(String word) {
		this.word = word;
	}
	
	public BabyLink getNext() {
		return next;
	}
	
	public void setNext(BabyLink next) {
		this.next = next;
	}
	
	public void display() {
		System.out.println(word + " ");
	}
	
	@Override
	public String toString() {
		return "BabyLink [word=" + word + "]";
	}
	
}
