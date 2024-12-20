package prac6_1;

import java.util.*;

class Graph {

	private Map<Integer, GraphNode> nodes = new HashMap<>();
	
	// add a user to the graph 
	public void addUser(int userId) {
		if(!nodes.containsKey(userId)) {
			nodes.put(userId, new GraphNode(userId));
			System.out.println("User "+userId+" added.");
		} else {
			System.out.println("User "+userId+" already exists.");
		}
	}
	
	// remove a user from the graph 
	public void removeUser(int userId) {
		GraphNode node = nodes.get(userId);
		if (node != null) {
			for (GraphNode neighbor : node.getNeighbors()) {
				neighbor.removeNeighbors(node);
			}
			nodes.remove(userId);
			System.out.println("User "+userId+" removed.");
		} else {
			System.out.println("User "+userId+" does not exists.");
		}
	}
	
	// add friendship between two users 
	public void addFriend(int userId1, int userId2) {
		GraphNode node1 = nodes.get(userId1);
		GraphNode node2 = nodes.get(userId2);
		
		if (node1 == null || node2 == null) {
			System.out.println("One or both users do not exists.");
			return;
		}
		
		node1.addNeighbors(node2);
		node2.addNeighbors(node1);
		System.out.println("Friendship added between User "+userId1+" and User "+userId2);
	}
	// remove friendship between two users
	public void removeFriend(int userId1, int userId2) {
		GraphNode node1 = nodes.get(userId1);
		GraphNode node2 = nodes.get(userId2);
		
		if (node1 == null || node2 == null) {
			System.out.println("One or both users do not exist.");
			return;
		}
		
		node1.removeNeighbors(node2);
		node2.removeNeighbors(node1);
		System.out.println("Friendship removed between User "+ userId1+" and User "+userId2);
	}
	// get friends of a user 
	public List<Integer> getFriends(int userId){
		GraphNode node = nodes.get(userId);
		if (node == null) {
			System.out.println("User "+userId+" does not exists.");
			return Collections.emptyList();
		}
		return node.getNeighbors().stream().map(GraphNode::getId).toList();
	}
	
	// find mutual friends b/w two users 
	public List<Integer> findMutualFriends(int userId1, int userId2){
		GraphNode node1 = nodes.get(userId1);
		GraphNode node2 = nodes.get(userId2);
		
		if (node1 == null || node2 == null) {
			System.out.println("One or both users do not exist.");
			return Collections.emptyList();
		}
		Set<Integer> friendsOfUser1 = new HashSet<>(getFriends(userId1));
		Set<Integer> friendsOfUser2 = new HashSet<>(getFriends(userId2));
		friendsOfUser1.retainAll(friendsOfUser2);
		return new ArrayList<>(friendsOfUser1);
	}
	
	// suggest friends for a user 
	public List<Integer> suggestFriends(int userId){
		GraphNode node = nodes.get(userId);
		if (node == null) {
			System.out.println("User "+userId+" not found.");
			return Collections.emptyList();
		}
		
		Set<Integer> suggestions = new HashSet<>();
		Set<Integer> directFriends = new HashSet<>(getFriends(userId));
		
		for (GraphNode friend : node.getNeighbors()) {
			for (GraphNode friendsfriend : friend.getNeighbors()) {
				int suggestionId = friendsfriend.getId();
				if (suggestionId != userId && !directFriends.contains(suggestionId)) {
					suggestions.add(suggestionId);
				}
			}
		}
		return new ArrayList<>(suggestions);
	}
	
	// find degree of separation b/w the users (shortest path)
	public int findDegreeOfSeperation(int userId1, int userId2) {
		GraphNode start = nodes.get(userId1);
		GraphNode end = nodes.get(userId2);
		
		if (start == null || end == null) {
			System.out.println("Either or both of the users not found.");
			return -1;
		}
		
		Queue<GraphNode> queue = new LinkedList<>();
		Set<GraphNode> visited = new HashSet<>();
		Map<GraphNode, Integer> distances = new HashMap<>();
		
		queue.add(start);
		visited.add(start);
		distances.put(start, 0);
		
		while(!queue.isEmpty()) {
			GraphNode current = queue.poll();
			
			// termination condition 
			if (current == end) {
				return distances.get(current);
			}
			
			for (GraphNode neighbor : current.getNeighbors()) {
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					distances.put(neighbor, distances.get(current)+1);
					queue.add(neighbor);
				}
			}
		}
		return -1;
	}
	
	// display the graph all users and their friends
	public void displayGraph() {
		for (GraphNode node : nodes.values()) {
			System.out.println(node);
		}
	}
}
