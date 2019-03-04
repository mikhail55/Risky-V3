package AI;

/**
 * @author Tyler Wilson
 */
public class NeuralNet {
    private Node[] inputNodes;
    private Node[][] hiddenNodes;
    private Node[] outputNodes;

    private Matrix inputToHidden;
    private Matrix[] hiddenToHidden;
    private Matrix hiddenToOutput;

    private double mutationRate;

    /**
     * This constructor creates a neural network from scratch
     *
     * It first creates all of the nodes in each layer, and then creates the matricies of connections between the nodes
     * @param numInputs the number of input nodes in the network
     * @param hiddenLayers the number of hidden layers
     * @param numHidden the number of nodes in a hidden layer
     * @param numOutputs the number of output nodes
     * @param mutationRate the percent of connections that are changed each mutation
     */
    public NeuralNet(int numInputs, int hiddenLayers, int numHidden, int numOutputs, double mutationRate) {
        this.mutationRate = mutationRate;

        inputNodes = new Node[numInputs + 1];
        hiddenNodes = new Node[hiddenLayers][numHidden + 1];
        outputNodes = new Node[numOutputs];

        for (int i = 0; i < inputNodes.length; i++) {
            inputNodes[i] = new Node();
        }
        inputNodes[inputNodes.length - 1].setConstant(true);

        for (int i = 0; i < hiddenNodes.length; i++) {
            for (int n = 0; n < hiddenNodes[i].length; n++) {
                hiddenNodes[i][n] = new Node();
            }
            hiddenNodes[i][hiddenNodes[i].length - 1].setConstant(true);
        }

        for(int i = 0; i < outputNodes.length; i++) {
            outputNodes[i] = new Node();
        }

        inputToHidden = new Matrix(inputNodes, hiddenNodes[0]);

        if(hiddenLayers != 0) {
            hiddenToHidden = new Matrix[hiddenLayers - 1];
            for (int i = 0; i < hiddenToHidden.length; i++) {
                hiddenToHidden[i] = new Matrix(hiddenNodes[i], hiddenNodes[i + 1]);
            }
        }

        hiddenToOutput = new Matrix(hiddenNodes[hiddenNodes.length - 1], outputNodes);
    }

    public NeuralNet(NeuralNet parent) {
        this.mutationRate = parent.mutationRate;

        this.inputNodes = parent.inputNodes;
        System.arraycopy(parent.inputNodes, 0, this.inputNodes, 0, parent.inputNodes.length);

        this.hiddenNodes = parent.hiddenNodes;
        System.arraycopy(parent.hiddenNodes, 0, this.hiddenNodes, 0, parent.hiddenNodes.length);
        for(int i = 0; i < hiddenNodes.length; i++) {
            System.arraycopy(parent.hiddenNodes[i], 0, this.hiddenNodes[i], 0, parent.hiddenNodes[i].length);
        }

        this.outputNodes = parent.outputNodes;
        System.arraycopy(parent.outputNodes, 0, this.outputNodes, 0, parent.outputNodes.length);

        inputToHidden = new Matrix(inputNodes, hiddenNodes[0], parent.inputToHidden.getConnections());

        if(hiddenNodes.length > 0) {
            hiddenToHidden = new Matrix[hiddenNodes.length - 1];
            for (int i = 0; i < hiddenToHidden.length; i++) {
                hiddenToHidden[i] = new Matrix(hiddenNodes[i], hiddenNodes[i + 1], parent.hiddenToHidden[i].getConnections());
            }
        }

        hiddenToOutput = new Matrix(hiddenNodes[hiddenNodes.length - 1], outputNodes, parent.hiddenToOutput.getConnections());
    }

