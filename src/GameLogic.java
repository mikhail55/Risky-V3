public class GameLogic {

    private GameCell[][] board;

    private GameCell.Owner currentPlayer;

    private GameCell.Owner[] players;

    private RiskyFileReader fileReader;

    public GameLogic() {
    }

    public void move(GameCell from, GameCell to, int numTroops){
    }

    public void attack(GameCell attacker, GameCell defender, int numTroops){

    }

    public  void deploy(GameCell selectedCell){

    }

    private boolean checkAdjacent(GameCell origin, GameCell destination){
        return true;
    }

    public int getEarnedTroops(){
        return 0;
    }

    public void endTurn(){

    }

    public GameCell[][] getBoard() {
        return board;
    }
}
