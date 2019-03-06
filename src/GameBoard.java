
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JPanel{
    private GameCell[] provinces;

    private GameCell[][] cells;

    private GameLogic logic;

    private Menu menu;

    int rows = 17;
    int columns = 19;
    int cellSize = 40;

    public GameBoard() {
        setUpGameCells();

        // Add a mouse listener to the panel
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the point where the mouse was clicked
                Point clickPoint = e.getPoint();

                Player currentPlayer = logic.getCurrentPlayer();
                GameCell cellClicked = cells[0][0];

                boolean tileFound = false;

                int row = 0;
                int column = 0;

                while(!tileFound) {
                    if(clickPoint.getX() > cellSize * (column + 1)) {
                        column++;
                    }

                    else if(clickPoint.getY() > cellSize * (row + 1)) {
                        row ++;
                    }

                    else {
                        tileFound = true;
                        cellClicked = cells[column][row];
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

    private void setUpGameCells() {
        cells = new GameCell[columns][rows];

        /*for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                cells[i][n] = new GameCell(GameCell.Owner.Neutral, new Point(i * cellSize, n * cellSize));
            }
        }*/
        for(int i = 0; i < cells[0].length; i++) {
            cells[0][i] = new GameCell(GameCell.Owner.Water, new Point(0, i * cellSize));
        }

        for(int i = 0; i < cells[columns - 1].length; i++) {
            cells[columns - 1][i] = new GameCell(GameCell.Owner.Water, new Point((columns - 1) * cellSize, i * cellSize));
        }

        for(int i = 1; i < columns - 1; i++) {
            cells[i][0] = new GameCell(GameCell.Owner.Water, new Point(i * cellSize, 0));
            cells[i][rows - 1] = new GameCell(GameCell.Owner.Water, new Point(i * cellSize, (rows - 1) * cellSize));
        }
    }

    public GameCell[][] getCells() {
        return cells;
    }

    public void paintComponent(Graphics g){
        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                if(cells[i][n] != null) {
                    cells[i][n].drawCell(g);
                }
            }
        }
    }
}
