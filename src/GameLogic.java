
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

    public void move(GameCell from, GameCell to, int numTroops){
        if (currentPlayer.checkTile(from, to)){
            from.setAddedTroops(-numTroops);

            to.setAddedTroops(numTroops);
        }
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
