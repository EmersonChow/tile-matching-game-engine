package Bejeweled;

import TMGE.*;

import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BejeweledScoreboard extends Scoreboard {
	JLabel lblTimer;
    public void updateTime(int timeLeft) {
    	SimpleDateFormat dateFormat= new SimpleDateFormat("mm:ss");
        lblTimer.setText("Time Limit: " + dateFormat.format(timeLeft));
    }

    public BejeweledScoreboard(JPanel scorePanel, Player player1, Player player2, int timeLeft) {
        p1 = player1;
        p2 = player2;
        
        SimpleDateFormat dateFormat= new SimpleDateFormat("mm:ss");
        lblTimer = new JLabel("Time Limit: " + dateFormat.format(timeLeft));
        
        gameStatusLabel = new JLabel("Current player: " + p1.getName());
        JLabel random = new JLabel("   ");
        p1Label = new JLabel(p1.getName() + " score: " + p1.getPlayerScore());
        p1Label.setOpaque(true);
        p1Label.setBackground(Color.decode("#56CBF9"));
        p2Label = new JLabel(p2.getName() + " score: " + p2.getPlayerScore());
        p2Label.setOpaque(true);
        
       
        scorePanel.add(lblTimer);
        scorePanel.add(random);
        scorePanel.add(gameStatusLabel);
        scorePanel.add(p1Label);
        scorePanel.add(p2Label);
    }
}
