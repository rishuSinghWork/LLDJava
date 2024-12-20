package prac5;

import java.util.*;

class LeaderboardService {

	private Map<String , Integer> playerScores = new HashMap<>();
	private TreeMap<Integer, List<Player>> scoreBoard = new TreeMap<>(Collections.reverseOrder());
	
	// method to add player with initial score 
	public void addPlayer(Player player, int initialScore) {
		playerScores.put(player.getPlayerId(), initialScore); // ---> first DS 
		scoreBoard.computeIfAbsent(initialScore, k -> new ArrayList<>()).add(player);
		System.out.println("Player added: " + player + " with score " + initialScore);
	}
	// method to update player score
	public void updatePlayerScore(String playerId, int addScore) {
		Integer currScore = playerScores.get(playerId);
		if (currScore == null) {
			System.out.println("No record found!");
			return;
		}
		
		// remove the player from the old score list
		Player player = findPlayerById(playerId, currScore);
		if (player != null) {
			scoreBoard.get(currScore).remove(player);
			if (scoreBoard.get(currScore).isEmpty()) {
				scoreBoard.remove(currScore);
			}
		}
		
		// update the score and re-insert
		int newScore = currScore + addScore;
		playerScores.put(playerId, newScore);
		scoreBoard.computeIfAbsent(newScore, k -> new ArrayList<>()).add(player);
		
		System.out.println("Player score updated: " + player + " to new score " + newScore);
	}
	
		
	
	// method to retrieve the top N players
	public List<Player> getTopNPlayers(int n){
		List<Player> topPlayers = new ArrayList<>();
		for (List<Player> playersWithScore : scoreBoard.values()) {
			for(Player player : playersWithScore) {
				topPlayers.add(player);
				if(topPlayers.size() == n) {
					return topPlayers;
				}
			}
		}
		return topPlayers;
	}
	
	
	
	
	// Helper to find a player by ID in a specific score bucket 
	public Player findPlayerById(String playerId,int currScore) {
		List<Player> players = scoreBoard.get(currScore);
		if (players != null) {
			for (Player player : players) {
				if (player.getPlayerId().equals(playerId)){
					return player;
				}
			}
		}
		return null;
	}
}
