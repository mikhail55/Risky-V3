package AI;

public class Connection {
    private double weight;
    private Node start;
    private Node end;

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

    public void newWeight() {

    }

    public double getValue() {
        return start.getValue() * weight;
    }

    public Node getEnd() {
        return end;
    }
}
