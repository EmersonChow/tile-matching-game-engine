import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class tmge {
    JFrame mainFrame;
    JPanel scorePanel;
    JLabel p1Label;
    JLabel p2Label;
    JPanel tilePanel;
    JButton tiles[][];
    int TILE_HEIGHT;
    int TILE_WIDTH;
    
    Player currentPlayer;
    Player p1;
    Player p2;

    Component selectedTileButton;
    Tile selectedTile;
    
    Tile TMGEboard[][];
    
    ArrayList<Tile> matched;

    
    private class ListenerForClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < TILE_WIDTH; i++) {
                for(int j = 0; j < TILE_HEIGHT; j++) {
                    if (e.getSource() == tiles[i][j]) {
                        if (e.getSource() instanceof Component) {
                        	checkMatch(i,j,e);
                        }
                    }
                }
            }
        }
    }
    
    public void checkMatch(int x, int y, ActionEvent e) {
    	// Reveals first tile
    	if(selectedTile == null) {
    		selectedTile = TMGEboard[x][y];
    		selectedTileButton = ((Component) e.getSource());
    		((Component) e.getSource()).setBackground(TMGEboard[x][y].getColor());
    		TMGEboard[x][y].reveal();
    	}
    	
    	// Reveals second tile & checks for match
    	else {
    		if(selectedTile != TMGEboard[x][y] && !matched.contains(TMGEboard[x][y])) {
    			
	    		// Successful Match
	    		if(selectedTile.getColor() == TMGEboard[x][y].getColor()) {
	    			((Component) e.getSource()).setBackground(TMGEboard[x][y].getColor());
	        		TMGEboard[x][y].reveal();
	        		
	        		// add to matched list
	        		matched.add(selectedTile);
	        		matched.add(TMGEboard[x][y]);
	        		
	    			currentPlayer.addPoint();
	    			updateLabel();
	
	    		}
	    		
	    		// Hide both tiles again
	    		else {
	    			selectedTileButton.setBackground(Color.GRAY);
	    			selectedTile.hide();
	    			((Component) e.getSource()).setBackground(Color.GRAY);
	    			TMGEboard[x][y].hide();
	    			switchPlayers();
	    		}
	    		
	    		// Reset selected tiles
	    		selectedTile = null;
	    		selectedTileButton = null;
    		}
    	}
    }
    
    public void updateScores() {
    	p1Label.setText("Player 1 score: " + p1.getPlayerScore());
    	p2Label.setText("Player 1 score: " + p1.getPlayerScore());
    }
    
    public void switchPlayers() {
    	if(currentPlayer == p1) {
    		currentPlayer = p2;
    	}
    	else {
    		currentPlayer = p1;
    	}
    }
    
    public void updateLabel() {
		p1Label.setText("Player 1 score: " + p1.getPlayerScore());
		p2Label.setText("Player 2 score: " + p2.getPlayerScore());
    }

    public tmge(int x, int y, Tile[][] board) {
    	p1 = new Player();
    	p2 = new Player();
    	matched = new ArrayList<Tile>();
    	
    	currentPlayer = p1;
    	
        TILE_HEIGHT = x;
        TILE_WIDTH = y;
        TMGEboard = board;
        
        mainFrame = new JFrame("TMGE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);

        scorePanel = new JPanel();
        p1Label = new JLabel("Player 1 score: " + p1.getPlayerScore());
        p2Label = new JLabel("Player 2 score: " + p2.getPlayerScore());
        
        scorePanel.add(p1Label);
        scorePanel.add(p2Label);
        
        tilePanel = new JPanel();
        tiles = new JButton[x][y];
        for (int i = 0 ; i < TILE_HEIGHT ; i++) {
            for (int j = 0; j < TILE_WIDTH; j++) {
                tiles[i][j] = new JButton();
                tiles[i][j].addActionListener(new ListenerForClick());
                tiles[i][j].setBorderPainted(false);
                tiles[i][j].setContentAreaFilled(true);
                tiles[i][j].setOpaque(true);
                tiles[i][j].setBackground(Color.GRAY);
                tilePanel.add(tiles[i][j]);
            }
        }

        tilePanel.setLayout(new GridLayout(y, x, 5,5));

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(scorePanel, BorderLayout.NORTH);
        mainFrame.add(tilePanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
