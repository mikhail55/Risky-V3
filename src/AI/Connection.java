package AI;

import java.util.Random;

/**
 * @author TylerWilson
 */
class Connection {
    private float weight;
    private Node start;
    private Node end;

    private Random rand = new Random();

    Connection(Node start, Node end) {
        this.start = start;
        this.end = end;
        newWeight();
    }

    Connection(Node start, Node end, float weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    void newWeight() {
        weight = (float) (rand.nextDouble() * 2) - 1;
    }

    float getValue() {
        return start.getValue() * weight;
    }

    float getWeight() {return weight;}

    Node getEnd() {
        return end;
    }
}
