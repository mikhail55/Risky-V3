import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameDriver {
    public static void main(String[] args) {
        final int WIDTH = 760, HEIGHT = 680;

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

        Player[] players = new Player[2];

        //GameLogic logic = new GameLogic(board.getBoard(), players);

        //players[0] = new Player(GameCell.Owner.Team1, logic);

        //players[1] = new Player(GameCell.Owner.Team2, logic);

        /*logic.setPlayers(players);

        board.getBoard()[0][1];

        board.getBoard()[0][1].setNumTroops(5);
        board.getBoard()[0][0].setNumTroops(2);
        board.getBoard()[1][0].setNumTroops(5);
        board.getBoard()[1][1].setNumTroops(2);


        logic.attack(board.getBoard()[0][1], board.getBoard()[0][0], 5);
        logic.attack(board.getBoard()[0][1], board.getBoard()[1][1], 5);*/

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
