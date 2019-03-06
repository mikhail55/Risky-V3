
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel{
    private GameCell[] provinces;

    private GameLogic logic;

    private Menu menu;

    public GameBoard() {

    }

    public void repaint(Graphics g){
        GameCell[][] cells = logic.getBoard();
        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                //cells[i][n].drawCell();
            }
        }
        g.setColor(Color.BLUE);
        g.drawRect(0,0,30,30);
    }


}
