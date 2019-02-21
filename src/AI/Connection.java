package AI;

import java.util.Random;

class Connection {
    private double weight;
    private Node start;
    private Node end;

    private Random rand = new Random();

    Connection(Node start, Node end) {
        this.start = start;
        this.end = end;
        newWeight();
    }

    Connection(Node start, Node end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    void newWeight() {
        weight = (rand.nextDouble() * 2) - 1;
    }

    double getValue() {
        return start.getValue() * weight;
    }

    double getWeight() {return weight;}

    Node getEnd() {
        return end;
    }
}
