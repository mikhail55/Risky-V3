/**
 * this class is designed to be used in combination with the other classes included in the Risky Game folder
 * this specific class is in charge of managing the cells and the menu.
 *
 * @author Rafael Rios
 *
 * v2.0 since 1.0
 */

import javax.swing.*;
        import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameBoard extends JPanel{


    private GameCell[] gameCells = new GameCell[400];

    //enum User {
    //    Team1,Team2 ,Team3, Team4,
    //}

    /**
     * this User enum variable is used to decide what the background color should be depending on the active user
     */
    //private User activeUser;

    private GameCell[] provinces;

    //private Color color;

    private GameLogic logic;

    private Menu menu;

    int cellSize = 26;

    public GameBoard() {
        //activeUserBackColor();
        //setBackground(color);
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the point where the mouse was clicked
                Point pressedPoint = e.getPoint();
                Player currentPlayer = logic.getCurrentPlayer();
                // This whole bit just goes through all the cells to find which one was clicked based on the click point
                boolean cellFinder = false;

                GameCell[][] cells2D = new GameCell[20][20];

                for(int i = 0; i < cells2D.length; i++) {
                    for(int n = 0; n < cells2D[i].length; n++) {
                        cells2D[i][n] = gameCells[(i * 20) + n];
                    }
                }

                // This whole bit just goes through all the cells to find which one was clicked based on the click point
                GameCell cellClicked = cells2D[0][0];

                boolean tileFound = false;

                int row = 0;
                int column = 0;

                while(!tileFound) {
                    if(pressedPoint.getX() > cellSize * (column + 1)) {
                        column++;
                    }

                    else if(pressedPoint.getY() > cellSize * (row + 1)) {
                        row ++;
                    }

                    else {
                        tileFound = true;
                        cellClicked = cells2D[column][row];
                    }
                }

                // Tell the player which tile was clicked
                currentPlayer.tileClicked(cellClicked);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });


        /**
         * this nested for loop is used to give the positions of the cells within the panel
         */
        int x = 10;
        int y = 10;
        int z = 0;
        for (int i=0; i < 20; i++) {
            for (int f = 0; f < 20; f++) {
                gameCells[z] = new GameCell(GameCell.Owner.notTaken, 5, new Point(x,y),false);
                x += 27;
                z+=1;
            }
            y+=27;
            x=10;
        }
        waterCells();

        //for demonstration purposes only, this is the syntax to create the different colors for the cells
        gameCells[0].setOwnerEndTurn(GameCell.Owner.Team1);
        gameCells[0].setNumTroops(10);
        gameCells[0].update();
        gameCells[19].setOwnerEndTurn(GameCell.Owner.Team2);
        gameCells[19].setNumTroops(10);
        gameCells[19].update();
        gameCells[399].setOwnerEndTurn(GameCell.Owner.Team3);
        gameCells[399].setNumTroops(10);
        gameCells[399].update();
        gameCells[380].setOwnerEndTurn(GameCell.Owner.Team4);
        gameCells[380].setNumTroops(10);
        gameCells[380].update();
        //demonstration ends here

    }


    public void paintComponent (Graphics g) {

        for (int i = 0; i < 400; i++) {
            gameCells[i].cellDraw(g);
        }
        averagesDraw(g);
    }

    /**
     * this function asks what user is active in order to know what color the background should be
     * @param user used to know who the active user is
     */
    /*public void setActiveUser(User user){
        this.activeUser = user;
    }*/

    /**
     * this is the function in charge of actually deciding what color the background should be depending on
     * who the user is
     */
    /*private void activeUserBackColor (){
        if (activeUser == User.Team1){
            color = Color.DARK_GRAY;
        }
        else if (activeUser == User.Team2){
            color = Color.GREEN;
        }
        else if (activeUser == User.Team3){
            color = Color.orange;
        }
        else if (activeUser == User.Team4){
            color = Color.pink;
        }
    }*/

    public GameCell[] getCells() {
        return gameCells;
    }

    /**
     * this function defines all the cells that should be water
     */
    private void waterCells(){
        for (int x=1; x < 18;x++){
            gameCells[x].setWater(true);
        }//first line

        for (int x=22; x < 36;x++){
            gameCells[x].setWater(true);
        }//second line

        for (int x=44; x < 55;x++){
            gameCells[x].setWater(true);
        }//third line

        for (int x=64; x < 76;x++){
            gameCells[x].setWater(true);
        }//fourth line

        for (int x=85; x < 96;x++){
            gameCells[x].setWater(true);
        }//fifth line

        for (int x=106; x < 113;x++){
            gameCells[x].setWater(true);
        }//sixth line

        for (int x=164; x < 176;x++){
            gameCells[x].setWater(true);
        }//eighth line

        for (int x=183; x < 197;x++){
            gameCells[x].setWater(true);
        }//ninth line

        for (int x=202; x < 217;x++){
            gameCells[x].setWater(true);
        }//tenth line

        for (int x=222; x < 238;x++){
            gameCells[x].setWater(true);
        }//eleventh line

        for (int x=243; x < 258;x++){
            gameCells[x].setWater(true);
        }//twelfth line

        for (int x=264; x < 277;x++){
            gameCells[x].setWater(true);
        }//thirteenth line

        for (int x=284; x < 297;x++){
            gameCells[x].setWater(true);
        }//fourteenth line

        for (int x=305; x < 315;x++){
            gameCells[x].setWater(true);
        }//fifteenth line

        for (int x=325; x < 335;x++){
            gameCells[x].setWater(true);
        }//sixteenth line

        for (int x=346; x < 354;x++){
            gameCells[x].setWater(true);
        }//eighteenth line

    }

    /**
     * this method is used to show the average of the board that each player has
     * @return
     */
    private String CellAvgCalculator (){
        int player1Cells = 0;
        int player2Cells = 0;
        int player3Cells = 0;
        int player4Cells = 0;
        int notTakenCells = 0;

        GameCell.Owner cellOwner;

        for (int i = 0; i<400; i++){
           cellOwner  = gameCells[i].getOwner();

           if (cellOwner == GameCell.Owner.Team1){
               player1Cells++;
           }
           if (cellOwner == GameCell.Owner.Team2){
               player2Cells++;
           }
           if (cellOwner == GameCell.Owner.Team3){
               player3Cells++;
           }
           if (cellOwner == GameCell.Owner.Team4){
               player4Cells++;
           }
           else {
               notTakenCells++;
           }
        }

        String finalAverages = "Cells owned by player 1: "+((player1Cells*100)/400)+"\n" +
                "Cells owned by player 2:"+((player2Cells*100)/400)+"\n" +
                "Cells owned by player 3: "+((player3Cells*100)/400)+"\n"+
                "Cells owned by player 4: "+((player4Cells*100)/400)+"\n"+
                "Cells Not Owned: "+((notTakenCells*100)/400)+"\n";

        return finalAverages;
    }

    private void averagesDraw (Graphics page){
        String averages = CellAvgCalculator();
        page.setColor(Color.BLACK);
        page.drawString(averages, 500, 20);
    }

    public void setLogic(GameLogic logic) {
        this.logic = logic;
    }
}

