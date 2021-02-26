import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class bejeweled {
    int TILE_HEIGHT = 8;
    int TILE_WIDTH = 8;
    JButton tiles[][];
    Color[][] board;

    Color[] PUBLIC_COLORS = new Color[] {Color.decode("#FFB5C7"), Color.decode("#B1ACDF"), Color.decode("#afcfde"), Color.decode("#C9DE9E"),Color.decode("#FAE2BE")};
    
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

}
