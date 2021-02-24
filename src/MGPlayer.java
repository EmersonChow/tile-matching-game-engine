public class MGPlayer implements  Player{

    private int playerID;
    private MGScore currentScore = new MGScore();
    private boolean turn;

    MGPlayer(int id) {
        playerID = id;
        currentScore.updateScore(0);
        turn = false;
    }

    @Override
    public int getPlayerScore() {
        return currentScore.getScore();
    }

    @Override
    public int getID() {
        return playerID;
    }

    @Override
    public void display() {
        System.out.println("ID: " + getID());
        System.out.println("Score: " + getPlayerScore());
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean gameTurn) {
        turn = gameTurn;
    }

}
