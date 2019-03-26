import java.awt.*;

/**
 * @author Mikhail
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
        } else if (owner == Owner.Neutral) {
            numTroops = 2;
        } else {
            numTroops = 5;
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


    /**
     * @author Tyler
     * @param g the graphics component
     */
    public void drawCell(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(coordinates.x, coordinates.y, 40, 40);

        if(owner == Owner.Water) {
            g.setColor(Color.blue);
        } else if(owner == Owner.Neutral) {
            g.setColor(Color.lightGray);
        } else if(owner == Owner.Team1) {
            g.setColor(Color.RED);
        } else if(owner == Owner.Team2) {
            g.setColor(Color.MAGENTA);
        } else if(owner == Owner.Team3) {
            g.setColor(Color.GREEN);
        } else if(owner == Owner.Team4) {
            g.setColor(Color.YELLOW);
        }

        g.fillRect(coordinates.x + 1, coordinates.y + 1, 39, 39);


        g.setColor(Color.black);
        if(!isWater) {
            g.drawString(Integer.toString(numTroops), coordinates.x + 16, coordinates.y + 26);
        }
    }
}
