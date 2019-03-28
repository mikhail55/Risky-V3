import AI.*;

/**
 * @author TylerWilson
 */
public class NetController {
    private NeuralNet neuralNet;
    private InputController inputController;

    // Map Size : 19 x 17 cells

    // 1 input : What team is the AI
    // 4 inputs : How many troops each player gets per turn
    // 323 inputs : Who owns which cell
    // 323 inputs : How many troops on a cell
    // 323 inputs : which tile is selected
    // 1 input : is selecting the number of troops?

    // 323 outputs : select a tile
    // 1 output : getNumTroops
    // 1 output : end turn

    //private int numCells = 323;

    private int numInputs = 975;
    private int hiddenLayers = 2;
    private int numHidden = 1000;
    private int numOutputs = 325;

    /*private int numInputs = 4;
    private int hiddenLayers = 5;
    private int numHidden = 6;
    private int numOutputs = 4;*/

    private float mutationRate = (float) 0.1;

    private Player player;
    private GameCell.Owner owner;
    private GameLogic logic;

    // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING
    // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING

    NetController() {
        neuralNet = new NeuralNet(numInputs, hiddenLayers, numHidden, numOutputs, mutationRate);
    }

    NetController(float[][][] weights) {
        neuralNet = new NeuralNet(weights);
    }

    // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING
    // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING // ONLY FOR TESTING


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

    NetController(float[][][] weights, GameCell.Owner owner, GameLogic logic) {
        neuralNet = new NeuralNet(weights);
        player = new Player(owner, logic);

        this.owner = owner;
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

    private float[] getInputs() {
        float[] inputs = new float[numInputs];

        int posInInput = 0;

        GameBoard board = logic.getBoard();

        inputs[posInInput] = owner.ordinal() + 1;
        posInInput ++;

        for (int i = 0; i < 3; i++) {
            inputs[posInInput] = logic.getPlayers()[i].getTroopsPerTurn();
            posInInput++;
        }

        GameCell[][] cells = new GameCell[20][20];

        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                cells[i][n] = board.getCells()[(i * 20) + n];
            }
        }

        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                inputs[posInInput] = cells[i][n].getOwner().ordinal();
                posInInput ++;
            }
        }

        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                inputs[posInInput] = cells[i][n].getNumTroops();
                posInInput ++;
            }
        }

        return inputs;
    }

    public void turn() {
        boolean[] moves = getMove();

        GameBoard board = logic.getBoard();

        GameCell[][] cells = new GameCell[20][20];

        for(int i = 0; i < cells.length; i++) {
            for(int n = 0; n < cells[i].length; n++) {
                cells[i][n] = board.getCells()[(i * 20) + n];
            }
        }

        for(int i = 0; i < moves.length - 1; i++) {
            if(moves[i]) {
                boolean rowFound = false;
                int cellRow = 0;
                int cellPosInRow;

                while(!rowFound) {
                    if(((cellRow + 1) * cells[0].length - 1) < i) {
                        cellRow++;
                    } else {
                        rowFound = true;
                    }
                }

                cellPosInRow = i - (cellRow * cells[0].length);

                int numTroops = neuralNet.getNumTroops();

                player.tileClicked(cells[cellRow][cellPosInRow], numTroops);
            }
        }
        player.endTurn();

    }

    public float[][][] save() {
        return neuralNet.getWeights();
    }
}
