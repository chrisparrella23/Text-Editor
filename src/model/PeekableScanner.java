package model;

import java.util.Scanner;

public class PeekableScanner {
	private final Scanner scan;
	private String next;
	
	public PeekableScanner(String source) {
		this.scan = new Scanner(source);
		
		if (scan.hasNext()) {
			this.next = scan.next();
		} else {
			this.next = null;
		}
	}
	
	public boolean hasNext() {
		return (this.next != null);
	}
	
	public String next() {
		String current= this.next;
		
		if (scan.hasNext()) {
			this.next = scan.next();
		} else {
			this.next = null;
		}
		return current;
	}
	
	public String peek() {
		return this.next();
	}
}
