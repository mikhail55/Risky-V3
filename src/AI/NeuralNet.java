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

    private float mutationRate;

    // Backup mutation rate if none is passed
    private float baseMutation = 0.05f;

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
    public NeuralNet(int numInputs, int hiddenLayers, int numHidden, int numOutputs, float mutationRate) {
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

    /**
     * This creates a neural network from a parent.
     *
     * Literally just copies the network
     *
     * @param parent the network to be copied
     */
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

    /**
     * Makes a neural network from saved values
     * @param weights the weights to use when making the network
     */
    public NeuralNet(float[][][] weights) {
        this.mutationRate = baseMutation;

        inputNodes = new Node[weights[0].length];
        hiddenNodes = new Node[weights.length - 1][weights[1].length];
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

    /**
     * Finds the number of troops to make a move with based on the highest value passed through the network
     *
     * Sketchy, yeah, but it might work. I can't test it because nothing else is finished in the game
     * There's no way to know what a trained network will return because I can't train one
     *
     * @return the number of troops
     */
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

    /**
     * Give the network its input values for the next decision
     * @param inputs
     */
    public void setInputs(float[] inputs) {
        for(int i = 0; i < inputs.length; i++) {
            inputNodes[i].setValue(inputs[i]);
        }
    }

    /**
     * This iterates through the network and gets the weight of each connection so it can be saved
     * @return all the weights
     */
    public float[][][] getWeights() {
        float[][][] weights;

        weights = new float[hiddenToHidden.length + 2][][];

        weights[0] = new float[inputToHidden.getWeights().length][];
        for(int i = 0; i < weights[0].length; i ++) {
            weights[0][i] = inputToHidden.getWeights()[i];
        }

        for(int i = 0; i < hiddenToHidden.length; i++) {
            weights[i + 1] = new float[hiddenToHidden[i].getWeights().length][];
            for(int n = 0; n < weights[i + 1].length; n++) {
                weights[i + 1][n] = hiddenToHidden[i].getWeights()[n];
            }
        }

        weights[weights.length - 1] = new float[hiddenToOutput.getWeights().length][];
        for(int i = 0; i < weights[weights.length - 1].length; i ++) {
            weights[weights.length - 1][i] = hiddenToOutput.getWeights()[i];
        }

        return weights;
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
