import java.util.Scanner;
import javax.swing.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		@SuppressWarnings("resource")
		Scanner cal = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Choose a game: memory or bejeweled");
	    
	    if(cal.next().equals("memory")) {
	    	System.out.println("Please specify the board size (i.e. 4 for 4x4)");
	    	int boardsize = cal.nextInt();
	    	SwingUtilities.invokeLater(() -> new memory(boardsize, boardsize));
	    }
	    else {
	    	SwingUtilities.invokeLater(() -> new bejeweled());
	    }
	}

}
