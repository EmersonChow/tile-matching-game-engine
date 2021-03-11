package Bejeweled;

import TMGE.Player;
import TMGE.TMGE;
import TMGE.Tile;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

//With inspiration from https://github.com/BillyBarbaro/Bejeweled/blob/master/Jewels.java

public class Bejeweled {
    int TILE_HEIGHT = 8;
    int TILE_WIDTH = 8;
    JButton tiles[][];
    BejeweledTile[][] board;
    TMGE env;

    Color[] PUBLIC_COLORS = new Color[] {Color.decode("#FFB5C7"), Color.decode("#B1ACDF"), Color.decode("#afcfde"), Color.decode("#C9DE9E"),Color.decode("#FAE2BE")};
    
	private int firstSelectedRow = -1;
	private int firstSelectedColumn = -1;
	
	BejeweledScoreboard scoreboard;
	int timeLeft = 60000; // 60 seconds
	boolean bothRoundsPlayed = false; // Check if both players got a turn
	
	Player p1;
	Player p2;
	
	Player currentPlayer;
	
    Timer gameTimer;
	

	public Bejeweled() {
		// TODO Auto-generated constructor stub
		board = bejeweledBoardMaker(TILE_HEIGHT, TILE_WIDTH);
		env = new TMGE(TILE_WIDTH, TILE_HEIGHT, "Bejeweled.Bejeweled Game");
		p1 = new Player();
		p2 = new Player();
        tiles = env.getTilesInterface();
		for (int i = 0 ; i < TILE_HEIGHT ; i++) {
			for (int j = 0; j < TILE_WIDTH; j++) {
				tiles[i][j].addActionListener(new ListenerForClick());
			}
		}
		scoreboard = new BejeweledScoreboard(env.getScorePanel(), p1, p2, timeLeft);
		revealAllColorsBoardCreation(tiles, board);
		currentPlayer = p1;
		
		gameTimer = new Timer(100, new ActionListener() {

	        public void actionPerformed(ActionEvent e) {
	            timeLeft -= 100;
	            scoreboard.updateTime(timeLeft);
	            if(timeLeft<=0 && !bothRoundsPlayed)
	            {
	                gameTimer.stop();
	                currentPlayer = scoreboard.switchPlayers(currentPlayer);
	                scoreboard.rotateTurn(currentPlayer);
	                timeLeft = 60000;
	                bothRoundsPlayed = true;
	                gameTimer.start();
	            }
	            else if(timeLeft<=0 && bothRoundsPlayed) {
	            	gameTimer.stop();
	            	checkWin();
	            }
	        }
	    });
		
		gameTimer.start();
		
	}
	
