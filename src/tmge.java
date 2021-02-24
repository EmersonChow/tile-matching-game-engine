import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class tmge {
    JFrame mainFrame;
    JPanel tilePanel;
    JPanel northPanel;
    JPanel northRightPanel;
    JPanel northLeftPanel;
    JPanel southPanel;
    JButton tiles[][];
    int TILE_HEIGHT;
    int TILE_WIDTH;

    private JLabel lblTimer;
//    private JLabel lblDifficulty;
    private JLabel lblPlayer1;
    private JLabel lblPlayer2;

    private MGPlayer player1 = new MGPlayer(100);
    private MGPlayer player2 = new MGPlayer(101);

    private JLabel lblScore1;
    private JLabel lblScore2;

    int elapsedTime = 0;
    int sec = 0;
    int min = 0;
    String sec_string = String.format("%02d", sec);
    String min_string = String.format("%02d", min);
    boolean started = false;

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime + 1000;

            min = (elapsedTime/60000) % 60;
            sec = (elapsedTime/1000) % 60;
            sec_string = String.format("%02d", sec);
            min_string = String.format("%02d", min);

            lblTimer.setText(min_string + ":" + sec_string);
        }

    });


    Color temp[][] =
            {{Color.RED, Color.BLUE, Color.GREEN},
            {Color.RED, Color.BLUE, Color.GREEN},
            {Color.RED, Color.BLUE, Color.GREEN}};

    private class ListenerForClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < TILE_WIDTH; i++) {
                for(int j = 0; j < TILE_HEIGHT; j++) {
                    if (e.getSource() == tiles[i][j]) {
                        if (e.getSource() instanceof Component) {
                            ((Component) e.getSource()).setBackground(temp[i][j]);
                        }
                    }
                }
            }
        }
    }

    public tmge(int x, int y) throws InterruptedException {
        TILE_HEIGHT = x;
        TILE_WIDTH = y;

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

        tilePanel.setLayout(new GridLayout(y, x, 3,3));





        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 4, 5,20));

        northRightPanel= new JPanel();
        northRightPanel.setLayout(new GridLayout(2, 1, 5,5));

        northLeftPanel = new JPanel();
        northLeftPanel.setLayout(new GridLayout(2, 1, 5,5));

        southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        lblTimer = new JLabel();

        southPanel.add(lblTimer, BorderLayout.CENTER);
//        timer = new Thread(){
//            public void run(){
//                try{
//                    while() {
//                        Calendar calendr = new GregorianCalendar();
//                        int min = calendr.get(Calendar.MINUTE);
//                        int sec = calendr.get(Calendar.SECOND);
//
//                        lblTimer.setText(min + ":" + sec);
//
//                        sleep(1000);
//                    }
//                }
//                catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        };


        southPanel.add(lblTimer, BorderLayout.SOUTH);
        timer.start();

        lblPlayer1 = new JLabel();
        lblPlayer1.setText("Player1");


        lblScore1 = new JLabel();
        lblScore1.setText("Pairs: " + player1.getPlayerScore());


        lblPlayer2 = new JLabel();
        lblPlayer2.setText("Player2");


        lblScore2 = new JLabel();
        lblScore2.setText("Pairs: " + player2.getPlayerScore());

        // the order is importnat here

        northPanel.add(lblTimer);
        northLeftPanel.add(lblPlayer1);
        northLeftPanel.add(lblScore1);
        northPanel.add(northLeftPanel);

        northRightPanel.add(lblPlayer2);
        northRightPanel.add(lblScore2);
        northPanel.add(northRightPanel);




        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(tilePanel, BorderLayout.CENTER);
        mainFrame.add(northPanel, BorderLayout.NORTH);
        mainFrame.add(southPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);


    }
    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        elapsedTime = 0;
        sec = 0;
        min = 0;

        sec_string = String.format("%02d", sec);
        min_string = String.format("%02d", min);
        lblTimer.setText(min_string + ":" + sec_string);
    }


}