    public NeuralNet(int[][][] weights) {
        this.mutationRate = mutationRate;

        inputNodes = new Node[weights[0].length];
        hiddenNodes = new Node[weights.length - 2][weights[1].length];
        outputNodes = new Node[weights[weights.length - 1][0].length];

        for (int i = 0; i < inputNodes.length; i++) {
            inputNodes[i] = new Node();
        }
        inputNodes[inputNodes.length - 1].setConstant(true);

        for (int i = 0; i < hiddenNodes.length; i++) {
            for (int n = 0; n < hiddenNodes[i].length; n++) {
                hiddenNodes[i][n] = new Node();
            }
            hiddenNodes[i][hiddenNodes[i].length - 1].setConstant(true);
        }

        for(int i = 0; i < outputNodes.length; i++) {
            outputNodes[i] = new Node();
        }

        inputToHidden = new Matrix(inputNodes, hiddenNodes[0], weights[0]);

        if(hiddenNodes.length > 0) {
            hiddenToHidden = new Matrix[hiddenNodes.length - 1];
            for (int i = 0; i < hiddenToHidden.length; i++) {
                hiddenToHidden[i] = new Matrix(hiddenNodes[i], hiddenNodes[i + 1], weights[i + 1]);
            }
        }

        hiddenToOutput = new Matrix(hiddenNodes[hiddenNodes.length - 1], outputNodes, weights[weights.length - 1]);
    }

    /**
     * This mutates the entire network by randomizing mutationRate % of the connections between nodes
     */
    public void Mutate() {
        inputToHidden.Mutate(mutationRate);

        for (Matrix hiddenMatrix : hiddenToHidden) {
            hiddenMatrix.Mutate(mutationRate);
        }

        hiddenToOutput.Mutate(mutationRate);

        ResetNodes();
    }

    /**
     * Push all the information in the inputs through the network, layer by layer, up to the outputs
     *
     * After the output has been determined, reset all the nodes
     * @return Return whichever node had the highest output value
     */
    public int getOutput() {
        inputToHidden.Pass();
        for(int i = 0; i < hiddenToHidden.length; i++) {
            for (Node hiddenNode : hiddenNodes[i]) {
                hiddenNode.finalizeValue();
            }
            hiddenToHidden[i].Pass();
        }
        for (Node hiddenNode : hiddenNodes[hiddenNodes.length - 1]) {
            hiddenNode.finalizeValue();
        }
        hiddenToOutput.Pass();
        for (Node outNode : outputNodes) {
            outNode.finalizeValue();
        }

        int choiceNode = 0;

        for(int i = 1; i < outputNodes.length; i++) {
            if(outputNodes[i].getValue() > outputNodes[choiceNode].getValue()) {
                choiceNode = i;
            }
        }

        ResetNodes();

        return choiceNode;
    }

    public int getNumTroops() {
        inputToHidden.Pass();
        for(int i = 0; i < hiddenToHidden.length; i++) {
            for (Node hiddenNode : hiddenNodes[i]) {
                hiddenNode.finalizeValue();
            }
            hiddenToHidden[i].Pass();
        }
        for (Node hiddenNode : hiddenNodes[hiddenNodes.length - 1]) {
            hiddenNode.finalizeValue();
        }
        hiddenToOutput.Pass();

        int numTroops = 0;

        for(int i = 1; i < outputNodes.length; i++) {
            if(outputNodes[i].getValue() > numTroops) {
                numTroops = i;
            }
        }

        ResetNodes();

        return numTroops;
    }

    public void setInputs(double[] inputs) {
        for(int i = 0; i < inputs.length; i++) {
            inputNodes[i].setValue(inputs[i]);
        }
    }

    /**
     * This simply goes through each node and resets them to their default values.
     *
     * The default value is 0, save for a few nodes that will remain at a value of 1 no matter what (this is to give a
     * constant to each node in the next layer, which would be the weight of the 'constant' nodes connections)
     */
    private void ResetNodes() {
        for (Node inputNode : inputNodes) {
            inputNode.resetValue();
        }

        for (Node[] hiddenLayer : hiddenNodes) {
            for (Node hiddenNode : hiddenLayer) {
                hiddenNode.resetValue();
            }
        }

        for (Node outputNode : outputNodes) {
            outputNode.resetValue();
        }
    }
}
