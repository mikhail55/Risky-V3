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


        board.repaint();

        Player[] players = new Player[2];

        GameLogic logic = new GameLogic(board.getBoard(), players);

        players[0] = new Player(GameCell.Owner.Team1, logic);

        players[1] = new Player(GameCell.Owner.Team2, logic);

        logic.setPlayers(players);

        board.getBoard()[0][1];

        board.getBoard()[0][1].setNumTroops(5);
        board.getBoard()[0][0].setNumTroops(2);
        board.getBoard()[1][0].setNumTroops(5);
        board.getBoard()[1][1].setNumTroops(2);


        logic.attack(board.getBoard()[0][1], board.getBoard()[0][0], 5);
        logic.attack(board.getBoard()[0][1], board.getBoard()[1][1], 5);




    }
}
