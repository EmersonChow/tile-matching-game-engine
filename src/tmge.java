import javax.swing.*;
import java.awt.*;

public class tmge {
    JFrame mainFrame;
    JPanel tilePanel;
    JButton tiles[][];
    

    public tmge(int x, int y) {
        mainFrame = new JFrame("TMGE");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700, 700);

        tilePanel = new JPanel();
        tiles = new JButton[x][y];
        for (int i = 0 ; i < x ; i++) {
            for (int j = 0; j < y; j++) {
                tiles[i][j] = new JButton();
                tilePanel.add(tiles[i][j]);
            }
        }

        tilePanel.setLayout(new GridLayout(y, x, 2,2));

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(tilePanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new tmge(8, 8));
    }
}
