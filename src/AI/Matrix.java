package AI;

import java.util.Random;

/**
 * @author TylerWilson
 */
class Matrix {
    private Connection[][] matrix;
    private Random rand = new Random();

    Matrix(Node[] fromLayer, Node[] toLayer) {
        matrix = new Connection[fromLayer.length][toLayer.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                matrix[i][n] = new Connection(fromLayer[i], toLayer[n]);
            }
        }
    }

    Matrix(Node[] fromLayer, Node[] toLayer, Connection[][] weightCopy) {
        matrix = new Connection[fromLayer.length][toLayer.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                matrix[i][n] = new Connection(fromLayer[i], toLayer[n], weightCopy[i][n].getWeight());
            }
        }
    }

    Matrix(Node[] fromLayer, Node[] toLayer, float[][] weightCopy) {
        matrix = new Connection[fromLayer.length][toLayer.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                matrix[i][n] = new Connection(fromLayer[i], toLayer[n], weightCopy[i][n]);
            }
        }
    }

    /**
     * The mutation rate determines what percent of connections should be mutated
     * It uses an RNG to determine which connections are mutated
     * It should work out to about the same percentage of connections as the mutation rate
     * @param mutationRate the percentage of connections that should be mutated
     */
    void Mutate(float mutationRate) {
        for (Connection[] connections: matrix) {
            for (Connection connection: connections) {
                if((float) rand.nextDouble() <= mutationRate) {
                    connection.newWeight();
                }
            }
        }
    }

    Connection[][] getConnections() {
        return matrix;
    }

    /**
     * This iterates through each connection and passes the value from the first layer of nodes to the second
     */
    void Pass() {
        for (Connection[] connections: matrix) {
            for (Connection connection: connections) {
                connection.getEnd().updateValue(connection.getValue());
            }
        }
    }

    float[][] getWeights() {
        float[][] weights = new float[matrix.length][];

        for(int i = 0; i < weights.length; i++) {
            weights[i] = new float[matrix[i].length];
            for(int n = 0; n < weights[i].length; n ++) {
                weights[i][n] = matrix[i][n].getWeight();
            }
        }

        return weights;
    }
}
