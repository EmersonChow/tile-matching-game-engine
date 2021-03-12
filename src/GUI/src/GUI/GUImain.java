package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Bejeweled.*;
import Memory.*;

import javax.swing.*;


public class GUImain implements ActionListener {

	JFrame frame;
	JPanel panel;
	GridBagLayout grid;
	GridBagConstraints gbc;

	JLabel pickLbl;
	JLabel pickBoardLbl;

	JButton memory;
	JButton bejeweled;

	JTextField boardSize;
	JTextField pl1;
	JTextField pl2;

	public GUImain() {
		frame = new JFrame();
		frame.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.gridx = 0;
		gbc.gridy = 0;

		frame = new JFrame("TMGE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);

		panel = new JPanel();

		grid = new GridBagLayout();
		panel.setLayout(grid);

		gbc.gridx = 0;
		gbc.gridy = 3;
		pickLbl = new JLabel("Pick a GAME");
		panel.add(pickLbl, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		memory = new JButton("MEMORY");
		panel.add(memory, gbc);
		memory.addActionListener(this);

		gbc.gridx = 1;
		gbc.gridy = 4;
		bejeweled = new JButton("BEJEWELED");
		panel.add(bejeweled, gbc);
		bejeweled.addActionListener(this);

		gbc.gridx = 0;
		gbc.gridy = 1;
		pl1 = new JTextField("Player1");
		panel.add(pl1, gbc);
		pl1.addActionListener(this);

		gbc.gridx = 1;
		gbc.gridy = 1;
		pl2 = new JTextField("Player2");
		panel.add(pl2, gbc);
		pl2.addActionListener(this);

		frame.setContentPane(panel);
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String pla1 = pl1.getText();
		String pla2 = pl2.getText();

		if (e.getSource() == memory) {

			frame.setVisible(false);
			SwingUtilities.invokeLater(() -> new Memory(4, 4, pla1, pla2));
		}
		if (e.getSource() == bejeweled) {
			System.out.println("bejeweled");
			frame.setVisible(false);
			SwingUtilities.invokeLater(() -> new Bejeweled(pla1,pla2));
		}
	}

	public static void main(String[] args) {
		new GUImain();
	}

}
