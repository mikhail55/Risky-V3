
import javax.swing.*;
import java.awt.*;

public class GameDriver {
    public static void main(String[] args) {
        // The default dimensions of the window
        final int WIDTH = 760, HEIGHT = 680;

        JFrame frame = new JFrame("Risky");

        // Set up default window size
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        GameBoard board = new GameBoard();
        board.repaint();

        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        board.repaint();

    }
}
