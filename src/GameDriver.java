import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDriver {
    public static void main(String[] args) {
        final int WIDTH = 761, HEIGHT = 703;

        JFrame frame = new JFrame("Risky Man");

        // Set up default window size
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        GameBoard board = new GameBoard();
        board.repaint();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(board);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        board.repaint();

        Player[] players = new Player[4];

        GameLogic logic = new GameLogic();

        players[0] = new Player(GameCell.Owner.Team1, logic);
        players[1] = new Player(GameCell.Owner.Team2, logic);
        players[2] = new Player(GameCell.Owner.Team3, logic);
        players[3] = new Player(GameCell.Owner.Team4, logic);

        logic.setPlayers(players);
        logic.setGameBoard(board);

        // Repaint the program each time taskPerformer is called
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.repaint();
            }
        };

        // Call taskPerformer every 60 ticks
        Timer clock = new Timer(60, taskPerformer);
        clock.start();

    }
}
