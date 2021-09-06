package model;

public class BabyListIterator {
	private BabyLink current;
	private BabyLink previous;
	private BabyLinkedList ourList;
	
	public BabyListIterator(BabyLinkedList list) {
		ourList = list;
		reset();
	}
	
	public void reset() {
		current = ourList.getFirst();
		previous = null;
	}
	
	public boolean atEnd() {
		return current.getNext() == null;
	}
	
	public void nextLink() {
		previous = current;
		current = current.getNext();
	}
	
	public BabyLink getCurrent() {
		return current;
	}
	
	public void insertBefore(String dData) {
		BabyLink newLink = new BabyLink(dData);
		if(previous == null) {
			newLink.setNext(ourList.getFirst());
			ourList.insertFirst(dData);
			reset();
		} else {
			newLink.setNext(previous.getNext());
			previous.setNext(newLink);
			current = newLink;
		}
	}
	
	public String deleteCurrent() {
		String value = current.getiData();
		if(previous == null) {
			ourList.insertFirst(value);
			reset();
		} else {
			previous.setNext(current.getNext());
			if(atEnd()) {
				reset();
			} else {
				current = current.getNext();
			}
		}
		return value;
	}
	
	public void insertAfter(String dData) {
		BabyLink newLink = new BabyLink(dData);
		if(ourList.isEmpty()) {
			ourList.insertFirst(dData);
			current = newLink;
		} else {
			newLink.setNext(current.getNext());
			current.setNext(newLink);
			nextLink();
		}
	}
}
