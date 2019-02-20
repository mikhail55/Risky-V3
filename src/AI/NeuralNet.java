package AI;

public class NeuralNet {
    private Node[] inputNodes;
    private Node[][] hiddenNodes;
    private Node[] outputNodes;

    private Matrix inputToHidden;
    private Matrix[] hiddenToHidden;
    private Matrix hiddenToOutput;

    private double mutationRate;

    NeuralNet(int numInputs, int hiddenLayers, int numHidden, int numOutputs, double mutationRate) {
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

        hiddenToHidden = new Matrix[hiddenLayers - 1];
        for(int i = 0; i < hiddenToHidden.length; i++) {
            hiddenToHidden[i] = new Matrix(hiddenNodes[i], hiddenNodes[i + 1]);
        }

        hiddenToOutput = new Matrix(hiddenNodes[hiddenNodes.length - 1], outputNodes);
    }

    public void Mutate() {
        inputToHidden.Mutate(mutationRate);

        for(int i = 0; i < hiddenToHidden.length; i++) {
            hiddenToHidden[i].Mutate(mutationRate);
        }

        hiddenToOutput.Mutate(mutationRate);
    }

    public int getOutput() {
        inputToHidden.Pass();
        for (int i = 0; i < hiddenToHidden.length; i++) {
            hiddenToHidden[i].Pass();
        }
        hiddenToOutput.Pass();

        int choiceNode = 0;

        for(int i = 1; i < outputNodes.length; i++) {
            if(outputNodes[i].getValue() > outputNodes[choiceNode].getValue()) {
                choiceNode = i;
            }
        }

        return choiceNode;
    }
}
