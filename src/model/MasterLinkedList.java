package model;

public class MasterLinkedList {
	private MasterLink firstMasterLink;
	private MasterLink current;
	private MasterLink previous;

//	public MasterLinkedList() {
//		firstMasterLink = null;
//	}
//
//	public static MasterLinkedList parser(String s) {
//		MasterLinkedList masterLinkedList = new MasterLinkedList();
//
//		PeekableScanner scan1 = new PeekableScanner(s);
//
//		while (scan1.hasNext()) {
//			String word = scan1.next().toLowerCase();
//			masterLinkedList.addToList(word, scan1.peek());
//		}
//
//		return masterLinkedList;
//	}
//
//	public MasterLink getFirst() {
//		return firstMasterLink;
//	}
//
//	public void setFirst(MasterLink link) {
//		this.firstMasterLink = link;
//	}
//
//	public boolean isEmpty() {
//		return firstMasterLink == null;
//	}
//
//	public void display() {
//		MasterLink current = firstMasterLink;
//		while (current != null) {
//			current.displayLink();
//			current = current.next;
//		}
//		System.out.println();
//	}
//
//	public MasterLink find(String key) {
//		MasterLink current = firstMasterLink;
//		while (!current.getKeyword().equalsIgnoreCase(key)) {
//			if (current.getNext() == null) {
//				return null;
//			} else {
//				current = current.getNext();
//			}
//		}
//		return current;
//	}
//
//	public void reset() {
//		current = getFirst();
//		previous = null;
//	}
//
//	public boolean uniqueCheck(String key) {
//		if (isEmpty()) {
//			return false;
//		} else {
//			MasterLink current = firstMasterLink;
//			while (current != null) {
//				if (current.getKeyword().equalsIgnoreCase(key)) {
//					return true;
//				} else {
//					current = current.next;
//				}
//			}
//		}
//		return false;
//	}
//
//	public void displayList() {
//		System.out.println("List (first -> last)");
//		MasterLink current = firstMasterLink;
//		while (current != null) {
//			current.displayLink();
//			current = current.getNext();
//		}
//		System.out.println();
//	}
//
//	public void insertFirst(String s) {
//		MasterLink newLink = new MasterLink(s);
//		newLink.setNext(firstMasterLink);
//		firstMasterLink = newLink;
//	}
//
//	public MasterLink deleteFirst() {
//		MasterLink temp = firstMasterLink;
//		firstMasterLink = firstMasterLink.getNext();
//		return temp;
//	}
//
//	public void insertAfter(String s) {
//		MasterLink newLink = this.containsLink(s);
//
//		if (isEmpty()) {
//			setFirst(newLink);
//			current = newLink;
//		} else {
//			if (newLink == null) {
//
//			}
//			newLink.next = current.next;
//			current.next = newLink;
//			nextLink();
//		}
//	}
//
//	public void addToList(String word, String secondWord) {
//		MasterLink newLink = this.containsLink(word);
//		if (newLink == null) {
//			newLink = new MasterLink(word);
//
//			if (!this.isEmpty()) {
//				MasterLink temp = getLast();
//
//				newLink.setPrev(temp);
//				this.add(newLink);
//				temp.setNext(newLink);
//			} else {
//				this.add(newLink);
//			}
//		}
//	}
//
//	// goes through to check if word is there
//	public MasterLink containsLink(String s) {
//		for (MasterLink masterLink : this) {
//			if (masterLink.getKeyword().equals(s)) {
//				return masterLink;
//			}
//		}
//		return null;
//	}
//
//	public void nextLink() {
//		previous = current;
//		current = current.next;
//	}
//
//	public int sizeRecur(MasterLink current) {
//		if (current == null) {
//			return 0;
//		} else {
//			return 1 + sizeRecur(current.next);
//		}
//	}
//
//	public String makeParagraph(String word, int num) {
//		int random;
//		MasterLink current = this.containsLink(word);
//		StringBuilder paragraph = new StringBuilder(word + " ");
//
//		for (int i = 0; i < num; i++) {
//			if (current != null) {
//				random = (int) (Math.random() * current.getBabyLinkedList().size());
//				paragraph.append(current.getBabyLinkedList().get(random).getNextWord()).append(" ");
//				current = get(i);
//			}
//		}
//		return paragraph.toString();
//	}
	
	public MasterLinkedList() {
		firstMasterLink = null;
	}
	
	public boolean isEmpty() {
		return firstMasterLink == null;
	}
	
	public void insertFirst(String keyword, String nextWord) {
		MasterLink newLink = new MasterLink(keyword, nextWord);
		if (firstMasterLink == null) {
			firstMasterLink = newLink;
		} else {
			newLink.setNext(firstMasterLink);
			firstMasterLink = newLink;
		}
	}
	
	public void insertLast(String keyword, String nextWord) {
		MasterLink newLink = new MasterLink(keyword, nextWord);
		if (this.firstMasterLink == null) {
			firstMasterLink = newLink;
			return;
		}
		MasterLink current = this.firstMasterLink;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		current.setNext(newLink);
	}
	
	public MasterLink deleteFirst() {
		MasterLink temp = firstMasterLink;
		firstMasterLink = firstMasterLink.getNext();
		return temp;
	}
	
	public MasterLink deleteLast() {
		MasterLink current = this.firstMasterLink;
		while(current.getNext().getNext() != null) {
			current = current.getNext();
		}
		MasterLink temp = current.getNext();
		current.setNext(null);
		return temp;
	}
	
	public MasterLink delete(String key) {
		MasterLink current = firstMasterLink;
		MasterLink previous = firstMasterLink;
		while(current.getiData() != key) {
			if(current.getNext() == null) {
				return null;
			} else {
				previous = current;
				current = current.getNext();
			}
		}
		
		if(current == firstMasterLink) {
			firstMasterLink = firstMasterLink.getNext();
		} else {
			previous.setNext(current.getNext());
		}
		return current;
	}
	
	public boolean find(String key) {
		MasterLink current = firstMasterLink;
		while(!(current.getiData().equalsIgnoreCase(key))) {
			if(current.getNext() == null) {
				return false;
			} else {
				current = current.getNext();
			}
		}
		return true;
	}
	
	public MasterLink getLink(String key) {
		MasterLink current = firstMasterLink;
		while(!(current.getiData().equalsIgnoreCase(key))) {
			if(current.getNext() == null) {
				return null;
			} else {
				current = current.getNext();
			}
		}
		return current;
	}
	
	public void displayBabyLists() {
		System.out.println("Baby Linked Lists: ");
		MasterLink current = this.firstMasterLink; 
		while(current != null) {
			System.out.println("\nBaby List for '" + current.getiData() + "':");
			current.getBabyList().displayList();
			current = current.getNext();
		}
	} 
	
	public void display() {
		MasterLink current = firstMasterLink;
		System.out.println("\nMaster List: ");
		while(current != null) {
			current.display();
			current = current.getNext();
		}
	}	
}
