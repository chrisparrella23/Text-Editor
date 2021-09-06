package model;

import java.util.LinkedList;

public class MasterLink {
	public String keyword;
	public BabyLinkedList babyList;
	public MasterLink next;
//	public MasterLink prev;
	
//	private final String word;
//	private final LinkedList<BabyLink> babyLinkedList;
	
//	public MasterLink(String keyword) {
//		this.babyLinkedList = new LinkedList();
//		this.keyword = keyword;
//	}
//	
//	public String getKeyword() {
//		return keyword;
//	}
//	
//	public void setKeyword(String keyword) {
//		this.keyword = keyword;
//	}
//	
//	public MasterLink getNext() {
//		return next;
//	}
//	
//	public void setNext(MasterLink next) {
//		this.next = next;
//	}
//	
//	public void setBabyList(BabyLinkedList babyList) {
//		this.babyList = babyList;
//	}
//	
//	public BabyLinkedList getBabyList() {
//		return babyList;
//	}
//	
//	public void displayLink() {
//		System.out.print(keyword + " ");
//	}
//	
//	public void addBabyLink(BabyLink link) {
//		this.babyLinkedList.add(link);
//	}
//
//	public LinkedList<BabyLink> getBabyLinkedList() {
//		return this.babyLinkedList;
//	}
//	
//	public MasterLink getPrev() {
//		return this.prev;
//	}
//	
//	public void setPrev(MasterLink prevLink) {
//		this.prev = prevLink;
//	}
	
	// NEW CODE STARTS HERE
	
	public MasterLink(String keyword, String nextWord) {
		this.keyword = keyword;
		BabyLink babyLink = new BabyLink(nextWord);
		BabyLinkedList babyList = new BabyLinkedList(babyLink);
		this.babyList = babyList;
	}
	
	public boolean hasBabyList() {
		return this.babyList != null;
	}
	
	public String getiData() {
		return keyword;
	}
	
	public BabyLinkedList getBabyList() {
		return babyList;
	}
	
	public void setiData(String keyword) {
		this.keyword = keyword;
	}
	
	public MasterLink getNext() {
		return next;
	}
	
	public void setNext(MasterLink next) {
		this.next = next;
	}
	
	public void display() {
		System.out.println(keyword + " ");
	}
	
	@Override
	public String toString() {
		return "MasterLink [keyword=" + keyword + "]";
	}
}