	private class ListenerForClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < TILE_WIDTH; i++) {
				for(int j = 0; j < TILE_HEIGHT; j++) {
					if (e.getSource() == tiles[i][j]) {
						if (e.getSource() instanceof Component) {
							if (firstSelectedRow == -1 && firstSelectedColumn == -1) {
								((Component) e.getSource()).setBackground(Color.GRAY);
								firstSelectedRow = i;
								firstSelectedColumn = j;
							} else {
								
								if (i == firstSelectedRow && j == firstSelectedColumn) {
									firstSelectedRow = -1;
									firstSelectedColumn= -1;
									revealAllColorsBoardCreation(tiles, board);
								}
								else if (spotsTouch(i,j)) {
									//check if there is a match
									switchJewels(firstSelectedRow, firstSelectedColumn, i, j);
									if (checkMatch(firstSelectedRow, firstSelectedColumn, i, j))
									{
										performLogic(i,j);
										performLogic(firstSelectedRow,firstSelectedColumn);
										checkEntireBoard();
									}
									//if no match, then switch them back
									else {
										switchJewels(firstSelectedRow, firstSelectedColumn, i, j);
									}

									
									firstSelectedRow = -1;
									firstSelectedColumn= -1;
									
									revealAllColorsBoardCreation(tiles, board);
								
								}
							}
						}
					}
				}
			}
		}
    }
	
	public BejeweledTile[][] bejeweledBoardMaker(int x, int y){
		BejeweledTile[][] temp = new BejeweledTile[x][y];
		int i = 0, j = 0;
		while(((i+1)*(j+1)) < (x*y)) {
			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			if(i < 2) {
				if(checkHorizontalForCreateBoard(i, j, temp, randomColor)){
					temp[i][j] = new BejeweledTile(PUBLIC_COLORS[randomColor],i,j);
					if (++j == y) {
		    	        j = 0;
		    	        ++i;
		    	    }
				}
			}
			else{
				if(checkVerticalForCreateBoard(i,j,temp,randomColor) && checkHorizontalForCreateBoard(i, j, temp, randomColor)) {
					temp[i][j] = new BejeweledTile(PUBLIC_COLORS[randomColor],i,j);
					if (++j == y) {
		    	        j = 0;
		    	        ++i;
		    	    }
				}
			}
		}
		
		// This is for the very last tile because the above while loop ends before it gets there. Feel free to fix this.
		while(j < y) {
			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			if(checkVerticalForCreateBoard(i,j,temp,randomColor) && checkHorizontalForCreateBoard(i, j, temp, randomColor)) {
				temp[i][j] = new BejeweledTile(PUBLIC_COLORS[randomColor],i,j);
				j++;
			}
		}
		
		return temp;
	}
	
	public boolean checkVerticalForCreateBoard(int x, int y, Tile[][] currentBoard, int newcolor) {
		if(x >= 2) {
			if(currentBoard[x-1][y].getColor() == PUBLIC_COLORS[newcolor] && currentBoard[x-2][y].getColor() == PUBLIC_COLORS[newcolor]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkHorizontalForCreateBoard(int x, int y, Tile[][] currentBoard, int newcolor) {
		if(y >= 2) {
			if(currentBoard[x][y-1].getColor() == PUBLIC_COLORS[newcolor] && currentBoard[x][y-2].getColor() == PUBLIC_COLORS[newcolor]) {
				return false;
			}
		}
		return true;
	}
	
	public void revealAllColorsBoardCreation(JButton[][] tilebuttons, Tile[][] board) {
		int i = 0, j = 0;
		while(((i+1)*(j+1)) < (TILE_WIDTH*TILE_HEIGHT)) {
    	    tilebuttons[i][j].setBackground(board[i][j].getColor());
    	    if (++j == TILE_HEIGHT) {
    	        j = 0;
    	        ++i;
    	    }
    	}
		
			tilebuttons[i][j].setBackground(board[i][j].getColor());
			j++;
	}

	public boolean spotsTouch(int secondSelectedRow, int secondSelectedColumn) 
	/*
		This function returns T/F depending on if the tiles are next to each other. Used to check whether a player is allowed to swap.
	*/
	{
    	return (this.firstSelectedRow  == secondSelectedRow && 
		(this.firstSelectedColumn -secondSelectedColumn == 1 || this.firstSelectedColumn - secondSelectedColumn == -1)) 
		|| 
		(this.firstSelectedColumn == secondSelectedColumn && 
		(this.firstSelectedRow -secondSelectedRow == 1 || this.firstSelectedRow - secondSelectedRow == -1));
  	}

	public int upMatch(int row, int column) 
	/*
	  Returns the number of matches in the upwards direction that match the initial row,column tile
	*/
	{
		int matches = 0;
		
		// Color of the switched tile
		Color match = this.board[row][column].getColor();
		
		row--;
		//Iterate up through the board keeping in bounds to count the number of tiles with the same color
		while (row >= 0 && this.board[row][column].getColor() == match) {
		  matches++;
		  row--;
		}
		return matches;
	}

	public int downMatch(int row, int column) 
	/*
	  Returns the number of matches in the down direction that match the initial row,column tile
	*/
	{
		int matches = 0;
		
		// Color of the switched tile
		Color match = this.board[row][column].getColor();
		
		row++;
		//Iterate down through the board keeping in bounds to count the number of tiles with the same color
		while (row <= TILE_HEIGHT-1 && this.board[row][column].getColor() == match) {
		  matches++;
		  row++;
		}
		return matches;
	}

	public int rightMatch(int row, int column) 
	/*
	  Returns the number of matches in the right direction that match the initial row,column tile
	*/
	{
		int matches = 0;
		
		// Color of the switched tile
		Color match = this.board[row][column].getColor();
		
		column++;
	
		//Iterate right through the board keeping in bounds to count the number of tiles with the same color
		while (column <= TILE_WIDTH-1 && this.board[row][column].getColor() == match) {
		matches++;
		column++;
		}
		
		return matches;
	}

	public int leftMatch(int row, int column) 
	/*
	  Returns the number of matches in the left direction that match the initial row,column tile
	*/
	{
		int matches = 0;
		
		// Color of the switched tile
		Color match = this.board[row][column].getColor();
		
		column--;
		//Iterate left through the board keeping in bounds to count the number of tiles with the same color
		while (column >= 0 && this.board[row][column].getColor() == match) {
		  matches++;
		  column--;
		}
		return matches;
	}

	public ArrayList<BejeweledTile> fallHorizontal(int row, int column, int left, int right) 
	/*
		Checks left and right of a single tile at row,column and deletes those buttons. Then creates the same number of buttons above.
		Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead. 
	*/
	{
		
	
		Set<BejeweledTile> TileList = new HashSet<BejeweledTile>();
		//loop through the furthest left to the furthest right
		for (int i = column - left; i < column + right +1; ++i)
		{
			int currentRow = row;
			
			//loop through every row above currentRow
			while (currentRow > 0) 
			{
				board[currentRow][i] = board[currentRow - 1][i];
				TileList.add(board[currentRow][i]);
	
				currentRow--;
			}


			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			board[0][i] = new BejeweledTile(PUBLIC_COLORS[randomColor],0,i);
			TileList.add(board[0][i]);
		}

		ArrayList<BejeweledTile> myTileList = new ArrayList<BejeweledTile>();
		for(BejeweledTile tile:TileList)
		{
			myTileList.add(tile);
		}
		
		return myTileList;
	  }

	public ArrayList<BejeweledTile> fallVertical(int row, int column, int up, int down) 
	/*
		Checks up and down of a single tile at row,column and deletes those buttons. Then creates the same number of buttons above.
		Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead. 
	*/
	{
		Set<BejeweledTile> TileList = new HashSet<BejeweledTile>();
		
		//loop through the highest up to the lowest down

		for (int i =0; i<up+down+1; i++)
		{
			if (row-up>0)
			{
				board[row+down][column] = board[row-up-1][column];
				TileList.add(board[row+down][column]);
				row--;
			}
		}


		//loop through the top and creates colors
		for (int i =0; i<up+down+1; i++)
		{
			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			board[i][column] = new BejeweledTile(PUBLIC_COLORS[randomColor],i,column);
			TileList.add(board[i][column]);
		}
		
		ArrayList<BejeweledTile> myTileList = new ArrayList<BejeweledTile>();
		for(BejeweledTile tile:TileList)
		{
			myTileList.add(tile);
		}
		return myTileList;

	}

	public void switchJewels(int firstSelectedRow, int firstSelectedColumn, int secondSelectedRow, int secondSelectedColumn) 
	/*
		switches two colors. Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead.
	*/
	{
		BejeweledTile first = board[firstSelectedRow][firstSelectedColumn];
		board[firstSelectedRow][firstSelectedColumn] = board[secondSelectedRow][secondSelectedColumn];
		board[secondSelectedRow][secondSelectedColumn] = first;
	  }
	
	public boolean checkMatch(int firstSelectedRow, int firstSelectedColumn, int secondSelectedRow,  int secondSelectedColumn)
	//returns True if there is a match
	{
		//check firstTile
		int rmatch = rightMatch(firstSelectedRow,firstSelectedColumn);
		int lmatch = leftMatch(firstSelectedRow,firstSelectedColumn);
		int umatch = upMatch(firstSelectedRow,firstSelectedColumn);
		int dmatch = downMatch(firstSelectedRow,firstSelectedColumn); 
		if (lmatch + rmatch +1 >=3)
		{
			return true;
		}
		else if (umatch + dmatch +1>=3)
		{
			return true;
		}

		//check secondTile
		int rmatch2 = rightMatch(secondSelectedRow,secondSelectedColumn);
		int lmatch2 = leftMatch(secondSelectedRow,secondSelectedColumn);
		int umatch2 = upMatch(secondSelectedRow,secondSelectedColumn);
		int dmatch2 = downMatch(secondSelectedRow,secondSelectedColumn); 
		if (lmatch2 + rmatch2 +1 >=3)
		{
			return true;
		}
		else if (umatch2 + dmatch2 +1>=3)
		{
			return true;
		}
		return false;
	}


	public boolean performLogic(int row, int col) {
		
		// call checking functions
		int rmatch = rightMatch(row,col);
		int lmatch = leftMatch(row,col);
		int umatch = upMatch(row,col);
		int dmatch = downMatch(row,col);
		
		ArrayList<BejeweledTile> tileList = new ArrayList<BejeweledTile>();
						
		
		// check for horizontal match
		if ( lmatch + rmatch + 1 >= 3) {
			  tileList.addAll(fallHorizontal( row,col,lmatch,rmatch)); 
			  currentPlayer.addPoint();
			  scoreboard.updateScores();
			return true;
		}
		
	
		// check for vertical matching
		else if (umatch + dmatch + 1 >= 3) {
			tileList.addAll(fallVertical( row,col,umatch,dmatch));
			currentPlayer.addPoint();
			scoreboard.updateScores();
			return true;
		}
		
		return false;

	}

	public void checkEntireBoard()
	// Loop through the entire board and clear matches.
	{
		for (int i = 0; i < TILE_HEIGHT; ++i) {
			
			for (int j = 0; j < TILE_WIDTH; ++j) {
				if (performLogic(i,j)) {
					checkEntireBoard();
				}

			}		
			
		}
	}
	
	public void checkWin() {
		if(p1.getPlayerScore() > p2.getPlayerScore()) {
			scoreboard.declareWinner(p1);
		}
		// TMGE.Player 2 win
		else if(p1.getPlayerScore() < p2.getPlayerScore()) {
			scoreboard.declareWinner(p2);
		}
		// Tied score
		else {
			scoreboard.declareWinner();
		}
	}
	
	
}
