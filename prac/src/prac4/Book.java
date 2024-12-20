package prac4;

import java.util.ArrayList;
import java.util.List;

/**
 * - isbn String
 * - copies List<Copy>
 * 
 * - getIsbn()
 * - getCopies()
 * - addCopy()
 * - getAvailableCopies()
 */
class Book {
	private String isbn;
	private List<Copy> copies;
	
	// constructor 
	public Book(String isbn) {
		this.isbn = isbn;
		this.copies = new ArrayList<>();
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public List<Copy> getCopies(){
		return copies;
	}
	
	public void addCopy(Copy copy) {
		copies.add(copy);
	}
	
	public Copy getAvailableCopy(){
		for(Copy copy : copies) {
			if (!copy.isBorrowed()) {
				return copy;
			}
		}
		return null;	
	}
	
	@Override
	public String toString() {
		return "Book{" +
				"isbn='" + isbn + '\'' +
				", copies=" + copies +
				'}';
	}
}
