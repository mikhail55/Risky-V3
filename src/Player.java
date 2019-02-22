
public class Player {

    GameCell.Owner team;

    GameCell lastTileChecked;

    GameLogic logic;

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

    public void move(GameCell from, GameCell to, int numTroops){
        logic.move(from, to, numTroops);
    }

    public void attack(GameCell attacker, GameCell defender, int numTroops){
        logic.attack(attacker, defender, numTroops);
    }

    public  void deploy(GameCell selectedCell){
        logic.deploy(selectedCell);
    }

    public GameCell getLastTileChecked() {
        return lastTileChecked;
    }

    public void setLastTileChecked(GameCell lastTileChecked) {
        this.lastTileChecked = lastTileChecked;
    }
}
