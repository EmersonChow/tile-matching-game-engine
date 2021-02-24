public class MemoryMatch {
    private MGPlayer currentPlayer;
    private MGPlayer player1;
    private MGPlayer player2;
    private int time;
    private int level;

    public MemoryMatch(){
        currentPlayer = new MGPlayer(100);
        player1  = new MGPlayer(100);
        player2  = new MGPlayer(101);
        time = 0;
        level = 1;
    }

    public void generateBoard(){
    }

    public void switchPlayers(MGPlayer playerTurn){
        currentPlayer = playerTurn;
    }

//    public int getLevel(){
//        return time;
//    }
//
//    public int getTime(){
//        return time;
//    }
//    public void displayTime(){
//        System.out.println(time);
//    }
}
