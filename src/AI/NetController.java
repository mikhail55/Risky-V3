package AI;

public class NetController {
    NeuralNet neuralNet;

    int numinputs = 10;
    int hiddenLayers = 2;
    int numHidden = 14;
    int numOutputs = 2;
    double mutationRate = 0.1;

    NetController() {
        neuralNet = new NeuralNet(numinputs, hiddenLayers, numHidden, numOutputs, mutationRate);
    }

    NetController(NetController parent) {
        neuralNet = new NeuralNet(parent.neuralNet);
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
