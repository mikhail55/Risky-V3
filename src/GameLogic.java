import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private GameCell[][] board;

    private Player currentPlayer;

    private Player[] players;

    private RiskyFileReader fileReader;

    public GameLogic(GameCell[][] madeBoard, Player[] players) {
        board = madeBoard;

        this.players = players;

        currentPlayer = players[0];
    }

    //moves a set amount of troops from one tile to another if possible
    public void move(GameCell from, GameCell to, int numTroops){
        if (currentPlayer.checkTile(from, to) && checkAdjacent(from, to) && checkAllowed(from)){
            from.setAddedTroops(-numTroops);

            to.setAddedTroops(numTroops);
        }
    }

    public void attack(GameCell attacker, GameCell defender, int numTroops){
        if (!currentPlayer.checkTile(attacker, defender) && checkAdjacent(attacker, defender) && checkAllowed(attacker)){

            int killed = 0;

            ArrayList<Integer> rolledNumAtt = new ArrayList();
            ArrayList<Integer> rolledNumDef = new ArrayList();

            // roll the dice for each defending regiment
            for (int i = 0; i < defender.getNumTroops(); i ++){
                rolledNumAtt.add(rollDice());
            }

            //roll the dice for each attacking regiment(minus one)
            for (int i = 0; i < numTroops - 1; i++){
                rolledNumAtt.add(rollDice());
            }

            //for each rolled defender dice checks the attacker rolls
            for (int i = 0; i < rolledNumDef.size(); i++){
                for (int j = 0; j < rolledNumAtt.size(); j++){
                    //if there are any attacker rolls that are higher than the given defender roll, kills on of the defenders
                    if (rolledNumAtt.get(j) > rolledNumDef.get(i)){
                        //that attacker roll is removed
                        rolledNumAtt.remove(j);

                        defender.setAddedTroops(-1);
                        killed++;
                    }
                }
            }

            //if the attacker did not kill all of the defenders, removes one attacker for each defender alive
            if (killed != defender.getNumTroops()){
                attacker.setAddedTroops(defender.getNumTroops() - killed);
            }

        }
    }

    private int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    //deploys one troop on a selected tile
    public  void deploy(GameCell selectedCell){
        selectedCell.setNumTroops(selectedCell.getNumTroops()+1);
    }

    //checks if two tiles are adjacent to each other
    private boolean checkAdjacent(GameCell origin, GameCell destination){

        if ((destination.getCoordinates().x == origin.getCoordinates().x+1) || (destination.getCoordinates().x == origin.getCoordinates().x+1)){
            return true;
        } else if ((destination.getCoordinates().y == origin.getCoordinates().y+1) || (destination.getCoordinates().y == origin.getCoordinates().y - 1)){
            return true;
        }

        return false;
    }

    //checks if the player is allowed to perform actions with a selected tile
    private boolean checkAllowed(GameCell selected){
        if (selected.getOwner() == currentPlayer.team){
            return true;
        }
        return false;
    }

    public int getEarnedTroops(){
        return 0;
    }

    public void endTurn(){
        setNewPlayer();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                //updates each tile at the end of each turn
                board[i][j].update();

                if (board[i][j].getOwner() == currentPlayer.team){
                    //for each tile the player owned at the start of the turn, adds a troop for him to deploy
                    currentPlayer.setDeployableTroops(currentPlayer.getDeployableTroops()+1);
                }
            }
        }


    }

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

    public GameCell[][] getBoard() {
        return board;
    }

    public Player[] getPlayers() {
        return players;
    }
}
