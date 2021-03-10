import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BejeweledScoreboard {
	JLabel lblTimer;
    JLabel p1Label;
    JLabel p2Label;
    JLabel gameStatusLabel;

    Player p1;
    Player p2;
	
    public void updateScores() {
        p1Label.setText("Player 1 score: " + p1.getPlayerScore());
        p2Label.setText("Player 2 score: " + p2.getPlayerScore());
    }
    
    public void updateTime(int timeLeft) {
    	SimpleDateFormat dateFormat= new SimpleDateFormat("mm:ss");
        lblTimer.setText("Time Limit: " + dateFormat.format(timeLeft));
    }

    public void rotateTurn(Player current) {
        if (current == p1) {
            p1Label.setBackground(Color.decode("#fc0303"));
            gameStatusLabel.setText("Current player: Player 1");
            p2Label.setBackground(Color.WHITE);
        } else {
            p2Label.setBackground(Color.decode("#fc0303"));
            gameStatusLabel.setText("Current player: Player 2");
            p1Label.setBackground(Color.WHITE);
        }
    }

    public void declareWinner(Player winner) {
        if (winner == p1) {
            gameStatusLabel.setText("Game over! Player 1 wins!");
            p1Label.setBackground(Color.decode("#56CBF9"));
            p2Label.setBackground(Color.WHITE);
        } else {
            gameStatusLabel.setText("Game over! Player 2 wins!");
            p1Label.setBackground(Color.WHITE);
            p2Label.setBackground(Color.decode("#fc0303"));
        }
    }

    public void declareWinner() {
        gameStatusLabel.setText("Game over! Its a draw!");
        p1Label.setBackground(Color.WHITE);
        p2Label.setBackground(Color.WHITE);
    }

    public BejeweledScoreboard(JPanel scorePanel, Player player1, Player player2, int timeLeft) {
        p1 = player1;
        p2 = player2;
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("mm:ss");
        lblTimer = new JLabel("Time Limit: " + dateFormat.format(timeLeft));
        
        gameStatusLabel = new JLabel("Current player: Player 1");
        JLabel random = new JLabel("   ");
        p1Label = new JLabel("Player 1 score: " + p1.getPlayerScore());
        p1Label.setOpaque(true);
        p1Label.setBackground(Color.decode("#56CBF9"));
        p2Label = new JLabel("Player 2 score: " + p2.getPlayerScore());
        p2Label.setOpaque(true);
        
       
        scorePanel.add(lblTimer);
        scorePanel.add(random);
        scorePanel.add(gameStatusLabel);
        scorePanel.add(p1Label);
        scorePanel.add(p2Label);
    }

}
