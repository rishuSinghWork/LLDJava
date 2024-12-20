package prac6;

class User {
	private int userId;
	private String name;
	
	public User(int userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	public int getUser() {
		return userId;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId + 
				", name='" + name + '\'' +
				'}';
	}
}

