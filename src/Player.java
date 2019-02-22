
public class Player {

    GameCell.Owner team;

    GameCell lastTileChecked;

    public Player(GameCell.Owner player) {
        team = player;
    }

    public boolean checkTile(GameCell x, GameCell y){
        if (x.getOwner() == y.getOwner()){
            return true;
        }
        else{
            return false;
        }
    }

    public GameCell getLastTileChecked() {
        return lastTileChecked;
    }

    public void setLastTileChecked(GameCell lastTileChecked) {
        this.lastTileChecked = lastTileChecked;
    }
}
