package LLD_LV1.prac10;

class Book {

	private int bookId;
	private String title;
	private String author;
	private int totalCopies;
	private int availableCopies;
	
	public Book(int bookId, String title, String author, int totalCopies) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.totalCopies = totalCopies;
		this.availableCopies = totalCopies;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	public boolean isAvailable() {
		return availableCopies > 0;
	}
	
	public void borrowCopy() {
		if (availableCopies > 0)
			availableCopies--;
	}
	
	public void returnCopy() {
		if (availableCopies < totalCopies)
			availableCopies++;
	}
	
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", title='" + title +'\''+
				", author='" + author + '\''+
				", totalCopies=" + totalCopies +
				", availableCopies=" + availableCopies +
				'}';
	}
}
