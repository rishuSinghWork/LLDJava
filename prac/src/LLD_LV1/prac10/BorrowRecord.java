package LLD_LV1.prac10;

import java.time.LocalDate;

class BorrowRecord {

	private int recordId;
	private Member member;
	private Book book;
	private LocalDate borrowDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	
	public BorrowRecord(int recordId, Book book, Member member) {
		this.recordId = recordId;
		this.book = book;
		this.member = member;
		this.borrowDate = LocalDate.now();
		this.dueDate = borrowDate.plusWeeks(2);
	}
	
	public void returnBook() {
		this.returnDate = LocalDate.now();
	}
	
	public boolean isOverdue() {
		return returnDate == null && LocalDate.now().isAfter(dueDate);
	}
	
	public long calculateFine() {
		if (returnDate == null || !returnDate.isAfter(dueDate))
			return 0;
		return java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate)*2;
	}
	
	public int getRecordId() {
		return recordId;
	}
	
	public Book getBook() {
		return book;
	}
	@Override
	public String toString() {
		return "BorrowRecord{" +
				"recordId="+recordId+
				", member=" + member +
				", book=" + book +
				", borrowDate=" + borrowDate +
				", dueDate=" + dueDate + 
				", returnDate=" + returnDate + 
				'}';
	}
}
