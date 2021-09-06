package model;

public class BabyLinkedList {
	private BabyLink firstBabyLink;
//	private BabyLink next;
//	private BabyLink currentLink;
//	private BabyLink previous;
//	private String currentWord;
//	private String nextWord;
//	private int count;
	
//	public BabyLinkedList() {
//		firstBabyLink = null;
//		count = 0;
//	}
//	
//	public BabyLink getFirst() {
//		return firstBabyLink;
//	}
//	
//	public void setFirst(BabyLink link) {
//		this.firstBabyLink = link;
//	}
//	
//	public void displayList() {
//		System.out.println("List: (first -> last)");
//		BabyLink current = firstBabyLink;
//		while (current != null) {
//			current.displayLink();
//			current = current.getNext();
//		}
//	}
//	
//	public BabyLink find(String key) {
//		BabyLink current = firstBabyLink;
//		while (current.getNextWord() != key) {
//			if (current.getNext() == null) {
//				return null;
//			} else {
//				current = current.getNext();
//			}
//		}
//		return current;
//	}
//	
//	public BabyLink findByNum(int num) {
//		BabyLink current = firstBabyLink;
//		while (current.getNumber() != num) {
//			if (current.getNext() == null) {
//				return null;
//			} else {
//				current = current.next;
//			}
//		}
//		return current;
//	}
//	
//	public boolean isEmpty() {
//		return firstBabyLink == null;
//	}
//	
//	public void insertFirst(String s) {
//		BabyLink newLink = new BabyLink(s);
//		newLink.setNext(firstBabyLink);
//		firstBabyLink = newLink;
//		count++;
//	}
//	
//	public void insertAfter(String s) {
//		BabyLink newLink = new BabyLink(s);
//		if (isEmpty()) {
//			setFirst(newLink);
//			currentLink = newLink;
//		} else {
//			newLink.next = currentLink.next;
//			currentLink.next = newLink;
//			nextLink();
//			count++;
//		}
//	}
//	
//	public void nextLink() {
//		previous = currentLink;
//		currentLink = currentLink.next;
//	}
//	
//	public int sizeRecur(BabyLink current) {
//		if (current == null) {
//			return 0;
//		} else {
//			return 1 + sizeRecur(current.next);
//		}
//	}
//	
//	public int getCount() {
//		return count;
//	}
//	
//	public BabyLink getRandomLink(BabyLinkedList theList) {
//		int size = theList.getCount();
//		int random= (int) (Math.random() * size);
//		BabyLink babyLink = theList.getFirst();
//		
//		for (int i = 0; i < random; i++) {
//			babyLink = babyLink.getNext();
//		}
//		return babyLink;
//	}
	
	public BabyLinkedList(BabyLink babyLink) {
		this.firstBabyLink = babyLink;
	}
	
	public boolean isEmpty() {
		return firstBabyLink == null;
	}
	
	public void insertFirst(String keyword) {
		BabyLink newLink = new BabyLink(keyword);
		if (firstBabyLink == null) {
			firstBabyLink = newLink;
		} else {
			newLink.setNext(firstBabyLink);
			firstBabyLink = newLink;
		}
	}
	
	public void insertLast(String keyword) {
		BabyLink newLink = new BabyLink(keyword);
		BabyLink current = this.firstBabyLink;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newLink);
	}
	
	public BabyLink deleteFirst() {
		BabyLink temp = firstBabyLink;
		firstBabyLink = firstBabyLink.getNext();
		return temp;
	}
	
	public BabyLink deleteLast() {
		BabyLink current = this.firstBabyLink;
		while (current.getNext().getNext() != null) {
			current = current.getNext();
		}
		BabyLink temp = current.getNext();
		current.setNext(null);
		return temp;
	}
	
	public BabyLink delete(String key) {
		BabyLink current = firstBabyLink;
		BabyLink previous = firstBabyLink;
		while (current.getiData() != key) {
			if (current.getNext() == null) {
				return null;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
		if (current == firstBabyLink) {
			firstBabyLink = firstBabyLink.getNext();
		} else {
			previous.setNext(current.getNext());
		}
		return current;
	}
	
	public boolean find(String key) {
		BabyLink current = firstBabyLink;
		while (!(current.getiData().equalsIgnoreCase(key))) {
			if (current.getNext() == null) {
				return false;
			} else {
				current = current.getNext();
			}
		}
		return true;
	}
	
	public BabyLink getLink(String key) {
		BabyLink current = firstBabyLink;
		while (!(current.getiData().equalsIgnoreCase(key))) {
			if (current.getNext() == null) {
				return null;
			} else {
				current = current.getNext();
			}
		}
		return current;
	}
	
	public void displayList() {
		BabyLink current = firstBabyLink;
		while (current != null) {
			current.display();
			current = current.getNext();
		}
	}
	
	public BabyLink getFirst() {
		return firstBabyLink;
	}
}
