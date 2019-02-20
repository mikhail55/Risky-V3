package AI;

public class Node {

    // node function
    // output = (1/(1 + Math.pow(Math.E, (-1 * output))));

    double value;
    boolean isConstant = false;

    Node() {

    }

    public void setConstant(boolean bool) {
        isConstant = bool;
        value = 1;
    }

    public double getValue() {
        return (1/(1 + Math.pow(Math.E, (-1 * value))));
    }

    public void updateValue(double newValue) {
        value += newValue;
    }

    public void resetValue() {
        value = 0;

        if(isConstant) {
            value = 1;
        }
    }

    public void setValue(double newVal) {
        value = newVal;
    }
}
