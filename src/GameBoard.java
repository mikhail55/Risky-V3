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

public class GameBoard extends JPanel{


    private GameCell [] gameCells = new GameCell[400];

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

    public GameBoard() {
        //activeUserBackColor();
        //setBackground(color);



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
        gameCells[0].update();
        gameCells[19].setOwnerEndTurn(GameCell.Owner.Team2);
        gameCells[19].update();
        gameCells[399].setOwnerEndTurn(GameCell.Owner.Team3);
        gameCells[399].update();
        gameCells[380].setOwnerEndTurn(GameCell.Owner.Team4);
        gameCells[380].update();
        //demonstration ends here

    }


    public void paintComponent (Graphics g) {

        for (int i = 0; i < 400; i++) {
            gameCells[i].cellDraw(g);
        }
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
}

