package LLD_LV1.prac10;

import java.util.*;

class LibraryService {
	// Data structures to hold data 
	private Map<Integer, Book> books = new HashMap<>();
	private Map<Integer, Member> members = new HashMap<>();
	private List<BorrowRecord> borrowRecords = new ArrayList<>();
	private int nextRecordId = 1;
	
	
	// add book 
	public void addBook(int bookId, String title, String author, int totalCopies) {
		books.put(bookId, new Book(bookId, title, author, totalCopies));
		System.out.println("Book added: " + title);
	}
	
	
	// register member 
	public void registerMember(int memberId, String name, String contact) {
		members.put(memberId, new Member(memberId, name, contact));
		System.out.println("New Member added: " + memberId);
	}
	
	// borrow book 
	public void borrowBook(int bookId, int memberId) {
		Member member = members.get(memberId);
		Book book = books.get(bookId);
		
		if (member == null) {
			System.out.println("Member not found");
			return;
		}
		if (book == null) {
			System.out.println("Book not found");
			return;
		}
		if (!book.isAvailable()) {
			System.out.println("No copies available for: "+ book.getBookId());
			return;
		}
		book.borrowCopy();
		BorrowRecord record = new BorrowRecord(nextRecordId++, book, member);
		borrowRecords.add(record);
		System.out.println("Book borrowed: " + book.getBookId() + " by " + member.getMemberId());
	}
	
	// return book 
	public void returnBook(int recordId) {
		for (BorrowRecord record : borrowRecords) {
			if (record.getRecordId() == recordId) {
				record.returnBook();
				record.getBook().returnCopy();
				System.out.println("Book returned: "+ record.getBook().getBookId());
				System.out.println("Fine: Rs." + record.calculateFine());
				return;
			}
		}
	}
	// view all available books 
	public void viewAvailableBooks() {
		System.out.println("Avaialble Books:");
		books.values().forEach(System.out::println);
	}
}
