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

    private int numinputs = 651;
    private int hiddenLayers = 2;
    private int numHidden = 675;
    private int numOutputs = 323;
    private double mutationRate = 0.1;

    private Player player;

    NetController() {
        neuralNet = new NeuralNet(numinputs, hiddenLayers, numHidden, numOutputs, mutationRate);
    }

    NetController(NetController parent) {
        neuralNet = new NeuralNet(parent.neuralNet);
        neuralNet.Mutate();
    }

    private boolean[] getMove() {
        boolean[] moveChosen = new boolean[numOutputs];
        for (boolean move : moveChosen) {
            move = false;
        }
        moveChosen[neuralNet.getOutput()] = true;

        return moveChosen;
    }
}
