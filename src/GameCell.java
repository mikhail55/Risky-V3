public class GameCell {

    private int numTroops;

    enum Owner{
     Team1, Team2, Team3, Team4
    }
    private Owner owner;
    private Owner ownerEndTurn;

    private boolean isWater;
    private int addedTroops;

}
