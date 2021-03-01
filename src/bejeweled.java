import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

//With inspiration from https://github.com/BillyBarbaro/Bejeweled/blob/master/Jewels.java

public class bejeweled {
    int TILE_HEIGHT = 8;
    int TILE_WIDTH = 8;
    JButton tiles[][];
    Color[][] board;

    Color[] PUBLIC_COLORS = new Color[] {Color.decode("#FFB5C7"), Color.decode("#B1ACDF"), Color.decode("#afcfde"), Color.decode("#C9DE9E"),Color.decode("#FAE2BE")};
    
	private int firstSelectedRow = -1;
	private int firstSelectedColumn = -1;

	public bejeweled() {
		// TODO Auto-generated constructor stub
        board = bejeweledBoardMaker(TILE_HEIGHT, TILE_WIDTH);
        SwingUtilities.invokeLater(() -> new tmge(TILE_HEIGHT, TILE_WIDTH, board));
	}
	
	public Color[][] bejeweledBoardMaker(int x, int y){
		Color[][] temp = new Color[x][y];
		int i = 0, j = 0;
		while(((i+1)*(j+1)) < (x*y)) {
			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			if(i < 2) {
				if(checkHorizontalForCreateBoard(i, j, temp, randomColor)){
					temp[i][j] = PUBLIC_COLORS[randomColor];
					if (++j == y) {
		    	        j = 0;
		    	        ++i;
		    	    }
				}
			}
			else{
				if(checkVerticalForCreateBoard(i,j,temp,randomColor) && checkHorizontalForCreateBoard(i, j, temp, randomColor)) {
					temp[i][j] = PUBLIC_COLORS[randomColor];
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
				temp[i][j] = PUBLIC_COLORS[randomColor];
				j++;
			}
		}
		
		return temp;
	}
	
	public boolean checkVerticalForCreateBoard(int x, int y, Color[][] currentBoard, int newcolor) {
		if(x >= 2) {
			if(currentBoard[x-1][y] == PUBLIC_COLORS[newcolor] && currentBoard[x-2][y] == PUBLIC_COLORS[newcolor]) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkHorizontalForCreateBoard(int x, int y, Color[][] currentBoard, int newcolor) {
		if(y >= 2) {
			if(currentBoard[x][y-1] == PUBLIC_COLORS[newcolor] && currentBoard[x][y-2] == PUBLIC_COLORS[newcolor]) {
				return false;
			}
		}
		return true;
	}




	public boolean spotsTouch(int secondSelectedRow, int secondSelectedColumn) 
	/*
		This function returns T/F depending on if the tiles are next to each other. Used to check whether a player is allowed to swap.
	*/
	{
    	return (secondSelectedRow == this.firstSelectedRow && 
		(secondSelectedColumn - this.firstSelectedColumn == 1 || secondSelectedColumn - this.firstSelectedColumn == -1)) 
		|| 
		(secondSelectedColumn == this.firstSelectedColumn && 
		(secondSelectedRow - this.firstSelectedRow == 1 || secondSelectedRow - this.firstSelectedRow == -1));
  	}

	public int upMatch(int row, int column) 
	/*
	  Returns the number of matches in the upwards direction that match the initial row,column tile
	*/
	{
		int matches = 0;
		
		// Color of the switched tile
		Color match = this.board[row][column];
		
		row--;
		//Iterate up through the board keeping in bounds to count the number of tiles with the same color
		while (row >= 0 && this.board[row][column] == match) {
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
		Color match = this.board[row][column];
		
		row++;
		//Iterate down through the board keeping in bounds to count the number of tiles with the same color
		while (row <= TILE_HEIGHT-1 && this.board[row][column] == match) {
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
		Color match = this.board[row][column];
		
		column++;
		//Iterate right through the board keeping in bounds to count the number of tiles with the same color
		while (column <= TILE_WIDTH-1 & this.board[row][column] == match) {
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
		Color match = this.board[row][column];
		
		column--;
		//Iterate left through the board keeping in bounds to count the number of tiles with the same color
		while (column >= 0 && this.board[row][column] == match) {
		  matches++;
		  column--;
		}
		return matches;
	}

	public void fallHorizontal(int row, int column, int left, int right) 
	/*
		Checks left and right of a single tile at row,column and deletes those buttons. Then creates the same number of buttons above.
		Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead. 
	*/
	{
     
		//loop through the furthest left to the furthest right
		for (int i = column - left; i <= column + right; i++)
		{
			int currentRow = row;
			
			//loop through every row above currentRow
			while (currentRow > 0) 
			{
				board[currentRow][i] = board[currentRow - 1][i];
				currentRow--;
			}


			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			board[currentRow][i] = PUBLIC_COLORS[randomColor];
		}
	  }

	public void fallVertical(int row, int column, int up, int down) 
	/*
		Checks up and down of a single tile at row,column and deletes those buttons. Then creates the same number of buttons above.
		Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead. 
	*/
	{
	
		//loop through the highest up to the lowest down
		for (int i = row - up - 1; i <= 0; i--)
		{
			board[row+down][column] = board[i][column];	
			row--;		
		}
		//loop through the top and creates colors
		for (int i =0; i<up+down+1; i++)
		{
			int randomColor = new Random().nextInt(PUBLIC_COLORS.length);
			board[i][column] = PUBLIC_COLORS[randomColor];
		}

	}

	public void switchJewels(int firstSelectedRow, int firstSelectedColumn, int secondSelectedRow, int secondSelectedColumn) 
	/*
		switches two colors. Eventually tiles-buttons- might be used instead of board-colors-, shifting will need to be done to that one instead.
	*/
	{
		Color first = board[firstSelectedRow][firstSelectedColumn];
		board[firstSelectedRow][firstSelectedColumn] = board[secondSelectedRow][secondSelectedColumn];
		board[secondSelectedRow][secondSelectedColumn] = first;
	  }
}
