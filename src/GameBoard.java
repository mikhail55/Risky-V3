
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JPanel{
    private GameCell[] provinces;

    private GameLogic logic;

    private Menu menu;

    public GameBoard() {
        // Add a mouse listener to the panel
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the point where the mouse was clicked
                Point clickPoint = e.getPoint();

                Player currentPlayer = logic.getCurrentPlayer();
                GameCell[][] cells = logic.getBoard();
                GameCell cellClicked = cells[0][0];

                boolean tileFound = false;

                int row = 0;
                int column = 0;

                while(!tileFound) {
                    if(clickPoint.getY() > 40 * (row + 1)) {
                        row++;
                    }

                    else if(clickPoint.getX() > 40 * (column + 1)) {
                        column ++;
                    }

                    else {
                        tileFound = true;
                        cellClicked = cells[row][column];
                    }
                }

                currentPlayer.tileClicked(cellClicked);
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        GameCell[][] cells = logic.getBoard();
        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                cells[i][n].drawCell(g, i, n);
            }
        }
        g.setColor(Color.BLUE);
        g.drawRect(0,0,30,30);
    }


}
