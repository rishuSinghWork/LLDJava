package LLD_LV1.prac10;

class Member {

	private int memberId;
	private String name;
	private String contact;
	
	public Member(int memberId, String name, String contact) {
		this.memberId = memberId;
		this.name = name;
		this.contact = contact;
	}
	
	public int getMemberId() {
		return memberId;
	}
	
	@Override
	public String toString() {
		return "Member{" + 
				"memberId=" + memberId +
				", name='" + name + '\'' +
				", contact='" + contact + '\'' +
				'}';
	}
}
