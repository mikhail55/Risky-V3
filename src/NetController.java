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

    private int numinputs = 651;
    private int hiddenLayers = 2;
    private int numHidden = 675;
    private int numOutputs = 323;
    private double mutationRate = 0.1;

    private Player player;
    private GameCell.Owner owner;
    private GameLogic logic;

    NetController(GameCell.Owner owner, GameLogic logic) {
        neuralNet = new NeuralNet(numinputs, hiddenLayers, numHidden, numOutputs, mutationRate);
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
        double[] inputs = new double[numinputs];

        inputs[0] = owner.ordinal() + 1;

        return inputs;
    }

    public void turn() {

    }
}
