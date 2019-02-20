package AI;

public class NeuralNet {
    private Node[] inputNodes;
    private Node[][] hiddenNodes;
    private Node[] outputNodes;

    NeuralNet(int numInputs, int hiddenLayers, int numHidden, int numOutputs) {
        inputNodes = new Node[numInputs + 1];
        hiddenNodes = new Node[hiddenLayers][numHidden + 1];
        outputNodes = new Node[numOutputs];


    }
}
