
import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel{
    private GameCell[] provinces;

    private GameLogic logic;

    private Menu menu;

    public GameBoard() {
    }

    public void repaint(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRect(0,0,30,30);
    }


}
