import javax.swing.*;

public class memory {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new tmge(3, 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
