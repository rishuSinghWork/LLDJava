package prac6;

public class SocialNetworkSystem {

	public static void main(String[] args) {
		
		SocialNetworkService scn = new SocialNetworkService();
		
		// Add friendships
		scn.addFriend(1, 2);
		scn.addFriend(1, 3);
		scn.addFriend(2, 3);
		scn.addFriend(3, 4);
		// Display friendships 
		System.out.println("\nAll Friendships:");
		scn.displayAllFriendships();
		// Find mutual 
		System.out.println("\nMutual friends b/w user1 and user2 :");
		System.out.println(scn.findMutualFriends(1, 2));
		// remove friendships
		scn.removeFriends(1, 3);
		System.out.println("\nAll frindships after removing friendship b/w user1 and user3:");
		scn.displayAllFriendships();
		// Get friends of a user 
		System.out.println("\nFriends of user2:");
		System.out.println(scn.getFriends(2));
	}

}
