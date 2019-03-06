
import javax.swing.*;
import java.awt.*;
//import java.awt.event.MouseListener;

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
//        for (int i = 0; i < cells.length; i++){
//            for (int j = 0; j <cells[i].length; j++){
//                if(i == 0 || j ==0){
//                    cells[i][j] = new GameCell(GameCell.Owner.Water, new Point(i, j));
//                }
//            }
//        }
        board = cells;


    }

    public void paintComponent(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRect(0,0,30,30);
    }

    public GameCell[][] getBoard() {
        return board;
    }
}
