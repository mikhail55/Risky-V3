import java.util.ArrayList;
import java.util.Random;

/**
 * This class handles the logic of the game
 * @author Mikhail Pyatakhin
 * @version 1.0
 * @since 2019-03-28
 */

public class GameLogic {

    private Player currentPlayer;

    private Player[] players;

    private GameBoard gameBoard;

    public GameLogic() {
    }

    public void setGameBoard(GameBoard board) {
        gameBoard = board;
    }

    /**
     * moves a set amount of troops from one tile to another if possible
     * @param from tile that the troops are moved from
     * @param to tile that the troops are moved to
     * @param numTroops the amount of troops that are being pushed
     */
    public void move(GameCell from, GameCell to, int numTroops){
        if (currentPlayer.checkTile(from, to) && checkAdjacent(from, to) && checkAllowed(from)){
            from.setAddedTroops(-numTroops);

            to.setAddedTroops(numTroops);
        }
    }

    /**
     * This method is used when attacking the enemy
     * @param attacker the cell that the troops attack from
     * @param defender the cell that defends from the attackers
     * @param numTroops the number of troops that attack
     */
    public void attack(GameCell attacker, GameCell defender, int numTroops){
        if (!currentPlayer.checkTile(attacker, defender) && checkAdjacent(attacker, defender) && checkAllowed(attacker) && attacker.getNumTroops() > defender.getNumTroops()){

            System.out.println("Attacker: " + attacker.getOwner());
            System.out.println("Defender: " + defender.getOwner());

            int killedAtt = 0;
            int killedDef = 0;

            ArrayList<Integer> rolledNumAtt = new ArrayList();
            ArrayList<Integer> rolledNumDef = new ArrayList();

            // roll the dice for each defending regiment
            for (int i = 0; i < defender.getNumTroops(); i ++){
                rolledNumDef.add(rollDice());
            }
            System.out.println("Defender: " + rolledNumDef);

            //roll the dice for each attacking regiment(minus one)
            for (int i = 0; i < numTroops - 1; i++){
                rolledNumAtt.add(rollDice());
            }
            System.out.println("Attacker: " + rolledNumAtt);

            //for each rolled defender dice checks the attacker rolls
            for (int i = 0; i < rolledNumDef.size(); i++){
                for (int j = 0; j < rolledNumAtt.size(); j++){
                    //if there are any attacker rolls that are higher than the given defender roll, kills one of the defenders
                    if (rolledNumAtt.get(j) > rolledNumDef.get(i)){
                        //that attacker roll is removed
                        rolledNumAtt.remove(j);

                        killedDef++;
                        break;
                    } else if (j == rolledNumAtt.size()-1){
                        killedAtt++;
                    }
                }
            }

            System.out.println("Attackers dead: " + killedAtt);
            System.out.println("Defenders dead: " + killedDef);

            //if the attacker did not kill all of the defenders, removes one attacker for each defender alive
            if (killedDef != defender.getNumTroops()){
                attacker.setAddedTroops(-killedAtt);
                System.out.println("Had att: " + attacker.getNumTroops());
                System.out.println("Added att: " + attacker.getAddedTroops());

            } else {
                defender.setOwnerEndTurn(attacker.getOwner());
                defender.setAddedTroops(numTroops - killedDef);
                System.out.println("Had def: " + defender.getNumTroops());
                System.out.println("Added def: " + defender.getAddedTroops());
            }

            System.out.println("Def owner: " + defender.getOwnerEndTurn());


        }
    }

    /**
     *This method is used to roll a virtual dice
     * @return returns the result for the roll
     */
    private int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public GameBoard getBoard() {
        return gameBoard;
    }

    /**
     * deploys one troop on a selected tile
     * @param selectedCell the cells that the troops are being deployed to
     */
    public  void deploy(GameCell selectedCell){
        selectedCell.setNumTroops(selectedCell.getNumTroops()+1);
    }

    /**
     * checks if two tiles are adjacent to each other
     * @param origin the tile that the troops are moved from
     * @param destination the tile that the troops are being moved to
     * @return returns true if they are and false if they are not
     */
    private boolean checkAdjacent(GameCell origin, GameCell destination){

        if ((destination.getCoordinates().x == origin.getCoordinates().x+1) || (destination.getCoordinates().x == origin.getCoordinates().x+1)){
            return true;
        } else if ((destination.getCoordinates().y == origin.getCoordinates().y+1) || (destination.getCoordinates().y == origin.getCoordinates().y - 1)){
            return true;
        }

        return false;
    }

    /**
     * checks if the player is allowed to perform actions with a selected tile
     * @param selected the tile that is selected
     * @return returns true if the player is the owner and false if he is not
     */
    private boolean checkAllowed(GameCell selected){
        if (selected.getOwner() == currentPlayer.team){
            return true;
        }
        return false;
    }

    /**
     * This function gets called at the end of the turn to update the board
     */
    public void endTurn(){
        setNewPlayer();
        for (int i = 0; i < gameBoard.getCells().length; i++){
                //updates each tile at the end of each turn
                gameBoard.getCells()[i].update();

                if (gameBoard.getCells()[i].getOwner() == currentPlayer.team){
                    //for each tile the player owned at the start of the turn, adds a troop for him to deploy
                    currentPlayer.setDeployableTroops(currentPlayer.getDeployableTroops()+1);
                }
        }


    }

    /**
     * This function changes the currentPlayer at the end of the turn
     */
    private void setNewPlayer(){
        if (currentPlayer == players[0]){
            currentPlayer = players[1];
        }
        else if (currentPlayer == players[1]){
            currentPlayer = players[2];
        }
        else if (currentPlayer == players[2]){
            currentPlayer = players[3];
        }
        else { currentPlayer = players[0];}
    }

    public Player[] getPlayers() { return players; }

    public void setPlayers(Player[] players) {
        this.players = players;
        currentPlayer = players[0];
    }

    public Player getCurrentPlayer() { return currentPlayer; }
}
