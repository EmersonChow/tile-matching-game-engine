//MGSCore - MatchingGameScore
public class MGScore implements Score{
    public int score;

    public MGScore(){
        score = 0;
    }

    @Override
    public void updateScore(int points) {
        score += points;
    }

    @Override
    public int getScore() {
        return score;
    }


    @Override
    public void display() {
        System.out.println(score);
    }
}
