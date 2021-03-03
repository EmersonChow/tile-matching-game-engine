public class Player {

	int playerID;
	int score = 0;
	boolean turn;
	
	public int getPlayerScore() {
		return score;
	}
	
	public boolean isTurn() {
		return turn;
	}
	
	public void addPoint() {
		score += 1;
	}
}
