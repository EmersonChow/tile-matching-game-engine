import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class memory {
    int TILE_HEIGHT;
    int TILE_WIDTH;
    JButton tiles[][];
    Color[][] board;
	
    Color[] PUBLIC_COLORS = new Color[] {Color.decode("#dc143c"), Color.decode("#0000ff"), Color.decode("#008000"), Color.decode("#ffb6c1"),Color.decode("#7b68ee"),
    		Color.decode("#ff8c00"), Color.decode("#00ff00"), Color.decode("#008b8b"), Color.decode("#ffd700"),  Color.decode("#8b4513"), Color.decode("#8fbc8f"), Color.decode("#8a2be2"),
    		Color.decode("#98fb98"), Color.decode("#7b68ee"), Color.decode("#ff1493"), Color.decode("#fa8072"), Color.decode("#1e90ff"), Color.decode("#ff00ff"),
    		Color.decode("#b0c4de"), Color.decode("#da70d6"), Color.decode("#adff2f"), Color.decode("#40e0d0"), Color.decode("#deb887"),
    		Color.decode("#ff4500"), Color.decode("#b03060"), Color.decode("#7f007f"), Color.decode("#32cd32"), Color.decode("#00008b"), Color.decode("#4682b4"), 
    		Color.decode("#556b2f"), Color.decode("#7fffd4"), Color.decode("#cd5c5c")};
    
    
    public memory(int x, int y){
        TILE_HEIGHT = x;
        TILE_WIDTH = y;
        board = boardRandomizer(x, y);
        SwingUtilities.invokeLater(() -> new tmge(x, y, board));
        
    }

    private Color[][] boardRandomizer(int x, int y) {
    	Color[][] temp = new Color[x][y];
    	ArrayList<Color> colors = new ArrayList<Color>();
    	
    	for(int i = 0; i < ((x*y)/2); i++) {
    		colors.add(PUBLIC_COLORS[i]);
    		colors.add(PUBLIC_COLORS[i]);
    	}
    	
    	Collections.shuffle(colors);
    	
    	int i = 0, j = 0;
    	for (final Color colo : colors) {
    	    temp[i][j] = colo;
    	    if (++j == x) {
    	        j = 0;
    	        ++i;
    	    }
    	}
    	
    	return temp;
    }

}
