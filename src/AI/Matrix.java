package AI;

import java.util.Random;

public class Matrix {
    Connection[][] matrix;
    Random rand = new Random();

    Matrix(Node[] fromLayer, Node[] toLayer) {
        matrix = new Connection[fromLayer.length][toLayer.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                matrix[i][n] = new Connection(fromLayer[i], toLayer[n]);
            }
        }
    }

    /**
     * The mutation rate determines what percent of connections should be mutated
     * It uses an RNG to determine which connections are mutated
     * It should work out to about the same percentage of connections as the mutation rate
     * @param mutationRate
     */
    public void Mutate(double mutationRate) {
        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                if((rand.nextDouble() * 100) <= mutationRate) {
                    matrix[i][n].newWeight();
                }
            }
        }
    }

    /**
     * This iterates through each connection and passes the value from the first layer of nodes to the second
     */
    public void Pass() {
        for(int i = 0; i < matrix.length; i++) {
            for(int n = 0; n < matrix[i].length; n++) {
                matrix[i][n].getEnd().updateValue(matrix[i][n].getValue());
            }
        }
    }
}
