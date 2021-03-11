package Memory;

import TMGE.Player;
import TMGE.Scoreboard;

import javax.swing.*;
import java.awt.*;

public class MemoryScoreboard extends Scoreboard {
    public MemoryScoreboard(JPanel scorePanel, Player player1, Player player2) {
        p1 = player1;
        p2 = player2;

        gameStatusLabel = new JLabel("Current player: TMGE.Player 1");
        p1Label = new JLabel("TMGE.Player 1 score: " + p1.getPlayerScore());
        p1Label.setOpaque(true);
        p1Label.setBackground(Color.decode("#56CBF9"));
        p2Label = new JLabel("TMGE.Player 2 score: " + p2.getPlayerScore());
        p2Label.setOpaque(true);

        scorePanel.add(gameStatusLabel);
        scorePanel.add(p1Label);
        scorePanel.add(p2Label);
    }
}
