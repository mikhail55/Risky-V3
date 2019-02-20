import javax.swing.*;

public class GameDriver {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Risky Man");

        GameBoard board = new GameBoard();
        board.repaint();

        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (true){
            board.repaint();
        }

    }
}
