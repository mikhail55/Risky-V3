package AI;

import java.util.Random;

/**
 * @author TylerWilson
 *
 * A connection between two nodes in a neural network
 * Each connection is assigned a weight to manipulate data as it passes through the network
 * Changing this weight based on success rates is what makes a network 'learn'
 */
class Connection {
    private float weight;

    // The node that the data comes from
    private Node start;

    // The node the data is passed to
    private Node end;

    private Random rand = new Random();

    // This constructor is for a new connection (it creates a random weight
    Connection(Node start, Node end) {
        this.start = start;
        this.end = end;
        newWeight();
    }

    // This creates a connection with the desired weight
    Connection(Node start, Node end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // Generates a random weight between -1 and 1
    void newWeight() {
        weight = (float) (rand.nextDouble() * 2) - 1;
    }

    // Offsets the weight by a value between -1 and 1 (allows the network to tune to larger values if necessary)
    void mutateWeight() {
        weight += (float) (rand.nextDouble() * 2) - 1;
    }

    // Returns the value from the start node after manipulating it with the weight
    float getValue() {
        return start.getValue() * weight;
    }

    float getWeight() {return weight;}
    Node getEnd() {
        return end;
    }
}
