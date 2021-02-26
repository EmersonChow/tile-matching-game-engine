public class MGPlayer implements  Player{

    private String name;
    private int playerID;
    private int score;
    private boolean turn;

    MGPlayer(int id, String name) {
        this.name = name;
        playerID = id;
        score = 0;
        turn = false;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int points) {
        score += points;
    }

    @Override
    public void displayScore() {
        System.out.println(score);
    }

    @Override
    public int getID() {
        return playerID;
    }

    @Override
    public void displayName() {
        System.out.println("ID: " + getID());
        System.out.println("Score: " + score);
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean gameTurn) {
        turn = gameTurn;
    }

}
