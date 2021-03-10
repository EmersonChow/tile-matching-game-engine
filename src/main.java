import java.util.Scanner;
import javax.swing.*;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		@SuppressWarnings("resource")
		Scanner cal = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Choose a game: memory or bejeweled");
	    
	    if(cal.next().equals("memory")) {
	    	SwingUtilities.invokeLater(() -> new Memory(4, 4));
	    }
	    else {
	    	SwingUtilities.invokeLater(() -> new bejeweled());
	    }
	}

}
