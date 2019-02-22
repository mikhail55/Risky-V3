
public class Player {

    GameCell.Owner team;

    GameCell lastTileChecked;

    GameLogic logic;

    int troopsPerTurn = 0;

    public Player(GameCell.Owner player, GameLogic logic) {
        team = player;
        this.logic = logic;
    }

    public boolean checkTile(GameCell x, GameCell y){
        if (x.getOwner() == y.getOwner()){
            return true;
        }
        else{
            return false;
        }
    }

    public void tileClicked(GameCell clicked){

    }

    public GameCell getLastTileChecked() {
        return lastTileChecked;
    }

    public int getTroopsPerTurn() {
        return troopsPerTurn;
    }

    public void setLastTileChecked(GameCell lastTileChecked) {
        this.lastTileChecked = lastTileChecked;
    }
}
