import AI.*;

public class NetController {
    private NeuralNet neuralNet;
    private InputController inputController;

    // Map Size : 19 x 17 cells

    // 1 input : What team is the AI
    // 4 inputs : How many troops each player gets per turn
    // 323 inputs : Who owns which cell
    // 323 inputs : How many troops on a cell

    // 323 outputs : select a tile
    // 1 output : end turn

    private int numCells = 323;

    private int numInputs = 651;
    private int hiddenLayers = 2;
    private int numHidden = 675;
    private int numOutputs = 323;
    private double mutationRate = 0.1;

    private Player player;
    private GameCell.Owner owner;
    private GameLogic logic;

    NetController(GameCell.Owner owner, GameLogic logic) {
        neuralNet = new NeuralNet(numInputs, hiddenLayers, numHidden, numOutputs, mutationRate);
        player = new Player(owner, logic);

        this.owner = owner;
        this.logic = logic;
    }

    NetController(NetController parent) {
        neuralNet = new NeuralNet(parent.neuralNet);
        neuralNet.Mutate();
        player = new Player(parent.owner, parent.logic);

        this.owner = parent.owner;
    }

    private boolean[] getMove() {
        neuralNet.setInputs(getInputs());

        boolean[] moveChosen = new boolean[numOutputs];
        for (boolean move : moveChosen) {
            move = false;
        }
        moveChosen[neuralNet.getOutput()] = true;

        return moveChosen;
    }

    private double[] getInputs() {
        double[] inputs = new double[numInputs];

        int posInInput = 0;

        inputs[posInInput] = owner.ordinal() + 1;
        posInInput ++;

        for (int i = 0; i < 3; i++) {
            inputs[posInInput] = logic.getPlayers()[i].getTroopsPerTurn();
            posInInput++;
        }

        for(int i = 0; i < logic.getBoard().length; i++) {
            for(int n = 0; n < logic.getBoard()[i].length; n++) {
                inputs[posInInput] = logic.getBoard()[i][n].getOwner().ordinal();
                posInInput ++;
            }
        }

        for(int i = 0; i < logic.getBoard().length; i++) {
            for(int n = 0; n < logic.getBoard()[i].length; n++) {
                inputs[posInInput] = logic.getBoard()[i][n].getNumTroops();
                posInInput ++;
            }
        }

        return inputs;
    }

    public void turn() {
        boolean[] moves = getMove();

        for(int i = 0; i < moves.length - 1; i++) {
            if(moves[i]) {
                boolean rowFound = false;
                int cellRow = 0;
                int cellPosInRow;

                while(!rowFound) {
                    if(((cellRow + 1) * logic.getBoard()[0].length - 1) < i) {
                        cellRow++;
                    } else {
                        rowFound = true;
                    }
                }

                cellPosInRow = i - (cellRow * logic.getBoard()[0].length);

                player.tileClicked(logic.getBoard()[cellRow][cellPosInRow]);
            }
        }
        player.endTurn();

    }
}
