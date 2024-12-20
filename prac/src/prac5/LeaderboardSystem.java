package prac5;

import java.util.List;

public class LeaderboardSystem {

	public static void main(String[] args) {
		
		LeaderboardService leaderboardService = new LeaderboardService();
		// Adding players 
		Player player1 = new Player("1", "Alice");
		Player player2 = new Player("2", "Bob");
		Player player3 = new Player("3", "Charlie");
		
		leaderboardService.addPlayer(player1, 100);
		leaderboardService.addPlayer(player2, 120);
		leaderboardService.addPlayer(player3, 130);
		
		// update the score 
		leaderboardService.updatePlayerScore("1", 30);
		leaderboardService.updatePlayerScore("2", -30);
		leaderboardService.updatePlayerScore("3",10);
		
		// Retrieve top 2 players 
		System.out.println("Top 2 player:");
		List<Player> topPlayers = leaderboardService.getTopNPlayers(2);
		for (Player player : topPlayers) {
			System.out.println(player);
		}
	}

}
