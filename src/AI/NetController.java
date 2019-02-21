package AI;

public class NetController {
    private NeuralNet neuralNet;

    private int numinputs = 10;
    private int hiddenLayers = 2;
    private int numHidden = 14;
    private int numOutputs = 2;
    private double mutationRate = 0.1;

    NetController() {
        neuralNet = new NeuralNet(numinputs, hiddenLayers, numHidden, numOutputs, mutationRate);
    }

    NetController(NetController parent) {
        neuralNet = new NeuralNet(parent.neuralNet);
        neuralNet.Mutate();
    }

    boolean[] getMove() {
        boolean[] moveChosen = new boolean[numOutputs];
        for (boolean move : moveChosen) {
            move = false;
        }
        moveChosen[neuralNet.getOutput()] = true;

        return moveChosen;
    }
}
