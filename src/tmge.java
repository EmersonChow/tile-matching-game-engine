import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class tmge {
    JFrame mainFrame;
    JPanel scorePanel;
    JLabel p1Label;
    JLabel p2Label;
    JLabel gameStatusLabel;
    JPanel tilePanel;
    JButton tiles[][];
    int TILE_HEIGHT;
    int TILE_WIDTH;
    
    Player currentPlayer;
    Player p1;
    Player p2;

    Component firstTileButton;
    Component secondTileButton;
    
    Tile firstTile;
    Tile secondTile;
    
    Tile TMGEboard[][];
    
    ArrayList<Tile> matched;    
    
    
    private class ListenerForClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < TILE_WIDTH; i++) {
                for(int j = 0; j < TILE_HEIGHT; j++) {
                    if (e.getSource() == tiles[i][j]) {
                        if (e.getSource() instanceof Component) {
                        	try {
								checkMatch(i,j,e);
								int delay = 750;
					        	Timer timer = new Timer( delay, new ActionListener(){
					        	  @Override
					        	  public void actionPerformed( ActionEvent e ){
					        		  checkWin();
					        	  }
					        	});
					        	timer.start();
								
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
                        }
                    }
                }
            }
        }
        
    }
    
    public void checkMatch(int x, int y, ActionEvent e) throws InterruptedException {
    	// Reveals first tile
    	
    	if(firstTile == null && !matched.contains(TMGEboard[x][y])) {
    		firstTile = TMGEboard[x][y];
    		firstTileButton = ((Component) e.getSource());
    		firstTileButton.setBackground(TMGEboard[x][y].getColor());
    		firstTile.reveal();
    	}
    	
    	// Reveals second tile & checks for match
    	else {
    		secondTile = TMGEboard[x][y];
        	secondTileButton = ((Component) e.getSource());
        	secondTileButton.setBackground(secondTile.getColor());
        	secondTile.reveal();
        	int delay = 750;
        	Timer timer = new Timer( delay, new ActionListener(){
        	  @Override
        	  public void actionPerformed( ActionEvent e ){

		    		if(firstTile != secondTile && !matched.contains(secondTile)) {
		    			
			    		// Successful Match
			    		if(firstTile.getColor() == secondTile.getColor()) {
			    			//secondTileButton.setBackground(secondTile.getColor());
			        		//secondTile.reveal();
			        		
			        		// add to matched list
			        		matched.add(firstTile);
			        		matched.add(secondTile);
			        		
			    			currentPlayer.addPoint();
			    			updateLabel();
			    		}
			    		
			    		// Hide both tiles again
			    		else {
			    			// Sleep functionality still not changing accurately
			    			// secondTileButton.setBackground(secondTile.getColor());
			    			
			    			firstTileButton.setBackground(Color.GRAY);
			    			firstTile.hide();
			    			
			    			secondTileButton.setBackground(Color.GRAY);
			    			secondTile.hide();
		
			    			switchPlayers();
			    			//Thread.sleep(2000);
			    		}
			    		
			    		// Reset selected tiles
			    		firstTile = null;
			    		firstTileButton = null;
			    		secondTile = null;
			    		secondTileButton = null;
		    		}
		    	}
          	} );
          	timer.setRepeats( false );
          	timer.start();
      	  }

    }

    public void updateScores() {
    	p1Label.setText("Player 1 score: " + p1.getPlayerScore());
    	p2Label.setText("Player 1 score: " + p1.getPlayerScore());
    }
    
    public void switchPlayers() {
    	if(currentPlayer == p1) {
    		currentPlayer = p2;
    		p2Label.setBackground(Color.decode("#fc0303"));
    		gameStatusLabel.setText("Current player: Player 2");
    		p1Label.setBackground(Color.WHITE);
    	}
    	else {
    		currentPlayer = p1;
    		p1Label.setBackground(Color.decode("#56CBF9"));
    		gameStatusLabel.setText("Current player: Player 1");
    		p2Label.setBackground(Color.WHITE);
    	}
    }
    
    public void updateLabel() {
		p1Label.setText("Player 1 score: " + p1.getPlayerScore());
		p2Label.setText("Player 2 score: " + p2.getPlayerScore());
    }
    
    public void checkWin() {
    	int boardsize = TILE_HEIGHT*TILE_WIDTH;
    	if(matched.size() >= boardsize) {
    		// Player 1 win
    		if(p1.getPlayerScore() > p2.getPlayerScore()) {
    			gameStatusLabel.setText("Game over! Player 1 wins!");
    			p1Label.setBackground(Color.decode("#56CBF9"));
    			p2Label.setBackground(Color.WHITE);
    		}
    		// Player 2 win
    		else if(p1.getPlayerScore() < p2.getPlayerScore()) {
    			gameStatusLabel.setText("Game over! Player 2 wins!");
    			p1Label.setBackground(Color.WHITE);
    			p2Label.setBackground(Color.decode("#fc0303"));
    		}
    		// Tied score
    		else {
    			gameStatusLabel.setText("Game over! Its a draw!");
    			p1Label.setBackground(Color.WHITE);
    			p2Label.setBackground(Color.WHITE);
    		}
    	}
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
        gameStatusLabel = new JLabel("Current player: Player 1");
        p1Label = new JLabel("Player 1 score: " + p1.getPlayerScore());
        p1Label.setOpaque(true);
        p1Label.setBackground(Color.decode("#56CBF9"));
        p2Label = new JLabel("Player 2 score: " + p2.getPlayerScore());
        p2Label.setOpaque(true);

        scorePanel.add(gameStatusLabel);
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
