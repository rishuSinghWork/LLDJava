package prac6;

import java.util.*;

class SocialNetworkService {
	private Map<Integer, Set<Integer>> friendships = new HashMap<>();
	/**
	 * this is implementation of adjacency list which represents the graph.
	 * @param userId1
	 * @param userId2
	 */
	// method to add friend
	public void addFriend(int userId1, int userId2) {
		if (userId1 == userId2) {
			System.out.println("A user cannot be friend with themselves.");
			return;
		}
		
		friendships.computeIfAbsent(userId1, k -> new HashSet<>()).add(userId2);
		friendships.computeIfAbsent(userId2, k -> new HashSet<>()).add(userId1);
		
		System.out.println("Friendship added between User "+userId1+" and "+userId2);
	}
	
	// method to remove friend 
	public void removeFriends(int userId1, int userId2) {
		Set<Integer> friendsOfUser1 = friendships.get(userId1);
		Set<Integer> friendsOfUser2 = friendships.get(userId2);
		
		if (friendsOfUser1 != null) {
			friendsOfUser1.remove(userId2);
			if(friendsOfUser1.isEmpty()) {
				friendships.remove(userId1);
			}
		}
		
		if (friendsOfUser2 != null) {
			friendsOfUser2.remove(userId1);
			if (friendsOfUser2.isEmpty()) {
				friendships.remove(userId2);
			}
		}
		
		System.out.println("Friendship remove between User "+userId1+" and "+userId2);
	}
	// method to get the friends of a user 
	public Set<Integer> getFriends(int userId){
		return friendships.getOrDefault(userId, Collections.emptySet());
	}
	// method to display mutual friends b/w two user
	public Set<Integer> findMutualFriends(int userId1, int userId2){
		Set<Integer> friendsOfUser1 = friendships.get(userId1);
		Set<Integer> friendsOfUser2 = friendships.get(userId2);
		
		if (friendsOfUser1 == null || friendsOfUser2 == null) {
			return Collections.emptySet();
		}
		
		Set<Integer> mutualFriends = new HashSet<>(friendsOfUser1);
		mutualFriends.retainAll(friendsOfUser2);
		return mutualFriends;
	}
	// display all friendships 
	public void displayAllFriendships() {
		for (Map.Entry<Integer, Set<Integer>> entry : friendships.entrySet()) {
			System.out.println("User " + entry.getKey()+ " is friends with: " + entry.getValue());
		}
	}
}
