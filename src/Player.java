import javax.swing.*;

/**
 * @author Mikhail Pyatakhin, Tyler Wilson
 * @version 1.0
 * @since 2019-03-28
 */
public class Player {

    GameCell.Owner team;

    GameCell lastTileChecked;

    GameLogic logic;

    private int deployableTroops;

    private int troopsPerTurn = 0;

    public Player(GameCell.Owner player, GameLogic logic) {
        team = player;
        this.logic = logic;
    }

    /**
     * checks if the owners of the tiles are the same
     * @author Mikhail Pyatakhin
     * @param x
     * @param y
     * @return
     */
    public boolean checkTile(GameCell x, GameCell y){
        if (x.getOwner() == y.getOwner()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @author Tyler Wilson
     */
    private int getNumTroops() {
        int numTroops = 1;
        boolean done = false;

        while (!done) {
            done = true;
            try {
                numTroops = Integer.parseInt(JOptionPane.showInputDialog("How many troops would you like to use?"));
            } catch (Exception e) {
                done = false;
            }

            if(numTroops < 0) {
                done = false;
            }
        }

        return numTroops;
    }

    /**
     * Checks which tile was clicked and decides what to do
     * @author Mikhail Pyatakhin
     * @param clicked the tile that was last clicked
     * @param numTroops number of troops that was selected
     */
    public void tileClicked(GameCell clicked, int numTroops){
        if (!(lastTileChecked == null)){
            //clicked the tile with the same owner = move
            if (lastTileChecked.getOwner() == clicked.getOwner()){
                logic.move(getLastTileChecked(), clicked, numTroops);
                //reset the selected tile
                lastTileChecked = null;
            }
            //clicked tile with a different owner = attack
            else if (!(lastTileChecked.getOwner() == clicked.getOwner())){
                logic.attack(lastTileChecked, clicked, numTroops);
                lastTileChecked = null;
            }
            //clicked the same tile twice = deploy
            else if (lastTileChecked == clicked) {
                logic.deploy(clicked);
                lastTileChecked = null;
            }
        } else {lastTileChecked = clicked;}
    }

    public void endTurn(){
        logic.endTurn();
    }

    public GameCell getLastTileChecked() {
        return lastTileChecked;
    }

    public int getDeployableTroops() { return deployableTroops; }

    public void setDeployableTroops(int deployableTroops) {
        this.deployableTroops = deployableTroops;
    }

    public int getTroopsPerTurn() {
        return troopsPerTurn;
    }

    public void setLastTileChecked(GameCell lastTileChecked) {
        this.lastTileChecked = lastTileChecked;
    }
}
