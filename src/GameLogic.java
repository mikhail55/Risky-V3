import java.util.ArrayList;
import java.util.Random;

public class GameLogic {

    private Player currentPlayer;

    private Player[] players;

    private RiskyFileReader fileReader;

    private GameBoard gameBoard;

    public GameLogic() {

    }

    public void setGameBoard(GameBoard board) {
        gameBoard = board;
    }

    //moves a set amount of troops from one tile to another if possible
    public void move(GameCell from, GameCell to, int numTroops){
        if (currentPlayer.checkTile(from, to) && checkAdjacent(from, to) && checkAllowed(from)){
            from.setAddedTroops(-numTroops);

            to.setAddedTroops(numTroops);
        }
    }

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

//                        defender.setAddedTroops(-1);
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

    private int rollDice(){
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    public GameBoard getBoard() {
        return gameBoard;
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
        for (int i = 0; i < gameBoard.getCells().length; i++){
            for (int j = 0; j < gameBoard.getCells()[i].length; j++){
                //updates each tile at the end of each turn
                gameBoard.getCells()[i][j].update();

                if (gameBoard.getCells()[i][j].getOwner() == currentPlayer.team){
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

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        currentPlayer = players[0];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
