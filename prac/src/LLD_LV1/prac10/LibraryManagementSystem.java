package LLD_LV1.prac10;

public class LibraryManagementSystem {

	public static void main(String[] args) {
		LibraryService library = new LibraryService();
		
		// add books
		library.addBook(101, "The Catcher in the Rye", "J.D. Salinger", 5);
		library.addBook(102, "To Kill a Mockingbird", "Harper Lee", 3);
		
		// register members
		library.registerMember(1, "Alice", "alice@example.com");
		library.registerMember(2, "Bob", "bob@example.com");
		
		// borrow books 
		library.borrowBook(101, 1); 
		library.borrowBook(102, 2);
		
		
        library.viewAvailableBooks();
		
		// return books 
		library.returnBook(1);
		
		// view all available books 
		
        library.viewAvailableBooks();
	}

}
