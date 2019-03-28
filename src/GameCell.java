/**
 * this class is designed to be used in combination with the other classes included in the Risky Game folder
 * this specific class is in charge of storing the user that owns it, its x and y values, whether its a water cell or
 * not and what color it should be.
 *
 * @author Rafael Rios
 *
 * v2.0 since 1.0
 */

import java.awt.*;

public class GameCell {

    private int numTroops;
    private int addedTroops;

    /**
     * enum is used to dictate what team the cell belongs to (water is used for territory that cannot be captured and
     * notTaken is used for territory that does not belong to anyone)
     */
    enum Owner{
        Team1, Team2, Team3, Team4, notTaken,
    }

    private Owner owner;
    private Owner ownerEndTurn;
    private Point coordinates;

    private boolean isWater;

    /**
     *This variable is used to give a color to each cell depending on who the owner is
     */
    private Color color;

    /**
     * This constructor is dedicated to requesting the values required to initialize a cell
     * @param cellOwner this parameter asks whether the cell belongs to any team, or if it is water
     * @param numTroops this parameter initializes the number of troops that each cell will have
     * @param isWater this parameter asks whether the cell will be playable or if its just part of the background
     */
    public GameCell(Owner cellOwner, int numTroops, Point coordinates, boolean isWater) {
        this.owner = cellOwner;
        this.numTroops = numTroops;
        this.coordinates = coordinates;

    }

    /**
     *This function is called at the end of each players turn, this is where all the changes will take effect
     */
    public void update(){
        owner = ownerEndTurn;
        numTroops += addedTroops;
        addedTroops = 0;
    }

    /** getters and setters */

    public int getNumTroops() {
        return numTroops;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setCoordinates (Point coordinate){
        this.coordinates = coordinate;

    }

    public boolean isWater() { return isWater; }

    public void setNumTroops(int numTroops) {
        this.numTroops = numTroops;
    }

    public void setAddedTroops(int addedTroops) {
        this.addedTroops = addedTroops;
    }

    public int getAddedTroops() {
        return addedTroops;
    }

    public void setOwnerEndTurn(Owner OwnerEndTurn) {
        this.ownerEndTurn = OwnerEndTurn;
    }

    public Owner getOwnerEndTurn() {
        return ownerEndTurn;
    }

    public Point getCoordinates(){
        return coordinates;
    }

    public void setWater(boolean isWater){
        this.isWater = isWater;
    }

    /**
     * this function is the one in charge of painting the cell, and finding out what color it is supposed to be
     * @param page this parameter is used to be able to draw the rectangles
     */
    public void cellDraw (Graphics page){

        if(isWater == false && owner == Owner.notTaken){
            color = Color.GRAY;
        }
        else if(isWater == false){
            userCellColor();
        }
        else if(isWater == true){
            color = Color.CYAN;
        }

        page.setColor(color);
        page.fillRect(coordinates.x, coordinates.y,25,25);

        page.setColor(color.darker());
        page.drawString(numTroops+"",coordinates.x+8,coordinates.y+17);
    }

    /**
     * this function is used to decide what color it should be depending on who its owner is
     */
    private void userCellColor (){
        if (owner == Owner.Team1){
            color = Color.GREEN;
        }
        else if (owner == Owner.Team2){
            color = Color.MAGENTA;
        }
        else if (owner == Owner.Team3){
            color = Color.orange;
        }
        else if (owner == Owner.Team4){
            color = Color.pink;
        }
    }
}
