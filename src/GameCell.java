public class GameCell {

    private int numTroops;
    private int addedTroops;

    enum Owner{
     Team1, Team2, Team3, Team4
    }
    private Owner owner;
    private Owner ownerEndTurn;

    private boolean isWater;

    public GameCell() {
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
}
