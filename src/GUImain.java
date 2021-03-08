import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUImain implements ActionListener{

	JFrame frame;
	JPanel panel;
	GridBagLayout  grid;
	GridBagConstraints gbc;

	JLabel pickLbl;
	JLabel pickBoardLbl;

	JButton memory;
	JButton bejeweled;

	JButton two;
	JButton four;
	JButton six;
	JButton eight;


	public GUImain() {
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx=1;
		gbc.weighty=0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(2,2,2,2);
		gbc.gridx = 0;
		gbc.gridy = 0;


		frame = new JFrame("TMGE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);

		panel = new JPanel();

		grid = new GridBagLayout();
		panel.setLayout(grid);

		gbc.gridx = 1;
		gbc.gridy = 0;
		pickLbl = new JLabel("Pick a GAME");
		panel.add(pickLbl, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		memory = new JButton("MEMORY");
		panel.add(memory, gbc);
		memory.addActionListener(this);
		gbc.gridx = 2;
		gbc.gridy = 1;
		bejeweled = new JButton("BEJEWELED");
		panel.add(bejeweled, gbc);
		bejeweled.addActionListener(this);

		frame.setContentPane(panel);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == memory)
		{
			frame.setVisible(false);
			SwingUtilities.invokeLater(() -> new Memory(4, 4));
		}
		if(e.getSource() == bejeweled)
		{
			System.out.println("bejeweled");
			frame.setVisible(false);
//			SwingUtilities.invokeLater(() -> new bejeweled());
		}
	}


	public static void main(String[] args) {

//		@SuppressWarnings("resource")
//		Scanner cal = new Scanner(System.in); // Create a Scanner object
//		// System.out.println("Choose a game: memory or bejeweled");
//
//		if (cal.next().equals("memory")) {
//			// System.out.println("Please specify the board size (i.e. 4 for 4x4)");
//			int boardsize = cal.nextInt();
//			SwingUtilities.invokeLater(() -> new memory(boardsize, boardsize));
//		}
		new GUImain();
	}



}
