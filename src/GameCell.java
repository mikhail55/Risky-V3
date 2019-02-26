/**
 * Header this is a header
 */

import java.awt.*;

public class GameCell {

    private int numTroops;
    private int addedTroops;

    /**
     * enum is used to dictate what team the cell belongs to
     */
    enum Owner{
     Team1, Team2, Team3, Team4, Water,
    }
    private Owner owner;
    private Owner ownerEndTurn;
    private Point coordinates;

    private boolean isWater;

    /**
     * This constructor is dedicated to requesting the values required to initialize a cell
     * @param cellOwner this parameter asks whether the cell belongs to any team, or if it is water
     * @param numTroops this parameter initializes the number of troops that each cell will have
     */
    public GameCell(Owner cellOwner, int numTroops) {
        this.owner = cellOwner;
        this.numTroops = numTroops;
    }

    public void update(){
        owner = ownerEndTurn;

        numTroops += addedTroops;
        addedTroops = 0;
    }

    public int getNumTroops() {
        return numTroops;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isWater() {
        return isWater;
    }

    public void setNumTroops(int numTroops) {
        this.numTroops = numTroops;
    }

    public void setAddedTroops(int addedTroops) {
        this.addedTroops = addedTroops;
    }

    public void setOwnerEndTurn(Owner OwnerEndTurn) {
        this.ownerEndTurn = OwnerEndTurn;
        ownerEndTurn = owner;
    }

    public Point getCoordinates() {
        return coordinates;
    }
}
