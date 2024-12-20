package prac5;

class Player {
	private String playerId;
	private String name;
	
	public Player(String playerId, String name) {
		this.playerId = playerId;
		this.name = name;
	}
	
	public String getPlayerId() {
		return playerId;
	}
	
	// return the entire obj in String 
	@Override 
	public String toString() {
		return "Player{" + 
				"playerId='" + playerId + '\''+
				", name='" + name + '\''+
				'}';
	}
}

