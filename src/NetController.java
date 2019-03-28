import AI.*;

/**
 * @author TylerWilson
 */
public class NetController {
    private NeuralNet neuralNet;
    private InputController inputController;

    // Map Size : 20 x 20 cells

    // 1 input : What team is the AI
    // 4 inputs : How many troops each player gets per turn
    // 400 inputs : Who owns which cell
    // 400 inputs : How many troops on a cell
    // 400 inputs : which tile is selected
    // 1 input : is selecting the number of troops?

    // 400 outputs : select a tile
    // 1 output : getNumTroops
    // 1 output : end turn

    //private int numCells = 400;

    private int numInputs = 1206;
    private int hiddenLayers = 2;
    private int numHidden = 1500;
    private int numOutputs = 402;

    private float mutationRate = (float) 0.1;

    private Player player;
    private GameCell.Owner owner;
    private GameLogic logic;

    /**
     * This creates a new, empty AI to start training
     * @param owner the team that the AI is controlling
     * @param logic a copy of the game logic
     */
    NetController(GameCell.Owner owner, GameLogic logic) {
        neuralNet = new NeuralNet(numInputs, hiddenLayers, numHidden, numOutputs, mutationRate);
        player = new Player(owner, logic);

        this.owner = owner;
        this.logic = logic;
    }

    /**
     * This constructor makes a copy of a parent neural network, and then mutates it to be slightly different
     *
     * @param parent the network this controllers neural network is copied after
     */
    NetController(NetController parent) {
        neuralNet = new NeuralNet(parent.neuralNet);
        neuralNet.Mutate();
        player = new Player(parent.owner, parent.logic);

        this.owner = parent.owner;
    }

    /**
     * This creates a neural network from saved values
     *
     * @param weights the weights that will be used to create the network
     * @param owner the team the AI is controlling
     * @param logic a copy of the game logic
     */
    NetController(float[][][] weights, GameCell.Owner owner, GameLogic logic) {
        neuralNet = new NeuralNet(weights);
        player = new Player(owner, logic);

        this.owner = owner;
    }

    /**
     * This gets the AIs move for its current turn
     *
     * @return what the chosen move was (out of an array of possible moves represented by booleans)
     */
    private boolean[] getMove() {
        neuralNet.setInputs(getInputs());

        boolean[] moveChosen = new boolean[numOutputs];
        for (boolean move : moveChosen) {
            move = false;
        }
        moveChosen[neuralNet.getOutput()] = true;

        return moveChosen;
    }

    /**
     *This gets all the inputs for the neural network to make a decision
     *
     * @return
     */
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

    /**
     * The function that is called each turn
     */
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
