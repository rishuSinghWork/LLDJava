package prac4;

import java.util.HashMap;
import java.util.Map;

/**
 * 	-addBook()
 * 	-borrowCopy()
 * 	-returnCopy()
 * 	-displayAllBooks()
 */
class LibraryService {
	private Map<String, Book> library = new HashMap<>();
	
	// method to add Books to the library
	public void addBook(String isbn, int numberOfCopies) {
		Book book = library.get(isbn);
		if(book == null) {
			book = new Book(isbn);
			library.put(isbn, book);
		}
		
		// Add specified number of copies 
		int nextCopyId = book.getCopies().size() + 1;
		for (int i = 0; i < numberOfCopies; i++) {
			book.addCopy(new Copy(nextCopyId++));
		}
		
		System.out.println("Book added successfully with " + numberOfCopies + " copies.");
	}
	
	// Method to borrow a copy of a book
	public boolean borrowCopy(String isbn) {
		Book book = library.get(isbn);
		if (book == null) {
			System.out.println("Book not found");
			return false;
		}
		
		Copy availableCopy = book.getAvailableCopy();
		if (availableCopy != null) {
			availableCopy.borrow();
			System.out.println("Copy borrowed successfully: " + availableCopy);
			return true;
		} else {
			System.out.println("All copies are currently borrowed.");
			return false;
		}
	}
	
	// Method to return a borrowed copy by ISBN and Copy Id
	
	// Method to display all books and their copies 
}
