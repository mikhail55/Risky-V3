import java.awt.*;

/**
 * @author Tyler & Mikhail
 */
public class GameCell {

    private int numTroops;
    private int addedTroops;

    enum Owner{
     Team1, Team2, Team3, Team4, Water, Neutral
    }
    private Owner owner;
    private Owner ownerEndTurn;
    private Point coordinates;

    private boolean isWater;

    public GameCell(Owner team, Point coordinates) {
        owner = team;
        ownerEndTurn = owner;
        this.coordinates = coordinates;

        if(owner == Owner.Water) {
            isWater = true;
        }
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

    public void setOwnerEndTurn(Owner ownerEndTurn) {
        this.ownerEndTurn = ownerEndTurn;
    }

    public int getAddedTroops() {
        return addedTroops;
    }

    public Owner getOwnerEndTurn() {
        return ownerEndTurn;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void drawCell(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(coordinates.x, coordinates.y, 40, 40);

        if(owner == Owner.Water) {
            g.setColor(Color.blue);
        } else if(owner == Owner.Neutral) {
            g.setColor(Color.lightGray);
        }

        g.fillRect(coordinates.x + 1, coordinates.y + 1, 39, 39);
    }
}
