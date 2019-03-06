
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

        //OUTSIDE
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


        // FIRST COLUMN
        cells[1][1] = new GameCell(GameCell.Owner.Water, new Point(cellSize, cellSize));

        cells[1][2] = new GameCell(GameCell.Owner.Neutral, new Point(cellSize, 2 * cellSize));
        cells[1][3] = new GameCell(GameCell.Owner.Neutral, new Point(cellSize, 3 * cellSize));

        for(int i = 4; i < cells[1].length; i++) {
            cells[1][i] = new GameCell(GameCell.Owner.Water, new Point(cellSize, i * cellSize));
        }


        // SECOND COLUMN
        for(int i = 1; i < 5; i++) {
            cells[2][i] = new GameCell(GameCell.Owner.Neutral, new Point(2 * cellSize, i * cellSize));
        }

        for(int i = 5; i < 11; i++) {
            cells[2][i] = new GameCell(GameCell.Owner.Water, new Point(2 * cellSize, i * cellSize));
        }

        for(int i = 11; i < 14; i++) {
            cells[2][i] = new GameCell(GameCell.Owner.Neutral, new Point(2 * cellSize, i * cellSize));
        }

        for(int i = 14; i < rows - 1 ; i++) {
            cells[2][i] = new GameCell(GameCell.Owner.Water, new Point(2 * cellSize, i * cellSize));
        }


        //THIRD COLUMN
        for(int i = 1; i < 6; i++) {
            cells[3][i] = new GameCell(GameCell.Owner.Neutral, new Point(3 * cellSize, i * cellSize));
        }

        for(int i = 6; i < 10; i++) {
            cells[3][i] = new GameCell(GameCell.Owner.Water, new Point(3 * cellSize, i * cellSize));
        }

        for(int i = 10; i < 15; i++) {
            cells[3][i] = new GameCell(GameCell.Owner.Neutral, new Point(3 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[3][i] = new GameCell(GameCell.Owner.Water, new Point(3 * cellSize, i * cellSize));
        }


        //FOURTH COLUMN
        for(int i = 1; i < 7; i++) {
            cells[4][i] = new GameCell(GameCell.Owner.Neutral, new Point(4 * cellSize, i * cellSize));
        }

        for(int i = 7; i < 9; i++) {
            cells[4][i] = new GameCell(GameCell.Owner.Water, new Point(4 * cellSize, i * cellSize));
        }

        for(int i = 9; i < 15; i++) {
            cells[4][i] = new GameCell(GameCell.Owner.Neutral, new Point(4 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[4][i] = new GameCell(GameCell.Owner.Water, new Point(4 * cellSize, i * cellSize));
        }


        //FIFTH COLUMN
        for(int i = 1; i < 16; i++) {
            cells[5][i] = new GameCell(GameCell.Owner.Neutral, new Point(5 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[5][i] = new GameCell(GameCell.Owner.Water, new Point(5 * cellSize, i * cellSize));
        }


        //SIXTH COLUMN
        cells[6][1] = new GameCell(GameCell.Owner.Water, new Point(6 * cellSize, cellSize));

        for(int i = 2; i < 5; i++) {
            cells[6][i] = new GameCell(GameCell.Owner.Neutral, new Point(6 * cellSize, i * cellSize));
        }

        cells[6][5] = new GameCell(GameCell.Owner.Water, new Point(6 * cellSize, 5 * cellSize));

        for(int i = 6; i < 16 ; i++) {
            cells[6][i] = new GameCell(GameCell.Owner.Neutral, new Point(6 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[6][i] = new GameCell(GameCell.Owner.Water, new Point(6 * cellSize, i * cellSize));
        }


        //SEVENTH COLUMN
        cells[7][1] = new GameCell(GameCell.Owner.Water, new Point(7 * cellSize, cellSize));

        for(int i = 2; i < 4; i++) {
            cells[7][i] = new GameCell(GameCell.Owner.Neutral, new Point(7 * cellSize, i * cellSize));
        }

        for(int i = 4; i < 9; i++) {
            cells[7][i] = new GameCell(GameCell.Owner.Water, new Point(7 * cellSize, i * cellSize));
        }

        for(int i = 9; i < 16; i++) {
            cells[7][i] = new GameCell(GameCell.Owner.Neutral, new Point(7 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[7][i] = new GameCell(GameCell.Owner.Water, new Point(7 * cellSize, i * cellSize));
        }


        //EIGHTH COLUMN
        cells[8][1] = new GameCell(GameCell.Owner.Water, new Point(8 * cellSize, cellSize));

        for(int i = 2; i < 4; i++) {
            cells[8][i] = new GameCell(GameCell.Owner.Neutral, new Point(8 * cellSize, i * cellSize));
        }

        for(int i = 4; i < 11; i++) {
            cells[8][i] = new GameCell(GameCell.Owner.Water, new Point(8 * cellSize, i * cellSize));
        }

        for(int i = 11; i < 15; i++) {
            cells[8][i] = new GameCell(GameCell.Owner.Neutral, new Point(8 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[8][i] = new GameCell(GameCell.Owner.Water, new Point(8 * cellSize, i * cellSize));
        }


        //NINTH COLUMN
        for(int i = 1; i < 4; i++) {
            cells[9][i] = new GameCell(GameCell.Owner.Neutral, new Point(9 * cellSize, i * cellSize));
        }

        for(int i = 4; i < 13; i++) {
            cells[9][i] = new GameCell(GameCell.Owner.Water, new Point(9 * cellSize, i * cellSize));
        }

        for(int i = 13; i < 14; i++) {
            cells[9][i] = new GameCell(GameCell.Owner.Neutral, new Point(9 * cellSize, i * cellSize));
        }

        for(int i = 14; i < rows - 1 ; i++) {
            cells[9][i] = new GameCell(GameCell.Owner.Water, new Point(9 * cellSize, i * cellSize));
        }


        //TENTH COLUMN
        for(int i = 1; i < 4; i++) {
            cells[10][i] = new GameCell(GameCell.Owner.Neutral, new Point(10 * cellSize, i * cellSize));
        }

        for(int i = 4; i < 12; i++) {
            cells[10][i] = new GameCell(GameCell.Owner.Water, new Point(10 * cellSize, i * cellSize));
        }

        for(int i = 12; i < 14; i++) {
            cells[10][i] = new GameCell(GameCell.Owner.Neutral, new Point(10 * cellSize, i * cellSize));
        }

        for(int i = 14; i < rows - 1 ; i++) {
            cells[10][i] = new GameCell(GameCell.Owner.Water, new Point(10 * cellSize, i * cellSize));
        }


        //ELEVENTH COLUMN
        for(int i = 1; i < 5; i++) {
            cells[11][i] = new GameCell(GameCell.Owner.Neutral, new Point(11 * cellSize, i * cellSize));
        }

        for(int i = 5; i < 11; i++) {
            cells[11][i] = new GameCell(GameCell.Owner.Water, new Point(11 * cellSize, i * cellSize));
        }

        for(int i = 11; i < 15; i++) {
            cells[11][i] = new GameCell(GameCell.Owner.Neutral, new Point(11 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[11][i] = new GameCell(GameCell.Owner.Water, new Point(11 * cellSize, i * cellSize));
        }


        //TWELFTH COLUMN
        for(int i = 1; i < 6; i++) {
            cells[12][i] = new GameCell(GameCell.Owner.Neutral, new Point(12 * cellSize, i * cellSize));
        }

        for(int i = 6; i < 10; i++) {
            cells[12][i] = new GameCell(GameCell.Owner.Water, new Point(12 * cellSize, i * cellSize));
        }

        for(int i = 10; i < 16; i++) {
            cells[12][i] = new GameCell(GameCell.Owner.Neutral, new Point(12 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[12][i] = new GameCell(GameCell.Owner.Water, new Point(12 * cellSize, i * cellSize));
        }


        //THIRTEENTH COLUMN
        for(int i = 1; i < 6; i++) {
            cells[13][i] = new GameCell(GameCell.Owner.Neutral, new Point(13 * cellSize, i * cellSize));
        }

        for(int i = 6; i < 8; i++) {
            cells[13][i] = new GameCell(GameCell.Owner.Water, new Point(13 * cellSize, i * cellSize));
        }

        for(int i = 8; i < 16; i++) {
            cells[13][i] = new GameCell(GameCell.Owner.Neutral, new Point(13 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[13][i] = new GameCell(GameCell.Owner.Water, new Point(13 * cellSize, i * cellSize));
        }


        //FOURTEENTH COLUMN
        for(int i = 1; i < 16; i++) {
            cells[14][i] = new GameCell(GameCell.Owner.Neutral, new Point(14 * cellSize, i * cellSize));
        }

        for(int i = 16; i < rows - 1 ; i++) {
            cells[14][i] = new GameCell(GameCell.Owner.Water, new Point(14 * cellSize, i * cellSize));
        }


        //FIFTEENTH COLUMN
        for(int i = 1; i < 6; i++) {
            cells[15][i] = new GameCell(GameCell.Owner.Neutral, new Point(15 * cellSize, i * cellSize));
        }

        for(int i = 6; i < 8; i++) {
            cells[15][i] = new GameCell(GameCell.Owner.Water, new Point(15 * cellSize, i * cellSize));
        }

        for(int i = 8; i < 10; i++) {
            cells[15][i] = new GameCell(GameCell.Owner.Neutral, new Point(15 * cellSize, i * cellSize));
        }

        cells[15][10] = new GameCell(GameCell.Owner.Water, new Point(15 * cellSize, 10 * cellSize));

        for(int i = 11; i < 15; i++) {
            cells[15][i] = new GameCell(GameCell.Owner.Neutral, new Point(15 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[15][i] = new GameCell(GameCell.Owner.Water, new Point(15 * cellSize, i * cellSize));
        }


        //SIXTEENTH COLUMN
        cells[16][1] = new GameCell(GameCell.Owner.Water, new Point(16 * cellSize, cellSize));

        for(int i = 2; i < 5; i++) {
            cells[16][i] = new GameCell(GameCell.Owner.Neutral, new Point(16 * cellSize, i * cellSize));
        }

        for(int i = 5; i < 11; i++) {
            cells[16][i] = new GameCell(GameCell.Owner.Water, new Point(16 * cellSize, i * cellSize));
        }

        for(int i = 11; i < 15; i++) {
            cells[16][i] = new GameCell(GameCell.Owner.Neutral, new Point(16 * cellSize, i * cellSize));
        }

        for(int i = 15; i < rows - 1 ; i++) {
            cells[16][i] = new GameCell(GameCell.Owner.Water, new Point(16 * cellSize, i * cellSize));
        }


        //SEVENTEENTH COLUMN
        cells[17][1] = new GameCell(GameCell.Owner.Water, new Point(17 * cellSize, cellSize));

        for(int i = 2; i < 4; i++) {
            cells[17][i] = new GameCell(GameCell.Owner.Neutral, new Point(17 * cellSize, i * cellSize));
        }

        for(int i = 4; i < 12; i++) {
            cells[17][i] = new GameCell(GameCell.Owner.Water, new Point(17 * cellSize, i * cellSize));
        }

        cells[17][12] = new GameCell(GameCell.Owner.Neutral, new Point(17 * cellSize, 12 * cellSize));

        for(int i = 13; i < rows - 1 ; i++) {
            cells[17][i] = new GameCell(GameCell.Owner.Water, new Point(17 * cellSize, i * cellSize));
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
