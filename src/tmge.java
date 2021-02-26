import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tmge {
    JFrame mainFrame;
    JPanel tilePanel;
    JButton tiles[][];
    int TILE_HEIGHT;
    int TILE_WIDTH;

    Color TMGEboard[][];

    private class ListenerForClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < TILE_WIDTH; i++) {
                for(int j = 0; j < TILE_HEIGHT; j++) {
                    if (e.getSource() == tiles[i][j]) {
                        if (e.getSource() instanceof Component) {
                        	((Component) e.getSource()).setBackground(TMGEboard[i][j]);
                        }
                    }
                }
            }
        }
    }

    public tmge(int x, int y, Color[][] board) {
        TILE_HEIGHT = x;
        TILE_WIDTH = y;
        TMGEboard = board;
        mainFrame = new JFrame("TMGE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);

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
        mainFrame.add(tilePanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
