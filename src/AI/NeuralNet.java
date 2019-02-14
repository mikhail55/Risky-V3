package AI;

public class NeuralNet {
    private Node[] inputs;
    private Node[][] hidden;
    private Node[] outputs;

    NeuralNet(int numInputs, int hiddenLayers, int numHidden, int numOutputs) {
        inputs = new Node[numInputs + 1];
        hidden = new Node[hiddenLayers][numHidden + 1];
        outputs = new Node[numOutputs];
    }
}
