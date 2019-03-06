
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class GameBoard extends JPanel{
    private GameCell[] provinces;

    private GameCell[][] board;

    private GameLogic logic;

    private Menu menu;

    public GameBoard() {
        GameCell[][] cells = new GameCell[2][2];
        cells[0][0] = new GameCell(GameCell.Owner.Team1, new Point(0,0));
        cells[0][1] = new GameCell(GameCell.Owner.Team1, new Point(0,1));
        cells[1][0] = new GameCell(GameCell.Owner.Team2, new Point(1,0));
        cells[1][1] = new GameCell(GameCell.Owner.Team2, new Point(1,1));
        board = cells;


    }

    public void repaint(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRect(0,0,30,30);
    }

    public GameCell[][] getBoard() {
        return board;
    }
}
