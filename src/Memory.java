import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Memory {
    int TILE_HEIGHT;
    int TILE_WIDTH;
    JButton tiles[][];
    Tile[][] board;
    TMGE env;

	Component firstTileButton;
	Component secondTileButton;

	Tile firstTile;
	Tile secondTile;

	MemoryScoreboard scoreboard;

	Player p1;
	Player p2;

	ArrayList<Tile> matched;
	Player currentPlayer;


    final Color[] PUBLIC_COLORS = new Color[] {Color.decode("#dc143c"), Color.decode("#0000ff"), Color.decode("#008000"), Color.decode("#ffb6c1"),Color.decode("#7b68ee"),
    		Color.decode("#ff8c00"), Color.decode("#00ff00"), Color.decode("#008b8b"), Color.decode("#ffd700"),  Color.decode("#8b4513"), Color.decode("#8fbc8f"), Color.decode("#8a2be2"),
    		Color.decode("#98fb98"), Color.decode("#7b68ee"), Color.decode("#ff1493"), Color.decode("#fa8072"), Color.decode("#1e90ff"), Color.decode("#ff00ff"),
    		Color.decode("#b0c4de"), Color.decode("#da70d6"), Color.decode("#adff2f"), Color.decode("#40e0d0"), Color.decode("#deb887"),
    		Color.decode("#ff4500"), Color.decode("#b03060"), Color.decode("#7f007f"), Color.decode("#32cd32"), Color.decode("#00008b"), Color.decode("#4682b4"),
    		Color.decode("#556b2f"), Color.decode("#7fffd4"), Color.decode("#cd5c5c")};

	public Memory(int WIDTH, int HEIGHT){
		TILE_HEIGHT = HEIGHT;
		TILE_WIDTH = WIDTH;
		matched = new ArrayList<Tile>();
		p1 = new Player();
		p2 = new Player();
		currentPlayer = p1;
		board = boardRandomizer(HEIGHT, WIDTH);
		env = new TMGE(WIDTH, HEIGHT, "Memory Game");

		tiles = env.getTilesInterface();
		for (int i = 0 ; i < HEIGHT ; i++) {
			for (int j = 0; j < WIDTH; j++) {
				tiles[i][j].addActionListener(new ListenerForClick());
			}
		}
		scoreboard = new MemoryScoreboard(env.getScorePanel(), p1, p2);

	}

	private class ListenerForClick implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < TILE_WIDTH; i++) {
				for(int j = 0; j < TILE_HEIGHT; j++) {
					if (e.getSource() == tiles[i][j]) {
						if (e.getSource() instanceof Component) {
							try {
								checkMatch(i,j,e);
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
		
		if(firstTile == null && !matched.contains(board[x][y])) {
			firstTile = board[x][y];
			firstTileButton = ((Component) e.getSource());
			firstTileButton.setBackground(board[x][y].getColor());
			firstTile.reveal();
			firstTileButton.setEnabled(false);
		}

		// Reveals second tile & checks for match
    	else {
    		secondTile = board[x][y];
        	secondTileButton = ((Component) e.getSource());
        	secondTileButton.setBackground(secondTile.getColor());
        	secondTile.reveal();
        	// Disable buttons for sleep duration
        	disableButtons();
        	
        	int delay = 750;
        	Timer timer = new Timer( delay, new ActionListener(){
        	  @Override
        	  public void actionPerformed( ActionEvent e ){
		    		if(firstTile != secondTile && !matched.contains(secondTile)) {
			    		// Successful Match
		    			if(firstTile.getColor() == secondTile.getColor()) {
							secondTileButton.setBackground(secondTile.getColor());
							secondTile.reveal();

							// add to matched list
							matched.add(firstTile);
							matched.add(secondTile);

							currentPlayer.addPoint();
							scoreboard.updateScores();
							checkWin();
						}
			    		
			    		// Hide both tiles again
			    		else {
			    			firstTileButton.setBackground(Color.GRAY);
			    			firstTile.hide();
			    			
			    			secondTileButton.setBackground(Color.GRAY);
			    			secondTile.hide();
			    			
			    			currentPlayer = scoreboard.switchPlayers(currentPlayer);
			    			scoreboard.rotateTurn(currentPlayer);
			    		}
			    		
			    		// Reset selected tiles
			    		firstTile = null;
			    		firstTileButton = null;
			    		secondTile = null;
			    		secondTileButton = null;
		    		}
		    		enableButtons();
		    	}
          	} );
          	timer.setRepeats( false );
          	timer.start();
      	  }
		
	}
	
	public void disableButtons() {
		// Disables all buttons
		
		for (int i = 0 ; i < TILE_HEIGHT ; i++) {
            for (int j = 0 ; j < TILE_WIDTH ; j++) {
            	tiles[i][j].setEnabled(false);
            }
		}
	}
	
	public void enableButtons() {
		// Re-enables buttons that have not already been matched
		
		for (int i = 0 ; i < TILE_HEIGHT ; i++) {
            for (int j = 0 ; j < TILE_WIDTH ; j++) {
            	if (!matched.contains(board[i][j])) {
            		tiles[i][j].setEnabled(true);
            	}
            }
		}
	}

	public void checkWin() {
		int boardsize = TILE_HEIGHT*TILE_WIDTH;
		if(matched.size() >= boardsize) {
			// Player 1 win
			if(p1.getPlayerScore() > p2.getPlayerScore()) {
				scoreboard.declareWinner(p1);
			}
			// Player 2 win
			else if(p1.getPlayerScore() < p2.getPlayerScore()) {
				scoreboard.declareWinner(p2);
			}
			// Tied score
			else {
				scoreboard.declareWinner();
			}
		}
	}

    private Tile[][] boardRandomizer(int x, int y) {
    	Tile[][] board = new Tile[x][y];
    	ArrayList<Color> colors = new ArrayList<Color>();

    	for(int i = 0; i < ((x*y)/2); i++) {
    		colors.add(PUBLIC_COLORS[i]);
    		colors.add(PUBLIC_COLORS[i]);
    	}

    	Collections.shuffle(colors);

    	int i = 0, j = 0;
    	for (final Color colo : colors) {
    	    board[i][j] = new Tile(colo);
    	    if (++j == x) {
    	        j = 0;
    	        ++i;
    	    }
    	}

    	return board;
    }

}
