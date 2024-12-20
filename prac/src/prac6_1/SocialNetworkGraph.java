package prac6_1;

public class SocialNetworkGraph {

	public static void main(String[] args) {
		Graph socialNetwork = new Graph();
		
		// Add users
		socialNetwork.addUser(1);
		socialNetwork.addUser(2);
		socialNetwork.addUser(3);
		socialNetwork.addUser(4);
		socialNetwork.addUser(5);
		
		// Add friends
		socialNetwork.addFriend(1, 2);
		socialNetwork.addFriend(1, 3);
		socialNetwork.addFriend(2, 3);
		socialNetwork.addFriend(3, 4);
		socialNetwork.addFriend(4, 5);
		
		// Display the Graph 
		System.out.println("\nSocial Network Graph:");
		socialNetwork.displayGraph();
		
		// Find Mutual friends
		System.out.println("\nMutual Friends between User1 and User3:");
		System.out.println(socialNetwork.findMutualFriends(1, 3));
		
		// Suggest friends
		System.out.println("\nFriend suggestions for User1:");
		System.out.println(socialNetwork.suggestFriends(1));
		
		// Degree of separation 
		System.out.println("\nDegrees of seperation between User1 and User5:");
		System.out.println(socialNetwork.findDegreeOfSeperation(1, 5));
	}

}
