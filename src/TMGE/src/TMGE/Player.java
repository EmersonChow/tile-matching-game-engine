package TMGE;

public class Player {

    int playerID;
    String name;
    int score = 0;
    boolean turn;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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