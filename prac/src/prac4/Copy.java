package prac4;

/**
 *  -copyId (integer)
 *  -isBorrowed flag (boolean)
 *  
 *  -getCopyId()
 *  -isBorrowed()
 *  -borrow()
 *  -returnCopy()
 */
class Copy {
	private int copyId;
	private boolean isBorrowed;
	
	// Constructor 
	public  Copy(int copyId) {
		this.copyId = copyId;
		this.isBorrowed = false;
	}
	
	public int getCopyId() {
		return copyId;
	}
	
	public boolean isBorrowed() {
		return isBorrowed;
	}
	
	public void borrow() {
		isBorrowed = true;
	}
	
	public void returnCopy() {
		isBorrowed = false;
	}
	
	@Override
	public String toString() {
		return "Copy{" + 
				"copyId=" + copyId +
				", isBorrowed=" + isBorrowed + 
				'}';
	}
}


